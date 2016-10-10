package com.zombispormedio.assemble.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Xavier Serrano on 07/10/2016.
 */

public class PreferencesManager {

    public static final String PREFERENCES = "Prefs";

    private Context ctx;

    public PreferencesManager(Context ctx) {
        this.ctx = ctx;
    }

    public SharedPreferences getPreferences() {
        return ctx.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor(){
        return getPreferences().edit();
    }

    public void set(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.apply();
    }

    public void set(String key, int value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString( String key){
        return getPreferences().getString(key, "");
    }

    public int getInt(String key){
        return getPreferences().getInt(key, 0);
    }

    public void remove(String key){
        SharedPreferences.Editor editor = getEditor();
        editor.remove(key);
        editor.apply();
    }

}
