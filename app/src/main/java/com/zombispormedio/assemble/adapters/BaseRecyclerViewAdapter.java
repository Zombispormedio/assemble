package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class BaseRecyclerViewAdapter<T, E extends AbstractViewHolder<T>> extends RecyclerView.Adapter<E> {

    protected IOnClickItemListHandler<T> listener;

    protected ArrayList<T> data;

    public BaseRecyclerViewAdapter(ArrayList<T> data) {
        this.data = data;
        this.listener = null;
    }

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(E holder, int position) {
        T item = data.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(IOnClickItemListHandler listener){
        this.listener=listener;
    }


}
