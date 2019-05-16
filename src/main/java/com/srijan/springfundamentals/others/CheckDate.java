package com.srijan.springfundamentals.others;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckDate {

    public static boolean checkDate(Date birthday){

        Date todaysDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);


        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(birthday);

        if(calendar.get(Calendar.MONTH) == birthdayCalendar.get(Calendar.MONTH)
            &&  calendar.get(Calendar.DATE) == birthdayCalendar.get(Calendar.DATE))
            return true;
        return false;
    }

    public static void main(String[] args ) {
        Date date = new Date();
        System.out.println(checkDate(date));
    }

}
