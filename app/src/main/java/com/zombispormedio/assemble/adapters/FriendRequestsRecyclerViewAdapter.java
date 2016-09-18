package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;

import com.zombispormedio.assemble.models.FriendRequestProfile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<FriendRequestProfile, FriendRequestsViewHolder> {


    public FriendRequestsRecyclerViewAdapter(ArrayList<FriendRequestProfile> data) {
        super(data);
    }

    @Override
    public FriendRequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FriendRequestsViewHolder holder = new FriendRequestsViewHolder(getView(parent, R.layout.list_item_friend_requests));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    public static class Factory extends BaseRecyclerViewAdapter.Factory<FriendRequestsRecyclerViewAdapter, FriendRequestProfile>{

        public FriendRequestsRecyclerViewAdapter make(){
            return make(new ArrayList<FriendRequestProfile>());
        }


        public FriendRequestsRecyclerViewAdapter make(ArrayList<FriendRequestProfile> data) {
            FriendRequestsRecyclerViewAdapter adapter = new FriendRequestsRecyclerViewAdapter(data);
            return super.make(adapter);
        }
    }
}
