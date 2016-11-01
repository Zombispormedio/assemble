package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.MainController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.IMainView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;


public class MainActivity extends BaseActivity implements IMainView {


    private NavigationManager navigation;

    private MainController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bindActivity(this);

        ctrl = new MainController(this);

        navigation = new NavigationManager(this);

        new android.os.Handler().postDelayed(() -> ctrl.checkAccess(), 500);

    }


    private void Login() {
        navigation.Login();
        finish();
    }

    private void Home() {
        navigation.Home();
        finish();
    }

    @Override
    public void goToLogin() {
        runOnUiThread(this::Login);
    }

    @Override
    public void goHome() {
        runOnUiThread(this::Home);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }

}
