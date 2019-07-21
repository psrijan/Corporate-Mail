package com.srijan.springfundamentals.service;


import com.srijan.springfundamentals.dto.request.statistics.FestivalStatisticsRequest;
import com.srijan.springfundamentals.dto.request.statistics.UpcomingBirthdayRequest;
import com.srijan.springfundamentals.dto.request.statistics.UpcomingFestivalRequest;
import com.srijan.springfundamentals.dto.request.statistics.WishStatisticsRequest;
import com.srijan.springfundamentals.dto.response.statistics.FestivalStatisticsResponse;
import com.srijan.springfundamentals.dto.response.statistics.UpcomingBirthdayResponse;
import com.srijan.springfundamentals.dto.response.statistics.UpcomingFestivalResponse;
import com.srijan.springfundamentals.dto.response.statistics.WishStatisticsResponse;

public interface MailStatisticsService {

    WishStatisticsResponse birthdayWishes(WishStatisticsRequest wishStatisticsRequest);

    UpcomingBirthdayResponse upcomingBirthday(UpcomingBirthdayRequest upcomingBirthdayRequest);

    UpcomingFestivalResponse upcomingFestival(UpcomingFestivalRequest upcomingFestivalRequest);

    FestivalStatisticsResponse festivalWishes(FestivalStatisticsRequest festivalStatisticsRequest);

}
