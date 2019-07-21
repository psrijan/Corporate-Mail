package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.dto.request.statistics.*;
import com.srijan.springfundamentals.dto.response.statistics.FestivalStatisticsResponse;
import com.srijan.springfundamentals.dto.response.statistics.UpcomingBirthdayResponse;
import com.srijan.springfundamentals.dto.response.statistics.UpcomingFestivalResponse;
import com.srijan.springfundamentals.dto.response.statistics.WishStatisticsResponse;
import com.srijan.springfundamentals.entities.AlertLog;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.mapper.MailStatisticsMapper;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import com.srijan.springfundamentals.repository.ClientRepository;
import com.srijan.springfundamentals.service.MailStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class MailStatisticsServiceImpl implements MailStatisticsService {

    @Autowired
    private AlertLogRepository alertLogRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public WishStatisticsResponse birthdayWishes(WishStatisticsRequest wishStatisticsRequest) {

        java.sql.Date startDate = calculateStartDate(wishStatisticsRequest.getWishType());
        java.sql.Date endDate = new java.sql.Date(new java.util.Date().getTime());

        List<AlertLog> alertLogList = alertLogRepository.getBirthdayAlertLogByDate(startDate , endDate );
        return MailStatisticsMapper.mapToBirthdayWishStatistics(alertLogList);
    }

    @Override
    public UpcomingBirthdayResponse upcomingBirthday(UpcomingBirthdayRequest upcomingBirthdayRequest) {

        java.sql.Date startDate = new java.sql.Date(new java.util.Date().getTime());
        java.sql.Date endDate = calculateUpcomingBirthdayEndDate(upcomingBirthdayRequest.getWishType());
        log.info("Start Date: {}" , startDate,toString() );
        log.info("End Date: {} " , endDate);
        List<Client> clientList = clientRepository.clientsWithBirthdayBetween(startDate, endDate);
        return MailStatisticsMapper.mapToUpcomingBirthdayResponse(clientList);

    }

    @Override
    public UpcomingFestivalResponse upcomingFestival(UpcomingFestivalRequest upcomingFestivalRequest) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM");

        //With the current implementation of festival finding upcoming date is
        //not possible

        return null;
    }

    @Override
    public FestivalStatisticsResponse festivalWishes(FestivalStatisticsRequest festivalStatisticsRequest) {

        java.sql.Date startDate = calculateStartDate(festivalStatisticsRequest.getWishType());
        java.sql.Date endDate = new java.sql.Date(new java.util.Date().getTime());

        List<AlertLog> alertLogList = alertLogRepository.getFestivalAlertLogByDate(startDate , endDate , Occassion.FESTIVAL.toString());
        return MailStatisticsMapper.mapToFestivalWishStatistics(alertLogList);
    }

    private java.sql.Date calculateStartDate(WishType wishType) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        switch (wishType) {
            case DAY:
                calendar.add(Calendar.DATE, -1);
                break;
            case WEEK:
                calendar.add(Calendar.DATE, -7);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH , -1);
                break;
            case YEAR:
                calendar.add(Calendar.YEAR, -1);
                break;
            default:
                log.info("Default Case Met. Some Error with date calculation");

        }
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    private java.sql.Date calculateUpcomingBirthdayEndDate(WishType wishType) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        switch (wishType) {
            case DAY:
                calendar.add(Calendar.DATE, 1);
            case WEEK:
                calendar.add(Calendar.DATE, 7);
            case MONTH:
                calendar.add(Calendar.MONTH , 1);
            case HALFYEAR:
                calendar.add(Calendar.MONTH, 6);
            default:
                log.info("Default Case Met. Some Error with date calculation");

        }
        return new java.sql.Date(calendar.getTimeInMillis());
    }

}
