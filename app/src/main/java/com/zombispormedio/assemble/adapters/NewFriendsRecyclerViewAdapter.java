package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendsRecyclerViewAdapter extends BaseRecyclerViewAdapter<FriendProfile, NewFriendViewHolder> {

    public NewFriendsRecyclerViewAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }


    @Override
    public NewFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewFriendViewHolder holder = new NewFriendViewHolder(getView(parent,R.layout.list_item_new_friends));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    public static class Factory extends BaseRecyclerViewAdapter.Factory<NewFriendsRecyclerViewAdapter, FriendProfile> {


        public NewFriendsRecyclerViewAdapter make() {
            return make(new ArrayList<FriendProfile>());
        }

        public NewFriendsRecyclerViewAdapter make(ArrayList<FriendProfile> data) {
            NewFriendsRecyclerViewAdapter adapter = new NewFriendsRecyclerViewAdapter(data);
            return super.make(adapter);
        }
    }
}
