package com.srijan.springfundamentals.job.wish;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.modules.EmailService;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import com.srijan.springfundamentals.repository.FestivalRepository;
import com.srijan.springfundamentals.repository.FriendRepository;
import com.srijan.springfundamentals.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Slf4j
public abstract class AbstractBirthdayWishJob {


    @Autowired
    protected EmailService emailService;

    @Autowired
    protected UserRepository applicationUserRepository;

    @Autowired
    protected FriendRepository friendRepository;

    @Autowired
    protected AlertLogRepository wishLogRepository;

    @Autowired
    protected FestivalRepository festivalRepository;



    public boolean isEmailSent(EmailDetail emailDetail) {
        long wishLogCount = wishLogRepository.countWishLogByYear(emailDetail.getApplicationUser().getId(), emailDetail.getFriend().getId(), DateUtil.getThisYear(), Occassion.BIRTHDAY.toString());
        log.info("Wish Count: {}", wishLogCount);
        return wishLogCount >0;
    }

    protected void saveEmailSentDetail(EmailDetail emailDetail) {
        log.info("Persisting Data  For {} {}" , emailDetail.getReceiverName() , emailDetail.getDateOfBirth());
        AlertLog wishLog = new AlertLog();
        wishLog.setDate(new Date());
        wishLog.setApplicationUser(emailDetail.getApplicationUser());
        wishLog.setFriend(emailDetail.getFriend());
        wishLog.setAlertType(Occassion.BIRTHDAY.toString());
        wishLog.setWished('Y');
        wishLog.setYear(DateUtil.getThisYear());
        wishLogRepository.save(wishLog);
    }


}
