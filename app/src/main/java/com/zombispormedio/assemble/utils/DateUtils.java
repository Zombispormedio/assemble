package com.zombispormedio.assemble.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Calendar;

import java.util.Locale;


/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class DateUtils {

    public final static String DEFAULT_INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public final static String SIMPLE_SLASH_FORMAT = "dd/MM/yyyy";

    public final static String SIMPLE_SLASH_FORMAT_WITH_HOUR = "dd/MM/yyyy HH:mm";

    public static String formatISODate(String format, String inDate) {
        DateTime d = ISODateTimeFormat.dateTime().parseDateTime(inDate);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withLocale(Locale.getDefault());
        return d.toString(formatter);
    }

    public static long appendYearsToNow(int operator) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, operator);
        return date.getTimeInMillis();
    }

    public static String convertToISOString(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        DateTimeFormatter format = ISODateTimeFormat.dateTime();

        return format.print(cal.getTimeInMillis());
    }

    public static String convertToISOString(int year, int month, int day, int hour, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        DateTimeFormatter format = ISODateTimeFormat.dateTime();

        return format.print(cal.getTimeInMillis());
    }

    public static Calendar parse(String inDate) {
        DateTime d = ISODateTimeFormat.dateTime().parseDateTime(inDate);
        Calendar outDate = Calendar.getInstance();
        outDate.setTimeInMillis(d.getMillis());
        return outDate;
    }

    public static int compareISODateString(String d1, String d2){
        Calendar c1=parse(d1);
        Calendar c2=parse(d2);
        return c1.compareTo(c2);
    }

    public static boolean isToday(String date) {
        boolean is;

        Calendar cal = parse(date);

        is = cal.get(Calendar.YEAR) == Now.YEAR() && cal.get(Calendar.MONTH) == Now.MONTH()
                && cal.get(Calendar.DAY_OF_MONTH) == Now.DAY();

        return is;
    }

    public static boolean isYesterday(String date) {
        boolean is;

        Calendar cal = parse(date);

        is = cal.get(Calendar.YEAR) == Now.YEAR() && cal.get(Calendar.MONTH) == Now.MONTH() &&
                cal.get(Calendar.DAY_OF_YEAR) == getYesterday().get(Calendar.DAY_OF_YEAR);

        return is;
    }


    public static class Now {

        private static Calendar Cal() {
            return Calendar.getInstance();
        }

        static int YEAR() {
            return Cal().get(Calendar.YEAR);
        }

        static int MONTH() {
            return Cal().get(Calendar.MONTH);
        }

        static int DAY() {
            return Cal().get(Calendar.DAY_OF_MONTH);
        }

        static int HOUR() {
            return Cal().get(Calendar.HOUR_OF_DAY);
        }

        static int MINUTES() {
            return Cal().get(Calendar.MINUTE);
        }

        public static String toDateString() {
            return DateUtils.convertToISOString(YEAR(), MONTH(), DAY(), HOUR(), MINUTES());
        }
    }


    public static Calendar getYesterday() {
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR, -1);

        return c1;
    }


    public static class DateBuilder {

        private int year;

        private int month;

        private int day;

        private int hour;

        private int minutes;

        public DateBuilder(int year, int month, int day, int hour, int minutes) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minutes = minutes;
        }

        public DateBuilder() {
            this.year = DateUtils.Now.YEAR();
            this.month = DateUtils.Now.MONTH();
            this.day = DateUtils.Now.DAY();
            this.hour = DateUtils.Now.HOUR();
            this.minutes = DateUtils.Now.MINUTES();
        }

        public DateBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public DateBuilder setMonth(int month) {
            this.month = month;
            return this;
        }

        public DateBuilder setDay(int day) {
            this.day = day;
            return this;
        }

        public DateBuilder setHour(int hour) {
            this.hour = hour % 24;
            return this;
        }

        public DateBuilder setMinutes(int minutes) {
            this.minutes = minutes % 60;
            return this;
        }

        public int compare(DateBuilder compared) {
            return this.toCalendar().compareTo(compared.toCalendar());
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public int getHour() {
            return hour;
        }

        public int getMinutes() {
            return minutes;
        }

        public Calendar toCalendar() {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);

            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minutes);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal;
        }

        public String build() {
            return DateUtils.convertToISOString(year, month, day, hour, minutes);
        }
    }


    public static class DateError {

        private int code;

        public DateError() {
            this.code = 0;
        }

        public DateError year() {
            code = Calendar.YEAR;
            return this;
        }

        public DateError month() {
            code = Calendar.MONTH;
            return this;
        }

        public DateError day() {
            code = Calendar.DAY_OF_MONTH;
            return this;
        }

        public DateError hour() {
            code = Calendar.HOUR_OF_DAY;
            return this;
        }

        public DateError minute() {
            code = Calendar.MINUTE;
            return this;
        }

        public boolean isYear() {
            return code == Calendar.YEAR;
        }


        public boolean isMonth() {
            return code == Calendar.MONTH;
        }

        public boolean isDay() {
            return code == Calendar.DAY_OF_MONTH;
        }

        public boolean isHour() {
            return code == Calendar.HOUR_OF_DAY;
        }

        public boolean isMinute() {
            return code == Calendar.MINUTE;
        }
    }

}
