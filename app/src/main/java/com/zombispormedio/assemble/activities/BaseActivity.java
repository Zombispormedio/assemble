package com.zombispormedio.assemble.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zombispormedio.assemble.views.IAuthView;

public class BaseActivity extends AppCompatActivity implements IAuthView {

    public String getAuthToken(){
        return  getPreferences(Context.MODE_PRIVATE).getString("token", "");
    }

    @Override
    public void setAuthToken() {

    }
}
