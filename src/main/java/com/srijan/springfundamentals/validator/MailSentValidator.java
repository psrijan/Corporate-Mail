package com.srijan.springfundamentals.validator;

import com.srijan.springfundamentals.dto.EmailDetail;
import com.srijan.springfundamentals.dto.Occassion;
import com.srijan.springfundamentals.others.DateUtil;
import com.srijan.springfundamentals.repository.AlertLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailSentValidator {

    @Autowired
    private AlertLogRepository alertLogRepository;

    public boolean isFestivalEmailSent(EmailDetail emailDetail , String alertType) {

        long count = alertLogRepository.countFestivalWishLogByYear(emailDetail.getApplicationUser().getId(),
                emailDetail.getFriend().getId(),
                DateUtil.getThisYear(), alertType);

        return count > 0;

    }

}
