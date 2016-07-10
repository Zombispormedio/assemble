package com.zombispormedio.assemble.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.zombispormedio.assemble.FirebaseTools;
import com.zombispormedio.assemble.HomeDrawerTools;
import com.zombispormedio.assemble.controllers.HomeController;
import com.zombispormedio.assemble.utils.NavigationTools;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IHomeView;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private HomeController ctrl;

    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    private Toolbar homebar;
    private DrawerLayout drawer;
    private NavigationView nav;
    private HomeDrawerTools drawerTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener= FirebaseTools.checkAccess(this, NavigationTools.Type.HOME);
        drawer= (DrawerLayout)findViewById(R.id.drawer_layout_home);
        nav=(NavigationView)findViewById(R.id.navview);
        drawerTools=new HomeDrawerTools(this, drawer,nav);

        homebar=(Toolbar) findViewById(R.id.home_bar);
        setSupportActionBar(homebar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.account_circle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ctrl=new HomeController(this);


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
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

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
