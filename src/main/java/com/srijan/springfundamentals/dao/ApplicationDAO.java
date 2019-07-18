package com.srijan.springfundamentals.dao;

import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.entities.Festival;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class ApplicationDAO {

    @Autowired
    private AlertLogRepository alertLogRepository;

    public boolean addAlertLog(Client friend , ApplicationUser applicationUser , Festival festival , String alertType) {
        try {
            AlertLog wishLog = new AlertLog();
            wishLog.setYear(DateUtil.getThisYear());
            wishLog.setWished('Y');
            wishLog.setAlertType(alertType);
            wishLog.setFriend(friend);
            wishLog.setApplicationUser(applicationUser);
            wishLog.setDate(new Date());
            alertLogRepository.save(wishLog);
            return true;
        } catch (Exception e) {
            log.error("Exception : " , e );
        }
        return false;
    }

}
