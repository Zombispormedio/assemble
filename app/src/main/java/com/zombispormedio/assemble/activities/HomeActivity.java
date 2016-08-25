package com.zombispormedio.assemble.activities;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.zombispormedio.assemble.adapters.HomeTabPagerAdapter;
import com.zombispormedio.assemble.controllers.HomeController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IHomeView;

public class HomeActivity extends BaseActivity implements IHomeView {

    private HomeController ctrl;
    private NavigationManager navigation;
    private DrawerLayout drawer;
    private TextView nav_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ctrl=new HomeController(this);
        navigation=new NavigationManager(this);

        drawer= (DrawerLayout)findViewById(R.id.drawer_layout_home);
        NavigationView nav = (NavigationView) findViewById(R.id.navview);

        setupToolbar();

        setHomeUpIcon(R.drawable.menu_bar);


        nav.setNavigationItemSelectedListener(NavListener());
        drawer.addDrawerListener(DrawerListener());


        TabLayout tabLayout = (TabLayout) findViewById(R.id.home_tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText(R.string.teams_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.gatherings_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.messages_tab_title));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewTabPager= (ViewPager) findViewById(R.id.home_pager);

        final HomeTabPagerAdapter adapterTabPager = new HomeTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewTabPager.setAdapter(adapterTabPager);

        viewTabPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewTabPager.setCurrentItem(1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewTabPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private NavigationView.OnNavigationItemSelectedListener NavListener(){
        return new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch(item.getItemId()){
                    case R.id.settings_menu:
                        ctrl.onSettingsMenuItem();
                        break;

                    case R.id.profile_menu:
                        ctrl.onProfileMenuItem();
                        break;

                    case R.id.help_menu:
                        ctrl.onHelpMenuItem();
                        break;

                    case R.id.friends_menu:
                        ctrl.onFriendsMenuItem();
                        break;

                }
                item.setChecked(false);
                drawer.closeDrawers();
                return true;
            }
        };
    }

    private ActionBarDrawerToggle DrawerListener(){
        return new ActionBarDrawerToggle(this, drawer, R.string.open_drawer_desc, R.string.close_drawer_desc){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ctrl.onDrawerOpened();
            }
        };
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    @Override
    public void goToLogin() {
        navigation.Login();
        finish();
    }

    @Override
    public void goToProfile() {
        navigation.Profile();
    }

    @Override
    public void goToSettings() {
        navigation.Settings();
    }

    @Override
    public void goToFriends() {
       navigation.Friends();
    }

    @Override
    public void goToHelp() {
        navigation.Help();
    }

    @Override
    public void setDrawerTitle(String text) {
        if(nav_title==null){
            nav_title=(TextView) findViewById(R.id.nav_title);
        }
        if(nav_title!=null){
            nav_title.setText(text);
        }
    }


}
