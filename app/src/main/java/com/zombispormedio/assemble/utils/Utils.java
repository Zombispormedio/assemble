package com.zombispormedio.assemble.utils;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.annimon.stream.IntStream;
import com.annimon.stream.Stream;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * Created by Xavier Serrano on 31/07/2016.
 */
public class Utils {

    public static boolean presenceOf(@Nullable Object obj) {
        return obj != null;
    }

    public static boolean presenceOf(@Nullable String obj) {
        boolean it_is = true;

        if (obj == null) {
            it_is = false;
        } else {
            if (obj.isEmpty()) {
                it_is = false;
            }
        }
        return it_is;
    }

    public static boolean safeEquals(@Nullable Object o1, Object o2) {
        boolean equals = o1 != null;
        if (equals) {
            equals = o1.equals(o2);
        }
        return equals;
    }


    public static int getColorByString(@NonNull String str) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        return generator.getColor(str.hashCode());

    }

    public static int[] toArray(@NonNull LinkedList<Integer> in) {
        return Stream.of(in)
                .mapToInt(i -> i)
                .toArray();
    }


    public static Integer[] toInteger(int[] in) {
        return IntStream.of(in)
                .boxed()
                .toArray(Integer[]::new);
    }

    public static class Pair<X, Y> {

        public final X first;

        public final Y second;

        public Pair(X first, Y second) {
            this.first = first;
            this.second = second;
        }
    }


    public static HashMap<String, String> convertJSONObjectToHashMap(@NonNull JSONObject object) {
        return Stream.of(object.keys())
                .reduce(new HashMap<String, String>(), (memo, key) -> {
                    try {
                        memo.put(key, String.valueOf(object.get(key)));
                    } catch (JSONException e) {
                        Logger.d(e);
                    }
                    return memo;
                });
    }

    public static String validateJSONValue(String key, @NonNull JSONObject jsonObject) {
        String value = "0";
        try {
            Object raw = jsonObject.get(key);
            value = String.valueOf(raw);
        } catch (JSONException e) {
            Logger.d(e.getMessage());
        }

        return value;
    }

    public static int safeGetValue(String key, @NonNull JSONObject jsonObject) {
        int value = 0;
        try {
            value = jsonObject.getInt(key);
        } catch (JSONException e) {
            Logger.d(e.getMessage());
        }

        return value;
    }


}
