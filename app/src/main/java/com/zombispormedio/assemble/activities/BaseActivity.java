package com.zombispormedio.assemble.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zombispormedio.assemble.AssembleApplication;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.components.ResourceComponent;
import com.zombispormedio.assemble.net.State;
import com.zombispormedio.assemble.services.api.APIConfiguration;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.PreferencesManager;
import com.zombispormedio.assemble.views.activities.IBaseView;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseActivity extends AppCompatActivity implements IBaseView{


    private PreferencesManager preferencesManager;

    public static final String AUTH = "token";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesManager=new PreferencesManager(this);
    }

    public String getAuthToken() {
        return preferencesManager.getString(AUTH);
    }

    @Override
    public void setAuthToken(String token) {
        preferencesManager.set(AUTH, token);
        APIConfiguration.getInstance().setToken(token);
    }

    @Override
    public void clearAuthToken() {
        preferencesManager.remove(AUTH);
        APIConfiguration.getInstance().clearToken();
    }

    public PreferencesManager getPreferencesManager() {
        return preferencesManager;
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

        State.getInstance().setContext(this);

    }

    @Override
    public ResourceComponent getResourceComponent() {
        return ((AssembleApplication)getApplication()).getResourceComponent();
    }


    protected  void setSubtitle(int id) {
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setSubtitle(id);
        }
    }

    protected  void setSubtitle(String subtitle) {
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setSubtitle(subtitle);
        }
    }


    protected  void setTitle(String title) {
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(title);
        }
    }

}
