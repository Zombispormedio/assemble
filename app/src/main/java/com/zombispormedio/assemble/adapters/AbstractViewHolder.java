package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;


import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public AbstractViewHolder(View view) {
        super(view);
    }

    public void bind(int position, T itemData) {

    }



}
