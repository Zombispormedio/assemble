package com.zombispormedio.assemble.utils;

import com.orhanobut.logger.Logger;

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

    public final static String SIMPLE_SLASH_FORMAT_WITH_HOUR = "dd/MM/yyyy HH:mm";

    public static String format(String format, String inDate) throws ParseException {
        DateFormat inFormat = new SimpleDateFormat(DEFAULT_INPUT_FORMAT, Locale.ENGLISH);
        Date outDate = inFormat.parse(inDate);
        DateFormat outFormat = new SimpleDateFormat(format, Locale.getDefault());
        return outFormat.format(outDate);
    }

    public static String format(String inFormatStr, String outFormatStr, String inDate) throws ParseException {
        DateFormat inFormat = new SimpleDateFormat(inFormatStr, Locale.ENGLISH);
        Date outDate = inFormat.parse(inDate);
        DateFormat outFormat = new SimpleDateFormat(outFormatStr, Locale.getDefault());
        return outFormat.format(outDate);
    }

    public static long appendYearsToNow(int operator) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, operator);
        return date.getTimeInMillis();
    }

    public static String toString(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        DateFormat formatUtility = new SimpleDateFormat(DEFAULT_INPUT_FORMAT, Locale.ENGLISH);

        return formatUtility.format(cal.getTime());
    }

    public static String toString(int year, int month, int day, int hour, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, 0);
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

    public static boolean isToday(String date){
        boolean is=false;

        try {
            Calendar cal=parse(date);

            is=cal.get(Calendar.YEAR)==Now.YEAR() && cal.get(Calendar.MONTH)==Now.YEAR() && cal.get(Calendar.DAY_OF_MONTH)==Now.DAY();

        } catch (ParseException e) {
            Logger.d(e.getMessage());
        }

        return is;
    }

    public static boolean isYesterday(String date){
        boolean is=false;

        try {
            Calendar cal=parse(date);

            is=cal.get(Calendar.YEAR)==Now.YEAR() && cal.get(Calendar.MONTH)==Now.YEAR() && cal.get(Calendar.DAY_OF_MONTH)==Now.DAY()-1;

        } catch (ParseException e) {
            Logger.d(e.getMessage());
        }

        return is;
    }




    public static class Now {

        private static Calendar Cal() {
            return Calendar.getInstance();
        }

        public static int YEAR(){
            return Cal().get(Calendar.YEAR);
        }

        public static int MONTH(){
          return   Cal().get(Calendar.MONTH);
        };

        public static int DAY(){
           return Cal().get(Calendar.DAY_OF_MONTH);
        }

        public static  int HOUR(){
            return Cal().get(Calendar.HOUR_OF_DAY);
        }

        public static int MINUTES(){
            return Cal().get(Calendar.MINUTE);
        }

        public static String toDateString(){
          return  DateUtils.toString(YEAR(), MONTH(), DAY(), HOUR(), MINUTES());
        }
    }


    public static class DateBuilder{
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
            this.hour = hour%24;
            return this;
        }

        public DateBuilder setMinutes(int minutes) {
            this.minutes = minutes%60;
            return this;
        }

        public int compare(DateBuilder compared){
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

        public Calendar toCalendar(){
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

        public String build(){
            return DateUtils.toString(year, month, day, hour, minutes);
        }
    }


    public static class DateError{
        private int code;

        public DateError() {
            this.code = 0;
        }

        public DateError year(){
            code=Calendar.YEAR;
            return this;
        }

        public DateError month(){
            code=Calendar.MONTH;
            return this;
        }

        public DateError day(){
            code=Calendar.DAY_OF_MONTH;
            return this;
        }

        public DateError hour(){
            code=Calendar.HOUR_OF_DAY;
            return this;
        }

        public DateError minute(){
            code=Calendar.MINUTE;
            return this;
        }

        public boolean isYear(){
           return code==Calendar.YEAR;
        }


        public boolean isMonth(){
            return code==Calendar.MONTH;
        }

        public boolean isDay(){
            return code==Calendar.DAY_OF_MONTH;
        }

        public boolean isHour(){
            return code==Calendar.HOUR_OF_DAY;
        }

        public boolean isMinute(){
            return code==Calendar.MINUTE;
        }
    }

}
