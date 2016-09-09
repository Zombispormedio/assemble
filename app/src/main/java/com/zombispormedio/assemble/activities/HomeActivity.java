package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.zombispormedio.assemble.adapters.HomeViewPagerAdapter;
import com.zombispormedio.assemble.controllers.HomeController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IHomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements IHomeView {

    private NavigationManager navigation;

    @BindView(R.id.drawer_layout_home)
    DrawerLayout drawer;

    private TextView navTitle;

    @BindView(R.id.nav_view)
    NavigationView nav;

    private ProgressDialog _progressDialog;

    private HomeController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbar();
        setHomeUpIcon(R.drawable.menu_bar);

        ButterKnife.bind(this);

        ctrl = new HomeController(this);
        navigation = new NavigationManager(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);

        _progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        _progressDialog.setMessage(getString(R.string.loading_app_data));
        _progressDialog.setIndeterminate(true);
        _progressDialog.setCancelable(false);

        nav.setNavigationItemSelectedListener(NavListener());
        drawer.addDrawerListener(DrawerListener());

        setupTabs();

        ctrl.onCreate();

    }

    private void setupTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.home_tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.teams_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.gatherings_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.messages_tab_title));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewTabPager = (ViewPager) findViewById(R.id.home_pager);

        final HomeViewPagerAdapter adapterTabPager = new HomeViewPagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewTabPager.setAdapter(adapterTabPager);

        viewTabPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewTabPager.setCurrentItem(1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

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


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private NavigationView.OnNavigationItemSelectedListener NavListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
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

    private ActionBarDrawerToggle DrawerListener() {
        return new ActionBarDrawerToggle(this, drawer, R.string.open_drawer_desc, R.string.close_drawer_desc) {
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
        if (navTitle == null) {
            navTitle = (TextView) findViewById(R.id.nav_title);
        }
        if (navTitle != null) {
            navTitle.setText(text);
        }
    }

    @Override
    public void hideProgressDialog() {
        _progressDialog.dismiss();
    }

    @Override
    public void showProgressDialog() {
        _progressDialog.show();
    }


}
