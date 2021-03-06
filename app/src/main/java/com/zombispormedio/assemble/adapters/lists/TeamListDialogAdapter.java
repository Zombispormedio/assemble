package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.TeamDialogHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class TeamListDialogAdapter extends BaseSortedListAdapter<Team, TeamDialogHolder> {

    private IOnClickItemListHandler<Team> listener;

    public TeamListDialogAdapter(@NonNull ArrayList<Team> data) {
        super(Team.class);
        addAll(data);
    }

    @NonNull
    @Override
    public TeamDialogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TeamDialogHolder holder = new TeamDialogHolder(getView(parent, R.layout.list_item_team_dialog));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }

    public static class Factory {

        private IOnClickItemListHandler<Team> listener;

        @NonNull
        public TeamListDialogAdapter make() {
            return make(new ArrayList<>());
        }

        @NonNull
        public TeamListDialogAdapter make(@NonNull ArrayList<Team> data) {
            TeamListDialogAdapter adapter = new TeamListDialogAdapter(data);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<Team> listener) {
            this.listener = listener;
        }
    }
}
