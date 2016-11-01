package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.PreviewFriendHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.support.annotation.NonNull;
import android.view.ViewGroup;


/**
 * Created by Xavier Serrano on 29/09/2016.
 */

public class PreviewFriendsListAdapter extends FriendProfileAdapter<PreviewFriendHolder> {

    private IOnClickItemListHandler<FriendProfile> listener;

    @NonNull
    @Override
    public PreviewFriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PreviewFriendHolder holder = new PreviewFriendHolder(getView(parent, R.layout.list_item_preview_friends));

        holder.setOnClickListener(listener);

        return holder;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }
}
