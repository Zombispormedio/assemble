package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.net.ConnectionState;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.views.activities.INewFriendView;
import com.zombispormedio.assemble.views.holders.INewFriendHolder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendController extends Controller {

    @Nullable
    private INewFriendView ctx;

    private final FriendResource friendResource;

    public NewFriendController(INewFriendView ctx) {
        super(ctx);
        this.ctx = ctx;
        friendResource = getResourceComponent().provideFriendResource();
    }


    public void onSearch() {
        String searchText = ctx.getSearchText();

        if (searchText.isEmpty() || !ConnectionState.getInstance().isConnected()) {
            return;
        }

        ctx.showProgress();
        friendResource.searchNewFriends(searchText, new ServiceHandler<ArrayList<FriendProfile>, Error>() {
            @Override
            public void onError(@NonNull Error error) {
                ctx.hideProgress();
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(ArrayList<FriendProfile> result) {
                ctx.bindSearchResults(result);
                ctx.hideProgress();
            }
        });
    }

    public void onItemClick(int position, FriendProfile data) {
        Logger.d(position);
        Logger.d(data);
    }

    public void onAddFriendClick(int position, @NonNull FriendProfile data, @NonNull final INewFriendHolder holder) {
        holder.setFriendChecked(true);
        friendResource.requestNewFriend(data.id, new ServiceHandler<Result, Error>() {
            @Override
            public void onError(@NonNull Error error) {
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(Result result) {
                ctx.showFriendRequestSent();
            }

        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
