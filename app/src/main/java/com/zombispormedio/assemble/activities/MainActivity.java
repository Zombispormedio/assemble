package com.zombispormedio.assemble.activities;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.zombispormedio.assemble.FirebaseTools;
import com.zombispormedio.assemble.utils.NavigationAdapter;
import com.zombispormedio.assemble.R;


public class MainActivity extends AppCompatActivity implements IMainView{

    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener= FirebaseTools.checkAccess(this);



    }

    private void goToLogin(){
        NavigationAdapter.Login(this);
    }

    private void goHome(){
        NavigationAdapter.Home(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void Hello() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}