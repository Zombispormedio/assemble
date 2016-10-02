package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.PreviewFriendsListAdapter;
import com.zombispormedio.assemble.controllers.CreateChatController;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.ICreateChatView;

import java.util.ArrayList;

import butterknife.BindView;

public class CreateChatActivity extends BaseActivity implements ICreateChatView {

    private CreateChatController ctrl;

    @BindView(R.id.friends_list)
    RecyclerView friendsList;

    private ProgressDialog progressDialog;

    private PreviewFriendsListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat);
        setupToolbar();
        bindActivity(this);

        ctrl=new CreateChatController(this);

        setupFriends();

        setupProgressDialog();

        ctrl.onCreate();
    }

    private void setupFriends() {
        adapter=new PreviewFriendsListAdapter();
        AndroidUtils.createListConfiguration(this, friendsList)
                .divider(true)
                .itemAnimation(true)
                .configure();

        adapter.setOnClickListener(new IOnClickItemListHandler<FriendProfile>() {
            @Override
            public void onClick(int position, FriendProfile data) {
                ctrl.onFriend(data);
            }
        });

        friendsList.setAdapter(adapter);

    }


    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.creating_chat));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    @Override
    public void bindFriends(ArrayList<FriendProfile> friends) {
        adapter.setData(friends);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void goHome() {
        NavigationManager.Home(this);
    }
}
