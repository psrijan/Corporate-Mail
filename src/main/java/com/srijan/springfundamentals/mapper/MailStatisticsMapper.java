package com.srijan.springfundamentals.mapper;

import com.srijan.springfundamentals.dto.response.statistics.*;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.others.DateUtil;

import java.util.List;
import java.util.stream.Collectors;

public class MailStatisticsMapper {

    public static WishStatisticsResponse mapToBirthdayWishStatistics(List<AlertLog> alertLogList) {
        WishStatisticsResponse wishStatisticsResponse  = new WishStatisticsResponse();
        wishStatisticsResponse.setCount(alertLogList.size());

        List<BirthdayWishInfo> birthdayWishInfoList = alertLogList.stream().map(alertLog -> {
            BirthdayWishInfo birthdayWishInfo = new BirthdayWishInfo();
            birthdayWishInfo.setName(alertLog.getFriend().getName());
            birthdayWishInfo.setBirthday(DateUtil.formatDateToString(alertLog.getDate() , DateUtil.STANDARD));
            return birthdayWishInfo;
        }).collect(Collectors.toList());
        wishStatisticsResponse.setWishInfoList(birthdayWishInfoList);
        return wishStatisticsResponse;
    }

    public static UpcomingBirthdayResponse mapToUpcomingBirthdayResponse(List<Client> clientList) {
        UpcomingBirthdayResponse upcomingBirthdayResponse = new UpcomingBirthdayResponse();
        upcomingBirthdayResponse.setCount(clientList.size());

        List<BirthdayWishInfo> birthdayWishInfoList = clientList.stream().map(client -> {
            BirthdayWishInfo birthdayWishInfo = new BirthdayWishInfo();
            birthdayWishInfo.setName(client.getName());
            birthdayWishInfo.setBirthday(DateUtil.formatDateToString(client.getBirthday() , DateUtil.STANDARD));
            return birthdayWishInfo;
        }).collect(Collectors.toList());
        upcomingBirthdayResponse.setWishInfoList(birthdayWishInfoList);
        return upcomingBirthdayResponse;
    }

    public static FestivalStatisticsResponse mapToFestivalWishStatistics(List<AlertLog> alertLogList) {
        FestivalStatisticsResponse festivalStatisticsResponse = new FestivalStatisticsResponse();
        festivalStatisticsResponse.setCount(alertLogList.size());


        List<FestivalStatisticsInfo> birthdayWishInfoList = alertLogList.stream().map(alertLog -> {
            FestivalStatisticsInfo festivalStatisticsInfo = new FestivalStatisticsInfo();
            festivalStatisticsInfo.setFestivalName(alertLog.getAlertType());
            festivalStatisticsInfo.setWishDate(DateUtil.formatDateToString(alertLog.getDate() , DateUtil.STANDARD));
            festivalStatisticsInfo.setWishedPerson(alertLog.getFriend().getName());
            return festivalStatisticsInfo;
        }).collect(Collectors.toList());
        festivalStatisticsResponse.setFestivalStatisticsInfoList(birthdayWishInfoList);
        return festivalStatisticsResponse;
    }


}
