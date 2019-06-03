package com.srijan.springfundamentals.others;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    private static final String months = "Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec";

    public static boolean checkDate(Date birthday) {
        Date todaysDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);


        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(birthday);

        if (calendar.get(Calendar.MONTH) == birthdayCalendar.get(Calendar.MONTH)
                && calendar.get(Calendar.DATE) == birthdayCalendar.get(Calendar.DATE))
            return true;
        return false;
    }

    public static boolean checkBirthdayTomorrow(Date birthday) {

        Date todaysDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);
        calendar.add(Calendar.DAY_OF_YEAR , 1);

        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(birthday);

        if (calendar.get(Calendar.MONTH) == birthdayCalendar.get(Calendar.MONTH)
                && calendar.get(Calendar.DATE) == birthdayCalendar.get(Calendar.DATE))
            return true;

        return false;

    }


    public static String getThisYear() {
        SimpleDateFormat yearOnly = new SimpleDateFormat("YYYY");
        return yearOnly.format(new Date());
    }

    public static String getFormattedDateForFestival() {

        Date date = new Date();
        Integer dateVal = date.getDate();
        Integer month = date.getMonth();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateVal).append(" ");

        List<String> monthList = Arrays.asList(months.split(","));
        return stringBuilder.append(monthList.get(month)).toString();
    }


}
