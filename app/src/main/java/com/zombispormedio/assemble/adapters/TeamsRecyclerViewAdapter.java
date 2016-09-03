package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsRecyclerViewAdapter extends BaseRecyclerViewAdapter<Team, TeamViewHolder> {

    public TeamsRecyclerViewAdapter(ArrayList<Team> data) {
        super(data);
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_teams, parent, false);

        TeamViewHolder holder = new   TeamViewHolder(itemView);
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }


    public static class Factory {

        private IOnClickItemListHandler<Team> listener;

        public void setOnClickListener(IOnClickItemListHandler<Team> listener) {
            this.listener = listener;

        }

        public TeamsRecyclerViewAdapter make(ArrayList<Team> data) {
            TeamsRecyclerViewAdapter adapter = new TeamsRecyclerViewAdapter(data);
            if (listener != null) {
                adapter.setOnClickListener(listener);
            }
            return adapter;
        }
    }
}
