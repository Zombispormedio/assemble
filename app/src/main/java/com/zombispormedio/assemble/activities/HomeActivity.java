package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.pagers.HomePagerAdapter;
import com.zombispormedio.assemble.controllers.HomeController;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.IHomeView;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.ON_MESSAGE_NOTIFY_HOME;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.SEVERAL_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.SEVERAL_MESSAGE_ACTIVE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.LOADED;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGES;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.READ;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.STATE;

public class HomeActivity extends BaseActivity implements IHomeView {

    private NavigationManager navigation;

    @BindView(R.id.drawer_layout_home)
    DrawerLayout drawer;

    private TextView usernameLabel;

    private TextView emailLabel;

    private ImageView imageView;

    private View headerView;

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


    @BindView(R.id.background_loading)
    ProgressBar backgroundProgressBar;

    private ProgressDialog progressDialog;


    private HomeController ctrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        setHomeUpIcon(R.drawable.menu_bar);
        bindActivity(this);

        setupHomeController();
        navigation = new NavigationManager(this);

        setupProgressDialog();

        setupDrawer();

        overlay.setVisibility(View.GONE);

        setupTabs();

        ctrl.onCreate();

    }


    private void setupHomeController() {
        Intent intent = getIntent();
        String action = intent.getAction();
        Bundle extra = intent.getExtras();
        if (SEVERAL_MESSAGE_ACTION.equals(action)) {
            ArrayList<Message> messages = extra.getParcelableArrayList(MESSAGES);
            ctrl = new HomeController(this, messages);
            setState(HomePagerAdapter.CHATS);
        } else {
            ctrl = new HomeController(this);
            if (SEVERAL_MESSAGE_ACTIVE_ACTION.equals(action)) {
                setState(HomePagerAdapter.CHATS);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
        navigation.onDestroy();
    }

    private void setupDrawer() {
        nav.setNavigationItemSelectedListener(NavListener());
        drawer.addDrawerListener(
                new ActionBarDrawerToggle(this, drawer, R.string.open_drawer_desc, R.string.close_drawer_desc));
        headerView = nav.getHeaderView(0);

        usernameLabel = (TextView) headerView.findViewById(R.id.username_label);

        emailLabel = (TextView) headerView.findViewById(R.id.email_label);

        imageView = (ImageView) headerView.findViewById(R.id.image_view);
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
        int position = getState();

        viewTabPager.setCurrentItem(position > -1 ? position : 1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {
                int position = tab.getPosition();
                moveToPosition(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void moveToPosition(int position) {
        viewTabPager.setCurrentItem(position);
        setState(position);
    }

    private void setState(int s) {
        getPreferencesManager().set(STATE, String.valueOf(s));
    }

    private int getState() {
        String value = getPreferencesManager().getString(STATE);
        return (value != null && value.isEmpty()) ? -1 : Integer.parseInt(value);
    }


    private NavigationView.OnNavigationItemSelectedListener NavListener() {
        return item -> {
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
        };
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void bindUsernameLabel(String username) {
        usernameLabel.setText(username);
    }

    @Override
    public void bindEmailLabel(String email) {
        emailLabel.setText(email);
    }

    @Override
    public void bindAvatar(String path, String letter) {
        new ImageUtils.ImageBuilder(headerView.getContext(), imageView)
                .url(path)
                .letter(letter)
                .circle(true)
                .build();
    }

    @Override
    public void showBackgroundLoading() {
        backgroundProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBackgroundLoading() {
        backgroundProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean isLoaded() {
        return getPreferencesManager().getInt(LOADED) == 1;
    }

    @Override
    public void notifyLoaded() {
        getPreferencesManager().set(LOADED, 1);
    }


    @OnTouch(R.id.alpha_layer)
    public boolean onAlphaLayerClick() {
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
        navigation.FirstStepCreateTeam();
    }


    @Override
    protected void setupReceivers() {
        super.setupReceivers();
        configureReceiver(new MessageReceiver(), ON_MESSAGE_NOTIFY_HOME);
    }


    private class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, @NonNull Intent intent) {
            Bundle extras = intent.getExtras();
            getResourceComponent().provideChatSubscription().haveOneChanged(extras.getInt(CHAT_ID));
            if (getState() != HomePagerAdapter.CHATS && !extras.getBoolean(READ)) {
                moveToPosition(HomePagerAdapter.CHATS);
            }
        }
    }
}
