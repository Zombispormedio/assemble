package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.NewFriendsListAdapter;
import com.zombispormedio.assemble.controllers.NewFriendController;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.holders.INewFriendHolder;
import com.zombispormedio.assemble.views.activities.INewFriendView;

import java.util.ArrayList;

import butterknife.BindView;

public class NewFriendActivity extends BaseActivity implements INewFriendView {

    @BindView(R.id.search_view)
    EditText searchView;

    @BindView(R.id.new_friends_list)
    RecyclerView friendsList;

    private NewFriendController ctrl;

    private NewFriendsListAdapter.Factory friendsListFactory;

    private NewFriendsListAdapter friendsListAdapter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        setupToolbar();
        bindActivity(this);

        ctrl = new NewFriendController(this);

        setupProgressDialog();

        setupList();

        setupSearch();

        ctrl.onCreate();

    }


    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.searching_new_friends));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }


    private void setupSearch() {

        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ctrl.onSearch();
                    hideKeyboard();
                    handled = true;
                }

                return handled;
            }
        });

    }

    private void hideKeyboard() {
        searchView.clearFocus();
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

    }


    private void setupList() {
        friendsListFactory = new NewFriendsListAdapter.Factory();

        AndroidUtils.createListConfiguration(this, friendsList)
                .divider(true)
                .itemAnimation(true)
                .configure();
        friendsListFactory.setOnClickListener(new IOnClickItemListHandler<FriendProfile>() {
            @Override
            public void onClick(int position, FriendProfile data) {
                ctrl.onItemClick(position, data);
            }
        });

        friendsListFactory.setAddFriendListener(new IOnClickComponentItemHandler<FriendProfile, INewFriendHolder>() {
            @Override
            public void onClick(int position, FriendProfile data, INewFriendHolder holder) {
                ctrl.onAddFriendClick(position, data, holder);
            }
        });

        friendsListAdapter = friendsListFactory.make();
        friendsList.setAdapter(friendsListAdapter);
    }

    @Override
    public void bindSearchResults(ArrayList<FriendProfile> results) {
        friendsListAdapter.addAll(results);
        friendsListAdapter.notifyDataSetChanged();
    }

    @Override
    public String getSearchText() {
        return searchView.getText().toString();
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
    public void showFriendRequestSent() {
        showAlert(getString(R.string.friend_request_sent));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
