package com.srijan.springfundamentals.scheduleservice;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Festival;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import com.srijan.springfundamentals.repository.UserRepository;
import com.srijan.springfundamentals.repository.FestivalRepository;
import com.srijan.springfundamentals.repository.FriendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BirthdayWishService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository applicationUserRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private AlertLogRepository wishLogRepository;

    @Autowired
    private FestivalRepository festivalRepository;

//    @Scheduled(fixedRate = 10000)
    public void sendWishes() {
        List<Client> friendList = friendRepository.findAll();
        ApplicationUser applicationUser = applicationUserRepository.findById(1l).get();
        Festival birthdayFestival = festivalRepository.getFestivalByCode(Occassion.BIRTHDAY.toString()).get();

        List<EmailDetail> birthdayList = friendList.stream()
                .filter(friend -> DateUtil.checkDate(friend.getBirthday()))
                .map(friend -> EmailDetail.builder()
                        .senderEmail(applicationUser.getEmailAddress())
                        .senderName(applicationUser.getName())
                        .receiverEmail(friend.getEmailAddress())
                        .receiverName(friend.getName())
                        .eventCode(Occassion.BIRTHDAY.toString())
                        .eventName(birthdayFestival.getName())
                        .eventUrl(birthdayFestival.getLogoUrl())
                        .friend(friend)
                        .applicationUser(applicationUser)
                        .build())
                .collect(Collectors.toList());

        birthdayList.stream().forEach(emailDetail -> {
            long wishLogCount = wishLogRepository.countWishLogByYear(applicationUser.getId(), emailDetail.getFriend().getId(), DateUtil.getThisYear(), Occassion.BIRTHDAY.toString());
            log.info("Wish Count: {}", wishLogCount);
            if (wishLogCount == 0) {
                boolean isEmailSent = emailService.sendIndividualMail(emailDetail);
                if (isEmailSent) {
                    AlertLog wishLog = new AlertLog();
                    wishLog.setDate(new Date());
                    wishLog.setApplicationUser(applicationUser);
                    wishLog.setFriend(emailDetail.getFriend());
                    wishLog.setName(Occassion.BIRTHDAY.toString());
                    wishLog.setWished('Y');
                    wishLog.setYear(DateUtil.getThisYear());
                    wishLogRepository.save(wishLog);
                }
            }
        });
    }
}
