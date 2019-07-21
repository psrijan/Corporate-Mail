package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.statistics.FestivalStatisticsRequest;
import com.srijan.springfundamentals.dto.request.statistics.UpcomingBirthdayRequest;
import com.srijan.springfundamentals.dto.request.statistics.WishStatisticsRequest;
import com.srijan.springfundamentals.dto.response.statistics.FestivalStatisticsResponse;
import com.srijan.springfundamentals.dto.response.statistics.UpcomingBirthdayResponse;
import com.srijan.springfundamentals.dto.response.statistics.WishStatisticsResponse;
import com.srijan.springfundamentals.service.MailStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("statistics")
public class MailStatisticsController {

    @Autowired
    private MailStatisticsService mailStatisticsService;

    @PostMapping(path = "wish", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WishStatisticsResponse birthdayWishes(@Valid @RequestBody WishStatisticsRequest wishStatisticsRequest) {
        log.info("Entering Birthday Wish Statistics API...");
        return mailStatisticsService.birthdayWishes(wishStatisticsRequest);
    }

    @PostMapping(path = "upcoming/birthday", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UpcomingBirthdayResponse upcomingBirthday(@Valid @RequestBody UpcomingBirthdayRequest upcomingBirthdayRequest) {
        log.info("Entering Upcoming Birthday Statistics API...");
        return mailStatisticsService.upcomingBirthday(upcomingBirthdayRequest);
    }

    @PostMapping(path = "upcoming/festival", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FestivalStatisticsResponse festivalWishes(@Valid @RequestBody FestivalStatisticsRequest festivalStatisticsRequest) {
        log.info("Entering Festival Wish Statsitics API...");
        return mailStatisticsService.festivalWishes(festivalStatisticsRequest);
    }
}
