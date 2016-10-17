package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.TeamHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsListAdapter extends BaseListAdapter<Team, TeamHolder> {

    private IOnClickItemListHandler<Team> listener;

    public TeamsListAdapter(ArrayList<Team> data) {
        super(Team.class);
        addAll(data);
    }

    @Override
    public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TeamHolder holder = new TeamHolder(getView(parent, R.layout.list_item_teams));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }

    public static class Factory{

        private IOnClickItemListHandler<Team> listener;

        public TeamsListAdapter make() {
            return make(new ArrayList<>());
        }

        public TeamsListAdapter make(ArrayList<Team> data) {
            TeamsListAdapter adapter = new TeamsListAdapter(data);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<Team> listener) {
            this.listener = listener;
        }
    }
}
