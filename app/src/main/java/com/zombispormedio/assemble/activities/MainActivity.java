package com.zombispormedio.assemble.activities;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.Window;


import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.controllers.MainController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IMainView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends BaseActivity implements IMainView {
    private final CountDownLatch timeoutLatch = new CountDownLatch(1);

    private NavigationManager navigation;
    private MainController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        ctrl=new MainController(this);

        navigation= new NavigationManager(this);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){}

                timeoutLatch.countDown();

            }
        });

        thread.start();
        goAfterSplashTimeout();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Touch event bypasses waiting for the splash timeout to expire.
        timeoutLatch.countDown();
        return true;
    }

    private void goAfterSplashTimeout(){

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timeoutLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        thread.start();
    }

    private void Login(){
        navigation.Login();
        finish();
    }

    private void Home(){
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
    protected void onStart() {
        super.onStart();
        ctrl.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
       ctrl.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();

    }





}
