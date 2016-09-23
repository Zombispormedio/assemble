package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.zombispormedio.assemble.adapters.pagers.HomePagerAdapter;
import com.zombispormedio.assemble.controllers.HomeController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.views.IHomeView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

public class HomeActivity extends BaseActivity implements IHomeView {

    private NavigationManager navigation;

    @BindView(R.id.drawer_layout_home)
    DrawerLayout drawer;

    private TextView navTitle;

    @BindView(R.id.nav_view)
    NavigationView nav;

    @BindView(R.id.overlay_home_layout)
    FrameLayout overlay;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.home_tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.home_pager)
    ViewPager viewTabPager;

    private ProgressDialog progressDialog;

    private HomeController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        setHomeUpIcon(R.drawable.menu_bar);
        bindActivity(this);

        ctrl = new HomeController(this);
        navigation = new NavigationManager(this);

        setupProgressDialog();

        nav.setNavigationItemSelectedListener(NavListener());
        drawer.addDrawerListener(DrawerListener());

        overlay.setVisibility(View.GONE);

        setupTabs();

        ctrl.onCreate();

    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.loading_app_data));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    private void setupTabs() {

        tabLayout.addTab(tabLayout.newTab().setText(R.string.teams_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.gatherings_tab_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.messages_tab_title));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final HomePagerAdapter adapterTabPager = new HomePagerAdapter(getSupportFragmentManager(),
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
        progressDialog.dismiss();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void showOverlay() {
        fab.setVisibility(View.GONE);
        overlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOverlay() {
        overlay.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
    }


    @OnTouch(R.id.alpha_layer)
    public boolean onAlphaLayerClick(View view, MotionEvent motionEvent) {
        hideOverlay();
        return false;
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        showOverlay();
    }

    @OnClick(R.id.meeting_fab)
    public void onMeetingFabClick(View view) {
        hideOverlay();
        navigation.CreateMeeting();
    }

    @OnClick(R.id.chat_fab)
    public void onChatFabClick(View view) {
        hideOverlay();
        navigation.CreateChat();
    }

    @OnClick(R.id.team_fab)
    public void onTeamFabClick(View view) {
        hideOverlay();
        navigation.CreateTeam();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
