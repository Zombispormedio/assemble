package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.INewFriendHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendsRecyclerViewAdapter extends BaseRecyclerViewAdapter<FriendProfile, NewFriendViewHolder> {

    private IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener;

    private IOnClickItemListHandler<FriendProfile> listener;

    public NewFriendsRecyclerViewAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }


    @Override
    public NewFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewFriendViewHolder holder = new NewFriendViewHolder(getView(parent,R.layout.list_item_new_friends));
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

        public NewFriendsRecyclerViewAdapter make() {
            return make(new ArrayList<FriendProfile>());
        }

        public NewFriendsRecyclerViewAdapter make(ArrayList<FriendProfile> data) {
            NewFriendsRecyclerViewAdapter adapter = new NewFriendsRecyclerViewAdapter(data);

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
