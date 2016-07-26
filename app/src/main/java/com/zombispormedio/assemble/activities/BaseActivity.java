package com.zombispormedio.assemble.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.zombispormedio.assemble.services.api.APIConfiguration;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IBaseView;

public class BaseActivity extends AppCompatActivity implements IBaseView {
    public static final String AUTH_PREFERENCES = "AuthPrefs";

    public SharedPreferences getAuthPreferences(){
        return getSharedPreferences(AUTH_PREFERENCES,Context.MODE_PRIVATE );
    }
    public String getAuthToken(){
        return  getAuthPreferences().getString("token", "");
    }

    @Override
    public void setAuthToken(String token) {

        SharedPreferences settings = getAuthPreferences();
        SharedPreferences.Editor editor= settings.edit();

        editor.putString("token", token);
        editor.commit();

        APIConfiguration.getInstance().setToken(token);

    }

    @Override
    public void clearAuthToken() {
        SharedPreferences settings = getAuthPreferences();
        SharedPreferences.Editor editor= settings.edit();

        editor.remove("token");
        editor.commit();

        APIConfiguration.getInstance().clearToken();
    }

    public void showAlert(String msg){
        AndroidUtils.showAlert(this, msg);
    }


}
