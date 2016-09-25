package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.ParticipantHolder;
import com.zombispormedio.assemble.models.FriendProfile;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 25/09/2016.
 */

public class ParticipantsListAdapter extends BaseListAdapter<
        FriendProfile, ParticipantHolder> {

    @Override
    public ParticipantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return   new ParticipantHolder(getView(parent, R.layout.list_item_participants));
    }
}
