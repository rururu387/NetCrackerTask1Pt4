package com.src;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


// Subtask 2
public class CalendarDateFormatter
{
    public static Date formatToDate(String dateStr) throws ParseException
    {
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return (Date)formatter.parse(dateStr);
    }

    public static Calendar formatToCalendar(String dateStr) throws ParseException
    {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        calendar.setTime(formatter.parse(dateStr));
        return calendar;
    }
}
