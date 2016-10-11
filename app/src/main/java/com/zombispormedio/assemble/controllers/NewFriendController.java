package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.net.Result;
import com.zombispormedio.assemble.views.holders.INewFriendHolder;
import com.zombispormedio.assemble.views.activities.INewFriendView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendController extends Controller {

    private INewFriendView ctx;

    private FriendResource friendResource;

    public NewFriendController(INewFriendView ctx) {
        super(ctx);
        this.ctx = ctx;
        friendResource=getResourceComponent().provideFriendResource();
    }


    public void onSearch() {
        String searchText=ctx.getSearchText();

        if(searchText.isEmpty()){
           return;
        }

        ctx.showProgress();
        friendResource.searchNewFriends(searchText, new ServiceHandler<ArrayList<FriendProfile>, Error>(){
            @Override
            public void onError(Error error) {
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

    public void onAddFriendClick(int position, FriendProfile data, final INewFriendHolder holder) {
        holder.showProgress();

        friendResource.requestNewFriend(data.id, new ServiceHandler<Result, Error>() {
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
            }

            @Override
            public void onSuccess(Result result) {
                ctx.showFriendRequestSent();
                holder.hideProgress();
                holder.setFriendChecked(true);
            }

        });

    }

    @Override
    public void onDestroy() {
        ctx=null;
    }
}
