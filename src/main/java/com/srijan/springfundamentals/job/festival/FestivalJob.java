package com.srijan.springfundamentals.job.festival;

import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.entities.ApplicationUser;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.entities.Festival;
import com.srijan.springfundamentals.modules.EmailService;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import com.srijan.springfundamentals.repository.FestivalRepository;
import com.srijan.springfundamentals.repository.FriendRepository;
import com.srijan.springfundamentals.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FestivalJob {
    @Autowired
    protected FestivalRepository festivalRepository;

    @Autowired
    protected FriendRepository friendRepository;

    @Autowired
    protected AlertLogRepository wishLogRepository;

    @Autowired
    protected UserRepository applicationUserRepository;

    @Autowired
    protected EmailService emailService;

    public boolean isEmailSent(ApplicationUser applicationUser, Client friend , Festival festival) {
        long wishCount = wishLogRepository.countWishLogByYear(applicationUser.getId(), friend.getId(), DateUtil.getThisYear(), Occassion.FESTIVAL.toString() + "-" + festival.getCode());
        return wishCount > 0;
    }
    
    
}
