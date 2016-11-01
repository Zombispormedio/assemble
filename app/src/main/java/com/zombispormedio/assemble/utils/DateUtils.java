package com.zombispormedio.assemble.utils;

import android.support.annotation.NonNull;

import java.util.Calendar;


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

        @NonNull
        public DateError year() {
            code = Calendar.YEAR;
            return this;
        }

        @NonNull
        public DateError month() {
            code = Calendar.MONTH;
            return this;
        }

        @NonNull
        public DateError day() {
            code = Calendar.DAY_OF_MONTH;
            return this;
        }

        @NonNull
        public DateError hour() {
            code = Calendar.HOUR_OF_DAY;
            return this;
        }

        @NonNull
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
