package com.zombispormedio.assemble.activities;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;

import com.zombispormedio.assemble.controllers.MainController;
import com.zombispormedio.assemble.utils.NavigationTools;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IMainView;


public class MainActivity extends AppCompatActivity implements IMainView {

    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthListener;

    private MainController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        ctrl=new MainController(this);


    }

    public void goToLogin(){
        NavigationTools.Login(this);
        finish();
    }

    public void goHome(){
        NavigationTools.Home(this);
        finish();
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
