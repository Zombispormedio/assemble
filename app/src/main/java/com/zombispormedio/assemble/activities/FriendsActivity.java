package com.zombispormedio.assemble.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.FriendsController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IFriendsView;

public class FriendsActivity extends BaseActivity implements IFriendsView{

    private FriendsController ctrl;
    private RecyclerView listFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        setupToolbar();

        ctrl= new FriendsController(this);

        listFriends=(RecyclerView) findViewById(R.id.rec_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrl.onNewFriend();
            }
        });

        setupFriends();
    }

    private void setupFriends() {

    }


    @Override
    public void goToNewFriend() {
        NavigationManager.NewFriend(this);
    }
}
