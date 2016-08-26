package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(int position, T itemData) {

    }

    public void setOnClickListener(IOnClickItemListHandler<T> listener){

    }


}
