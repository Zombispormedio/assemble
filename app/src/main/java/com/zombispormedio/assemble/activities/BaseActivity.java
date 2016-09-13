package com.zombispormedio.assemble.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.services.api.APIConfiguration;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IBaseView;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseActivity extends AppCompatActivity implements IBaseView {

    public static final String AUTH_PREFERENCES = "AuthPrefs";

    public SharedPreferences getAuthPreferences() {
        return getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
    }

    public String getAuthToken() {
        return getAuthPreferences().getString("token", "");
    }

    @Override
    public void setAuthToken(String token) {

        SharedPreferences settings = getAuthPreferences();
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("token", token);
        editor.apply();

        APIConfiguration.getInstance().setToken(token);

    }

    @Override
    public void clearAuthToken() {
        SharedPreferences settings = getAuthPreferences();
        SharedPreferences.Editor editor = settings.edit();

        editor.remove("token");
        editor.apply();

        APIConfiguration.getInstance().clearToken();
    }

    public void showAlert(String msg) {
        AndroidUtils.showAlert(this, msg);
    }


    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void setHomeUpIcon(int id) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(id);

        }
    }

    protected void bindActivity(Activity target){
        ButterKnife.bind(target);
        RealmConfiguration realmConfiguration= new RealmConfiguration.Builder(target)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        LocalStorage.Configuration
                .getInstance()
                .setDatabase(Realm.getDefaultInstance());

    }




}
