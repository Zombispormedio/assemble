package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.LeftHolder;
import com.zombispormedio.assemble.models.ContentMessage;
import com.zombispormedio.assemble.models.Message;

import android.view.ViewGroup;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class LeftListAdapter extends BaseListAdapter<ContentMessage, LeftHolder> {

    @Override
    public LeftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeftHolder(getView(parent, R.layout.left_message));
    }
}
