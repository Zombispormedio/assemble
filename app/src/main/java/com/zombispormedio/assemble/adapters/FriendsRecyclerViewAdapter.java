package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.FriendProfile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        itemView.setOnClickListener(this);

        return new FriendViewHolder(itemView);
    }


}
