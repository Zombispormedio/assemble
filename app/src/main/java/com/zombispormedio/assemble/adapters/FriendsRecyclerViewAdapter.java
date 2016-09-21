package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.ISelectedFriend;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 21/09/2016.
 */

public class FriendsRecyclerViewAdapter  extends BaseRecyclerViewAdapter<FriendProfile, FriendViewHolder> {

    private IOnClickComponentItemHandler<FriendProfile, ISelectedFriend> listener;

    public FriendsRecyclerViewAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FriendViewHolder holder = new FriendViewHolder(getView(parent, R.layout.list_item_friends));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }

        return holder;
    }

    public void setOnClickListener(
            IOnClickComponentItemHandler<FriendProfile, ISelectedFriend> listener) {
        this.listener = listener;
    }

    public static class Factory{

        private IOnClickComponentItemHandler<FriendProfile, ISelectedFriend> listener;

        public FriendsRecyclerViewAdapter make(){
            return  make(new ArrayList<FriendProfile>());
        }

        public FriendsRecyclerViewAdapter make(ArrayList<FriendProfile> data) {
            FriendsRecyclerViewAdapter adapter = new FriendsRecyclerViewAdapter(data);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickComponentItemHandler<FriendProfile, ISelectedFriend> listener) {
            this.listener = listener;
        }
    }
}
