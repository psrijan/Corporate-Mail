package com.srijan.springfundamentals.job.festival;


import com.srijan.springfundamentals.builder.EmailDetailBuilder;
import com.srijan.springfundamentals.dao.ApplicationDAO;
import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.entities.Festival;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.validator.MailSentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FestivalWishJob extends FestivalJob {

    @Autowired
    private MailSentValidator mailSentValidator;

    @Autowired
    private ApplicationDAO applicationDAO;

//    @Scheduled(cron = "${cron.pattern.festival-wish}")
    public void wishPeopleFestival() {

        ApplicationUser applicationUser = applicationUserRepository.findById(1l).get();
        String today = DateUtil.getFormattedDateForFestival();
        List<Festival> festivalList = festivalRepository.getFestivalbyDate(today);
        List<Client> friends = friendRepository.findAll();

        festivalList.forEach(festival -> {
            friends.forEach(friend -> {
                if (!isEmailSent(applicationUser, friend , festival)) {
                    EmailDetail emailDetail = EmailDetailBuilder.buildFestivalDetail(applicationUser, festival, friend);
                    boolean isEmailSent = mailSentValidator.isFestivalEmailSent(emailDetail , Occassion.FESTIVAL.toString());
                    if(!isEmailSent) {
                        boolean newMailSent = emailService.sendIndividualMail(emailDetail);
                        if (newMailSent) {
                            applicationDAO.addAlertLog(friend , applicationUser , festival , Occassion.FESTIVAL.toString() + "-" + festival.getCode());
                        }
                    }
                }
            });
        });
    }
}
