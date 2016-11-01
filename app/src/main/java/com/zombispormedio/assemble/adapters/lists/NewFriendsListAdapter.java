package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.NewFriendHolder;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.holders.INewFriendHolder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendsListAdapter extends FriendProfileAdapter<NewFriendHolder> {

    private IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener;

    private IOnClickItemListHandler<FriendProfile> listener;

    private NewFriendsListAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }


    @NonNull
    @Override
    public NewFriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewFriendHolder holder = new NewFriendHolder(getView(parent, R.layout.list_item_new_friends));
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

    public static class Factory {

        @Nullable
        private IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener;

        private IOnClickItemListHandler<FriendProfile> listener;

        public Factory() {
            addFriendListener = null;
        }

        @NonNull
        public Factory setAddFriendListener(
                IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener) {
            this.addFriendListener = addFriendListener;
            return this;
        }

        @NonNull
        public NewFriendsListAdapter make() {
            return make(new ArrayList<>());
        }

        @NonNull
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
