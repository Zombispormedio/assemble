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
