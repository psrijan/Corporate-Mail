package com.srijan.springfundamentals.job.alert;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.modules.EmailService;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import com.srijan.springfundamentals.repository.FriendRepository;
import com.srijan.springfundamentals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAlertJob {


    @Autowired
    protected FriendRepository friendRepository;

    @Autowired
    protected UserRepository applicationUserRepository;

    @Autowired
    protected EmailService emailService;

    @Autowired
    protected AlertLogRepository alertLogRepository;


    protected List<Client> birthdayClientList(List<Client> friendList) {

        List<Client> tomorrowBirthdayFriendList = friendList.stream().filter(friend -> DateUtil.checkBirthdayTomorrow(friend.getBirthday())).collect(Collectors.toList());
        return tomorrowBirthdayFriendList;

    }

    protected boolean isEmailSent(EmailDetail emailDetail) {

        long count = alertLogRepository.countWishLogByYear(emailDetail.getApplicationUser().getId(),
                emailDetail.getFriend().getId(),
                DateUtil.getThisYear(), Occassion.BIRTHDAYALERT.toString());

        return count > 0;

    }


    protected void persistAlertSent(EmailDetail emailDetail) {
        AlertLog alertLog = new AlertLog();
        alertLog.setApplicationUser(emailDetail.getApplicationUser());
        alertLog.setFriend(emailDetail.getFriend());
        alertLog.setAlertType(Occassion.BIRTHDAYALERT.toString());
        alertLog.setDate(new Date());
        alertLog.setWished('Y');
        alertLog.setYear(DateUtil.getThisYear());
        alertLogRepository.save(alertLog);
    }
}
