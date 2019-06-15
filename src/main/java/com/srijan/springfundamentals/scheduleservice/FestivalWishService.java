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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FestivalWishService {

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private AlertLogRepository wishLogRepository;

    @Autowired
    private UserRepository applicationUserRepository;

    @Autowired
    private EmailService emailService;

    //    @Scheduled(fixedRate = 5000)
    public void wishPeopleFestival() {

        ApplicationUser applicationUser = applicationUserRepository.findById(1l).get();
        String today = DateUtil.getFormattedDateForFestival();
        List<Festival> festivalList = festivalRepository.getFestivalbyDate(today);

        List<Client> friends = friendRepository.findAll();

        festivalList.forEach(festival -> {
            friends.forEach(friend -> {

                long wishCount = wishLogRepository.countWishLogByYear(applicationUser.getId(), friend.getId(), DateUtil.getThisYear(), Occassion.FESTIVAL.toString() + "-" + festival.getCode());
                if (wishCount == 0) {
                    EmailDetail emailDetail = EmailDetail.builder()
                            .senderName(applicationUser.getName())
                            .senderEmail(applicationUser.getEmailAddress())
                            .receiverName(friend.getName())
                            .receiverEmail(friend.getEmailAddress())
                            .eventUrl(festival.getLogoUrl())
                            .eventName(festival.getName())
                            .eventCode(festival.getCode())
                            .occasion(Occassion.FESTIVAL)
                            .friend(friend)
                            .applicationUser(applicationUser)
                            .build();
                    boolean success = emailService.sendIndividualMail(emailDetail);

                    if (success) {
                        AlertLog wishLog = new AlertLog();
                        wishLog.setYear(DateUtil.getThisYear());
                        wishLog.setWished('Y');
                        wishLog.setName(Occassion.FESTIVAL.toString() + "-" + festival.getCode());
                        wishLog.setFriend(friend);
                        wishLog.setApplicationUser(applicationUser);
                        wishLog.setDate(new Date());
                        wishLogRepository.save(wishLog);
                    }
                }
            });
        });
    }

}
