package com.zombispormedio.assemble.utils;


import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 25/07/2016.
 */
public class StringUtils {

    @NonNull
    public static String join(String delimiter, @NonNull Object[] in) {
        String out = "";

        if (in.length > 0) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(in[0]);

            for (int i = 1; i < in.length; i++) {
                buffer.append(delimiter);
                buffer.append(in[i]);
            }

            out = buffer.toString();
        }

        return out;
    }

    @NonNull
    public static String capitalize(@NonNull final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1).toLowerCase();
    }

    @NonNull
    public static String firstLetter(@NonNull String word) {
        return !word.isEmpty() ? String.valueOf(word.charAt(0)) : "X";
    }

    @NonNull
    public static String ellipse(@NonNull String str, int limit) {
        return str.substring(0, limit) + "…";
    }
}
