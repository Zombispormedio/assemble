package com.zombispormedio.assemble.activities;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.zombispormedio.assemble.adapters.HomeTabPagerAdapter;
import com.zombispormedio.assemble.controllers.HomeController;
import com.zombispormedio.assemble.utils.NavigationUtils;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IHomeView;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private HomeController ctrl;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView nav;
    private TextView nav_title;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ctrl=new HomeController(this);

        drawer= (DrawerLayout)findViewById(R.id.drawer_layout_home);
        nav=(NavigationView)findViewById(R.id.navview);
        toolbar =(Toolbar) findViewById(R.id.home_bar);
        
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_bar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        nav.setNavigationItemSelectedListener(NavListener());
        drawer.addDrawerListener(DrawerListener());


        tabLayout=(TabLayout)findViewById(R.id.home_tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText(R.string.teams_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.gatherings_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.messages_tab_title));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewTabPager= (ViewPager) findViewById(R.id.home_pager);

        final HomeTabPagerAdapter adapterTabPager = new HomeTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewTabPager.setAdapter(adapterTabPager);

        viewTabPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewTabPager.setCurrentItem(1);

        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener(){

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
                        ctrl.onSettingsMenuItemClick();
                        break;

                    case R.id.profile_menu:
                        ctrl.onProfileMenuItemClick();
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

    @Override
    public void goToLogin() {
        NavigationUtils.Login(this);
        finish();
    }

    @Override
    public void goToProfile() {
        NavigationUtils.Profile(this);
    }

    @Override
    public void goToSettings() {
        NavigationUtils.Settings(this);
    }

    @Override
    public void setNavTitleText(String text) {
        if(nav_title==null)nav_title=(TextView) findViewById(R.id.nav_title);
        if(nav_title!=null)nav_title.setText(text);
    }


}
