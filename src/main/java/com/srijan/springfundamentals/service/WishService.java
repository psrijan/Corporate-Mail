package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.entities.Friend;
import com.srijan.springfundamentals.others.CheckDate;
import com.srijan.springfundamentals.repository.FriendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WishService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private FriendRepository friendRepository;

    @Scheduled(fixedRate = 5000)
    public void sendWishes() {
        log.debug("Hello World --");
        List<Friend> friendList = friendRepository.findAll();

        List<Friend> birthdayList = friendList.stream()
                .filter(friend -> CheckDate.checkDate(friend.getBirthday()))
                .collect(Collectors.toList());

        try {
            emailService.sendMail(birthdayList);
        } catch (Exception ex ) {
            ex.printStackTrace();
        }

    }





}
