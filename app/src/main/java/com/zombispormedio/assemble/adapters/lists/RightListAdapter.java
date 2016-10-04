package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.RightHolder;
import com.zombispormedio.assemble.models.ContentMessage;

import android.view.ViewGroup;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class RightListAdapter extends BaseListAdapter<ContentMessage, RightHolder> {

    @Override
    public RightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RightHolder(getView(parent, R.layout.right_message));
    }

}
