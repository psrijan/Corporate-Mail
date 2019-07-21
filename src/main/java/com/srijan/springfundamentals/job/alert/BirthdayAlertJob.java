package com.srijan.springfundamentals.job.alert;

import com.srijan.springfundamentals.builder.EmailDetailBuilder;
import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Alerts the application user of potential birthday of a friend
 */
@Slf4j
@Service
public class BirthdayAlertJob extends AbstractAlertJob {


    @Scheduled(cron = "${cron.pattern.birthday-alert}")
    public void birthdayAlert() {
        log.info("Start of Birthday Alert Job...");
        try {
            List<ApplicationUser> applicationUserList = applicationUserRepository.findApplicationUserList();
            applicationUserList.forEach(applicationUser -> sendBirthdayAlertForUser(applicationUser));
        } catch (Exception ex) {
            log.error("Exception ", ex);
        }
    }

    private void sendBirthdayAlertForUser(ApplicationUser applicationUser) {
        List<Client> birthdayClientList = birthdayClientList(applicationUser.getClientList());
        birthdayClientList.forEach(friend -> {
            EmailDetail emailDetail = EmailDetailBuilder.buildBirthdayAlertDetail(applicationUser, friend);
            if (!isEmailSent(emailDetail)) {
                if (emailService.sendIndividualMail(emailDetail)) {
                    persistAlertSent(emailDetail);
                }
            }
        });
    }
}
