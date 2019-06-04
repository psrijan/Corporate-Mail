package com.srijan.springfundamentals.scheduleservice;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Friend;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import com.srijan.springfundamentals.repository.UserRepository;
import com.srijan.springfundamentals.repository.FriendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Alerts the application user of potential birthday of a friend
 */
@Slf4j
@Service
public class BirthdayAlertService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository applicationUserRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AlertLogRepository alertLogRepository;

//    @Scheduled(fixedRate = 5000)
    public void birthdayAlert() {
        List<Friend> friendList = friendRepository.findAll();
        List<Friend> friendTomBirthdayList = friendList.stream().filter(friend -> DateUtil.checkBirthdayTomorrow(friend.getBirthday())).collect(Collectors.toList());
        ApplicationUser applicationUser = applicationUserRepository.findById(1l).get();

        friendTomBirthdayList.forEach(friend -> {
            EmailDetail emailDetail = EmailDetail.builder()
                    .senderEmail(applicationUser.getEmailAddress())
                    .senderName(applicationUser.getName())
                    .receiverEmail(applicationUser.getEmailAddress())
                    .receiverName(applicationUser.getName())
                    .eventCode(Occassion.BIRTHDAYALERT.toString())
                    .eventName("Birthday Alert")
                    .data(friend.getName())
                    .eventUrl(null)
                    .occasion(Occassion.BIRTHDAYALERT)
                    .friend(friend)
                    .applicationUser(applicationUser)
                    .build();
            boolean isSent = emailService.sendIndividualMail(emailDetail);

            if (isSent) {
                AlertLog alertLog = new AlertLog();
                alertLog.setApplicationUser(applicationUser);
                alertLog.setFriend(friend);
                alertLog.setName(Occassion.BIRTHDAYALERT.toString());
                alertLog.setDate(new Date());
                alertLog.setWished('Y');
                alertLog.setYear(DateUtil.getThisYear());
                alertLogRepository.save(alertLog);
            }
        });
    }
}
