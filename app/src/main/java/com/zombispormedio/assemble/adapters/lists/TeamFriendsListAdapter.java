package com.zombispormedio.assemble.adapters.lists;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.TeamFriendHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 21/09/2016.
 */

public class TeamFriendsListAdapter extends BaseSortedListAdapter<
        TeamFriendHolder.SelectedContainer, TeamFriendHolder> {

    private IOnClickItemListHandler<TeamFriendHolder.SelectedContainer> listener;

    public TeamFriendsListAdapter(ArrayList<FriendProfile> data) {
        super(TeamFriendHolder.SelectedContainer.class);
        addAll(apply(data));
    }

    @Override
    public TeamFriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TeamFriendHolder holder = new TeamFriendHolder(getView(parent, R.layout.list_item_team_friends));
        holder.setOnClickListener(listener);

        return holder;
    }


    public void setOnClickListener(
            IOnClickItemListHandler<TeamFriendHolder.SelectedContainer> listener) {
        this.listener = listener;
    }

    public ArrayList<TeamFriendHolder.SelectedContainer> apply(ArrayList<FriendProfile> data) {

        return Stream.of(data)
                .map(TeamFriendHolder.SelectedContainer::new)
                .collect(Collectors.toCollection(ArrayList<TeamFriendHolder.SelectedContainer>::new));
    }


    public void setFriendProfiles(ArrayList<FriendProfile> data) {
        super.addAll(apply(data));
    }

    public void selectFriend(int index){
        TeamFriendHolder.SelectedContainer container= mData.get(index);
        container.select();
        notifyItemChanged(index);
    }

    public void deselectFriend(int index){
        TeamFriendHolder.SelectedContainer container= mData.get(index);
        container.deselect();
        notifyItemChanged(index);
    }




    public static class Factory {

        private IOnClickItemListHandler<TeamFriendHolder.SelectedContainer> listener;

        public TeamFriendsListAdapter make() {
            return make(new ArrayList<>());
        }

        public TeamFriendsListAdapter make(ArrayList<FriendProfile> data) {
            TeamFriendsListAdapter adapter = new TeamFriendsListAdapter(data);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<TeamFriendHolder.SelectedContainer> listener) {
            this.listener = listener;
        }
    }
}
