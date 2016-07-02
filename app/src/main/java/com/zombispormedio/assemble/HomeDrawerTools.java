package com.zombispormedio.assemble;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Master on 02/07/2016.
 */
public class HomeDrawerTools {
    private Context ctx;
    private Activity view;
    private DrawerLayout drawer;
    private NavigationView nav;
    private FirebaseAuth mAuth;

    public HomeDrawerTools(Context ctx,DrawerLayout drawer,NavigationView nav ){
        this.ctx=ctx;
        view=(Activity)ctx;
        this.drawer=drawer;
        this.nav=nav;
        mAuth= FirebaseAuth.getInstance();
        init();

    }

    private void init(){
        nav.setNavigationItemSelectedListener(navListener());
        drawer.addDrawerListener(DrawerListener());


    }

    private NavigationView.OnNavigationItemSelectedListener navListener(){
        return new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch(item.getItemId()){
                    case R.id.signout_menu:
                        mAuth.signOut();
                        Navigation.Login(ctx);
                        break;
                }
                drawer.closeDrawers();
                return true;
            }
        };
    }

    private ActionBarDrawerToggle DrawerListener(){
        return new ActionBarDrawerToggle(view, drawer, R.string.open_drawer_desc, R.string.close_drawer_desc){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                TextView nav_title=(TextView)view.findViewById(R.id.nav_title);

                nav_title.setText(mAuth.getCurrentUser().getEmail());

            }
        };
    }


}
