package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.SelectedMemberHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 23/09/2016.
 */

public class SelectedMembersListAdapter extends BaseSortedListAdapter<
        SelectedMemberHolder.Container, SelectedMemberHolder> {

    private IOnClickItemListHandler<SelectedMemberHolder.Container> listener;

    @NonNull
    private final ArrayList<Integer> friendIndexes;

    public SelectedMembersListAdapter() {
        super(SelectedMemberHolder.Container.class);
        friendIndexes = new ArrayList<>();
    }

    @NonNull
    @Override
    public SelectedMemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SelectedMemberHolder holder = new SelectedMemberHolder(getView(parent, R.layout.list_item_selected_members));
        holder.setOnClickListener(listener);
        return holder;
    }


    public void setOnClickListener(
            IOnClickItemListHandler<SelectedMemberHolder.Container> listener) {
        this.listener = listener;
    }

    public void addMember(FriendProfile content, int friendIndex) {
        if (!friendIndexes.contains(friendIndex)) {

            int index = add(new SelectedMemberHolder.Container(content, friendIndex));
            friendIndexes.add(index, friendIndex);

        }

    }

    public void removeMemberByFriend(int friendIndex) {
        int index = findIndexByFriend(friendIndex);
        if (index > -1) {
            removeItemAt(index);
            friendIndexes.remove(index);
        }
    }

    private int findIndexByFriend(int friendIndex) {
        int index = -1;
        if (friendIndexes.contains(friendIndex)) {
            int i = 0;

            while (i < mData.size() && index == -1) {
                SelectedMemberHolder.Container t = mData.get(i);
                if (t.getIndex() == friendIndex) {
                    index = i;
                }
                i++;
            }

        }

        return index;
    }

}
