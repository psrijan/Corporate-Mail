package com.srijan.springfundamentals.job.wish;

import com.srijan.springfundamentals.builder.EmailDetailBuilder;
import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.entities.Festival;
import com.srijan.springfundamentals.others.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BirthdayWishJob extends AbstractBirthdayWishJob {

//    @Scheduled(fixedRate = 5000)
    public void sendWishes() {
        List<Client> friendList = friendRepository.findAll();
        ApplicationUser applicationUser = applicationUserRepository.findById(1l).get();
        Festival birthdayFestival = festivalRepository.getFestivalByCode(Occassion.BIRTHDAY.toString()).get();

        List<EmailDetail> birthdayList = friendList.stream()
                .filter(friend -> DateUtil.checkDate(friend.getBirthday()))
                .map(friend -> EmailDetailBuilder.buildBirthdayDetail(applicationUser, friend, birthdayFestival))
                .collect(Collectors.toList());

        birthdayList.stream().forEach(emailDetail -> {

            if (!isEmailSent(emailDetail)) {
                boolean isEmailSent = emailService.sendIndividualMail(emailDetail);
                if (isEmailSent) {
                    AlertLog wishLog = new AlertLog();
                    wishLog.setDate(new Date());
                    wishLog.setApplicationUser(applicationUser);
                    wishLog.setFriend(emailDetail.getFriend());
                    wishLog.setAlertType(Occassion.BIRTHDAY.toString());
                    wishLog.setWished('Y');
                    wishLog.setYear(DateUtil.getThisYear());
                    wishLogRepository.save(wishLog);
                }
            }
        });
    }
}
