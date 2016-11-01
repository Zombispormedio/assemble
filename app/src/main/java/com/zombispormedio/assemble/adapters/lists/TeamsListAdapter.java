package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.TeamHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamsListAdapter extends BaseSortedListAdapter<Team, TeamHolder> {

    private IOnClickItemListHandler<Team> listener;

    private IOnClickItemListHandler<Team> starCheckerListener;

    public TeamsListAdapter(ArrayList<Team> data) {
        super(Team.class);
        addAll(data);
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TeamHolder holder = new TeamHolder(getView(parent, R.layout.list_item_teams));
        holder.setOnClickListener(listener);
        holder.setStarCheckerListener(starCheckerListener);

        return holder;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }

    public void setStarCheckerListener(
            IOnClickItemListHandler<Team> starCheckerListener) {
        this.starCheckerListener = starCheckerListener;
    }

    public static class Factory {

        private IOnClickItemListHandler<Team> listener;

        private IOnClickItemListHandler<Team> starCheckerListener;

        @NonNull
        public TeamsListAdapter make() {
            return make(new ArrayList<>());
        }

        @NonNull
        public TeamsListAdapter make(ArrayList<Team> data) {
            TeamsListAdapter adapter = new TeamsListAdapter(data);
            adapter.setOnClickListener(listener);
            adapter.setStarCheckerListener(starCheckerListener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<Team> listener) {
            this.listener = listener;
        }

        public void setStarCheckerListener(
                IOnClickItemListHandler<Team> starCheckerListener) {
            this.starCheckerListener = starCheckerListener;
        }
    }
}
