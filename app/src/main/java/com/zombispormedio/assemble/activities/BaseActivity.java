package com.zombispormedio.assemble.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.zombispormedio.assemble.views.IBaseView;

public class BaseActivity extends AppCompatActivity implements IBaseView {

    public String getAuthToken(){
        return  getPreferences(Context.MODE_PRIVATE).getString("token", "");
    }

    @Override
    public void setAuthToken() {

    }
}
