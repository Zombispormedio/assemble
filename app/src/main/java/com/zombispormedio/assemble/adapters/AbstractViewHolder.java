package com.zombispormedio.assemble.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(T itemData) {

    }
}
