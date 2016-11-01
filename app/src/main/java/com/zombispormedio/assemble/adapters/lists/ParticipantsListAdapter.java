package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.ParticipantHolder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;


/**
 * Created by Xavier Serrano on 25/09/2016.
 */

public class ParticipantsListAdapter extends FriendProfileAdapter<ParticipantHolder> {

    @NonNull
    @Override
    public ParticipantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParticipantHolder(getView(parent, R.layout.list_item_participants));
    }
}
