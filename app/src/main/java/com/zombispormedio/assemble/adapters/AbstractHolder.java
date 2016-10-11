package com.zombispormedio.assemble.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public abstract class AbstractHolder<T> extends RecyclerView.ViewHolder {

    public AbstractHolder(View itemView) {
        super(itemView);
    }

    public void bind(int position, T itemData) {
    }

    protected Context getContext(){
        return itemView.getContext();
    }

    protected View getView(){
        return itemView;
    }

}
