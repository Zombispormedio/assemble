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
public class FriendsAdapter
        extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener listener;
    private ArrayList<FriendProfile> data;

    public static class FriendsViewHolder extends RecyclerView.ViewHolder{
        private TextView usernameLabel;
        private TextView emailLabel;

        public FriendsViewHolder(View itemView) {
            super(itemView);
            usernameLabel=(TextView) itemView.findViewById(R.id.username_label);
            emailLabel=(TextView) itemView.findViewById(R.id.email_label);
        }

        public void bindFriend(FriendProfile friend){
            usernameLabel.setText(friend.username);
            emailLabel.setText(friend.email);

        }
    }

    public FriendsAdapter(ArrayList<FriendProfile> data) {
        this.data = data;
    }

    @Override
    public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_friends, parent, false);

        itemView.setOnClickListener(this);

        return new FriendsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendsViewHolder holder, int position) {
        FriendProfile item=data.get(position);

        holder.bindFriend(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }



}
