package com.srijan.springfundamentals.job.wish;

import com.srijan.springfundamentals.builder.EmailDetailBuilder;
import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Festival;
import com.srijan.springfundamentals.others.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BirthdayWishJob extends AbstractBirthdayWishJob {

    @Scheduled(cron = "${cron.pattern.birthday-wish}")
    public void sendWishes() {
        log.info("Birthday Wish Job Start...");

        try {
            List<ApplicationUser> applicationUserList = applicationUserRepository.findApplicationUserList();
            applicationUserList.stream().forEach(user -> wishBirthdayForUsersClient(user));
        } catch (Exception ex ) {
            log.error("Exception " , ex );
        }

    }

    private void wishBirthdayForUsersClient(ApplicationUser user) {

        Festival birthdayFestival = festivalRepository.getFestivalByCode(Occassion.BIRTHDAY.toString()).get();
        List<EmailDetail> birthdayList = user.getClientList().stream()
                .filter(friend -> DateUtil.checkIsBirthday(friend.getBirthday()))
                .map(friend -> EmailDetailBuilder.buildBirthdayDetail(user, friend, birthdayFestival))
                .collect(Collectors.toList());

        log.info("Birthday List: {} ", birthdayList.toString());
        birthdayList.stream().forEach(emailDetail -> {
            if (!isEmailSent(emailDetail)) {
                if (emailService.sendIndividualMail(emailDetail)) {
                    saveEmailSentDetail(emailDetail);
                }
            }
        });
    }
}
