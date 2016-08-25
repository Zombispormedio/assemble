package com.zombispormedio.assemble.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;


import android.view.Window;


import com.zombispormedio.assemble.controllers.MainController;

import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IMainView;


public class MainActivity extends BaseActivity implements IMainView {


    private NavigationManager navigation;

    private MainController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ctrl = new MainController(this);

        navigation = new NavigationManager(this);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        ctrl.checkAccess();
                    }
                }, 2000);


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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Login();
            }
        });
    }

    @Override
    public void goHome() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Home();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }

}
