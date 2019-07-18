package com.srijan.springfundamentals.job.alert;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.others.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Alerts the application user of potential birthday of a friend
 */
@Slf4j
@Service
public class BirthdayAlertJob extends AbstractAlertJob{


//    @Scheduled(fixedRate = 5000)
    public void birthdayAlert() {

        List<Client> birthdayClientList = birthdayClientList();
        ApplicationUser applicationUser = applicationUserRepository.findApplicationUserById(1l).get();

        birthdayClientList.forEach(friend -> {
            EmailDetail emailDetail = buildEmailDetail(applicationUser , friend);

            if(!isEmailSent(emailDetail)) {

                if (emailService.sendIndividualMail(emailDetail)) {
                    AlertLog alertLog = new AlertLog();
                    alertLog.setApplicationUser(applicationUser);
                    alertLog.setFriend(friend);
                    alertLog.setAlertType(Occassion.BIRTHDAYALERT.toString());
                    alertLog.setDate(new Date());
                    alertLog.setWished('Y');
                    alertLog.setYear(DateUtil.getThisYear());
                    alertLogRepository.save(alertLog);
                }
            }
        });
    }
}
