package com.zombispormedio.assemble.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Xavier Serrano on 20/10/2016.
 */

public class ISODate implements Comparable<ISODate> {

    private final Calendar cal;

    public ISODate(String iso) {
        cal = parse(iso);
    }

    public ISODate(Calendar cal) {
        this.cal = cal;
    }

    public ISODate(int year, int month, int day) {
        cal = Calendar.getInstance();
        setYear(year);
        setMonth(month);
        setDayOfMonth(day);
        setHour(0);
        setMinutes(0);
        setSeconds(0);
        setMilliseconds(0);
    }

    public ISODate(int year, int month, int day, int hour, int minutes) {
        cal = Calendar.getInstance();
        setYear(year);
        setMonth(month);
        setDayOfMonth(day);
        setHour(hour);
        setMinutes(minutes);
        setSeconds(0);
        setMilliseconds(0);
    }

    public boolean isYesterday() {
        ISODate yesterday = Yesterday();
        return getYear() == yesterday.getYear() && getMonth() == yesterday.getMonth() &&
                getDayOfYear() == yesterday.getDayOfYear();
    }

    public boolean isToday() {
        ISODate now = Now();
        return getYear() == now.getYear() && getMonth() == now.getMonth()
                && getDayOfMonth() == now.getDayOfMonth();
    }

    public boolean beforeDay(ISODate d2){
        return getDayOfMonth()>d2.getDayOfMonth();
    }

    public boolean beforeMonth(ISODate d2){
        return getMonth() > d2.getMonth();
    }

    public boolean beforeYear(ISODate d2){
        return getYear()>d2.getYear();
    }

    public void setYear(int y) {
        cal.set(Calendar.YEAR, y);
    }

    public void setMonth(int m) {
        cal.set(Calendar.MONTH, m);
    }

    public void setDayOfMonth(int d) {
        cal.set(Calendar.DAY_OF_MONTH, d);
    }


    public void setHour(int h) {
        cal.set(Calendar.HOUR_OF_DAY, h);
    }

    public void setMinutes(int m) {
        cal.set(Calendar.MINUTE, m);
    }

    public void setSeconds(int s) {
        cal.set(Calendar.SECOND, s);
    }

    public void setMilliseconds(int m) {
        cal.set(Calendar.MILLISECOND, m);
    }


    public int getYear() {
        return cal.get(Calendar.YEAR);
    }

    public int getMonth() {
        return cal.get(Calendar.MONTH);
    }

    public int getDayOfMonth() {
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfYear() {
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public int getHour() {
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinutes() {
        return cal.get(Calendar.MINUTE);
    }

    public int getSeconds() {
        return cal.get(Calendar.SECOND);
    }

    public int getMilliseconds() {
        return cal.get(Calendar.MILLISECOND);
    }

    public Calendar getCalendar() {
        return cal;
    }


    private Calendar parse(String inDate) {
        DateTime d = ISODateTimeFormat.dateTime().parseDateTime(inDate);
        Calendar outDate = Calendar.getInstance();
        outDate.setTimeInMillis(d.getMillis());
        return outDate;
    }

    public static ISODate Now() {
        return new ISODate(Calendar.getInstance());
    }

    public static ISODate Yesterday() {
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR, -1);
        return new ISODate(c1);
    }

    public String format(String format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format).withLocale(Locale.getDefault());
        return formatter.print(cal.getTimeInMillis());
    }

    public void appendYears(int o){
        cal.add(Calendar.YEAR, o);
    }

    public long getTimeInMilliseconds(){
        return cal.getTimeInMillis();
    }

    @Override
    public String toString() {
        DateTimeFormatter format = ISODateTimeFormat.dateTime();
        return format.print(cal.getTimeInMillis());
    }

    @Override
    public int compareTo(@NonNull ISODate isoDate) {
        return cal.compareTo(isoDate.getCalendar());
    }
}
