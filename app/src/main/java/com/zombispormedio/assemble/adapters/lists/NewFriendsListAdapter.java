package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.NewFriendHolder;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.holders.INewFriendHolder;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendsListAdapter extends BaseListAdapter<FriendProfile, NewFriendHolder> {

    private IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener;

    private IOnClickItemListHandler<FriendProfile> listener;

    public NewFriendsListAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }


    @Override
    public NewFriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewFriendHolder holder = new NewFriendHolder(getView(parent,R.layout.list_item_new_friends));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }

        holder.setAddFriendListener(addFriendListener);

        return holder;
    }

    public void setAddFriendListener(
            IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener) {
        this.addFriendListener = addFriendListener;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }

    public static class Factory  {

        private IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener;

        private IOnClickItemListHandler<FriendProfile> listener;

        public Factory() {
            addFriendListener=null;
        }

        public Factory setAddFriendListener(
                IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener) {
            this.addFriendListener = addFriendListener;
            return this;
        }

        public NewFriendsListAdapter make() {
            return make(new ArrayList<FriendProfile>());
        }

        public NewFriendsListAdapter make(ArrayList<FriendProfile> data) {
            NewFriendsListAdapter adapter = new NewFriendsListAdapter(data);

            adapter.setAddFriendListener(addFriendListener);

            adapter.setOnClickListener(listener);

            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<FriendProfile> listener) {
            this.listener = listener;
        }
    }
}
