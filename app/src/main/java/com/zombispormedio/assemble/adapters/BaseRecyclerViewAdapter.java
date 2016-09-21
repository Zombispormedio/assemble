package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class BaseRecyclerViewAdapter<T, E extends AbstractViewHolder<T>> extends RecyclerView.Adapter<E> {


    protected ArrayList<T> data;

    public BaseRecyclerViewAdapter(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected View getView(ViewGroup parent,int layout){
        return LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
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

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public void addElement(T elem){
        data.add(elem);
        notifyItemInserted(data.size()-1);
    }

    public void removeElement(int index){
        data.remove(index);
        notifyItemRemoved(index);
    }

}
