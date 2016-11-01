package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.FriendHolder;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.holders.IFriendHolder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 25/08/2016.
 */
public class FriendsListAdapter
        extends FriendProfileAdapter<FriendHolder> {

    private IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener;

    private IOnClickItemListHandler<FriendProfile> listener;

    private FriendsListAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FriendHolder holder = new FriendHolder(getView(parent, R.layout.list_item_friends));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }

        holder.setRemoveButtonListener(removeButtonListener);
        return holder;
    }

    public void setRemoveButtonListener(
            IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener) {
        this.removeButtonListener = removeButtonListener;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }

    public static class Factory {

        private IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener;

        private IOnClickItemListHandler<FriendProfile> listener;

        @NonNull
        public FriendsListAdapter make() {
            return make(new ArrayList<>());
        }

        @NonNull
        public FriendsListAdapter make(ArrayList<FriendProfile> data) {
            FriendsListAdapter adapter = new FriendsListAdapter(data);
            adapter.setRemoveButtonListener(removeButtonListener);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setRemoveButtonListener(
                IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener) {
            this.removeButtonListener = removeButtonListener;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<FriendProfile> listener) {
            this.listener = listener;
        }
    }


}
