package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 25/08/2016.
 */
public class FriendsRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<FriendProfile, FriendViewHolder> {


    public FriendsRecyclerViewAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }


    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_friends, parent, false);

        FriendViewHolder holder = new FriendViewHolder(itemView);
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    public static class Factory {

        private IOnClickItemListHandler<FriendProfile> listener;

        public void setOnClickListener(IOnClickItemListHandler<FriendProfile> listener) {
            this.listener = listener;

        }

        public FriendsRecyclerViewAdapter make(ArrayList<FriendProfile> data) {
            FriendsRecyclerViewAdapter adapter = new FriendsRecyclerViewAdapter(data);
            if (listener != null) {
                adapter.setOnClickListener(listener);
            }
            return adapter;
        }
    }


}
