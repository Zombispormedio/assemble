package com.zombispormedio.assemble.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class DateUtils {

    public final static String DEFAULT_INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public final static String SIMPLE_SLASH_FORMAT = "dd/MM/yyyy";

    public static String format(String format, String inDate) throws ParseException {
        DateFormat inFormat = new SimpleDateFormat(DEFAULT_INPUT_FORMAT, Locale.ENGLISH);
        Date outDate = inFormat.parse(inDate);
        DateFormat outFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return outFormat.format(outDate);
    }

    public static String format(String inFormatStr, String outFormatStr, String inDate) throws ParseException {
        DateFormat inFormat = new SimpleDateFormat(inFormatStr, Locale.ENGLISH);
        Date outDate = inFormat.parse(inDate);
        DateFormat outFormat = new SimpleDateFormat(outFormatStr, Locale.ENGLISH);
        return outFormat.format(outDate);
    }

    public static long appendYearsToNow(int operator) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, operator);
        return date.getTimeInMillis();
    }

    public static String toString(int year, int month, int day) {
        Calendar cal= Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND, 0);


        DateFormat formatUtility = new SimpleDateFormat(DEFAULT_INPUT_FORMAT, Locale.ENGLISH);

        return formatUtility.format(cal.getTime());
    }

    public static Date parse(String format, String inDate) throws ParseException {
        DateFormat inFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return inFormat.parse(inDate);
    }

    public static Calendar parse(String inDate) throws ParseException {
        DateFormat inFormat = new SimpleDateFormat(DEFAULT_INPUT_FORMAT, Locale.ENGLISH);
        Calendar outDate = Calendar.getInstance();
        outDate.setTime(inFormat.parse(inDate));
        return outDate;
    }

}
