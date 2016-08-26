package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.models.FriendProfile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class BaseRecyclerViewAdapter<T, E extends AbstractViewHolder<T>> extends RecyclerView.Adapter<E>
        implements View.OnClickListener {

    protected View.OnClickListener listener;
    protected ArrayList<T> data;

    public BaseRecyclerViewAdapter(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(E holder, int position) {
        T item=data.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }





}
