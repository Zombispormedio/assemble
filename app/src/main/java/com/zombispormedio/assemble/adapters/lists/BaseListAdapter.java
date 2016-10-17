package com.zombispormedio.assemble.adapters.lists;


import com.zombispormedio.assemble.adapters.AbstractHolder;
import com.zombispormedio.assemble.models.BaseModel;
import com.zombispormedio.assemble.models.Sorted;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class BaseListAdapter<T extends Sorted<T>, E extends AbstractHolder<T>> extends RecyclerView.Adapter<E> {


    protected SortedList<T> mData;

    public BaseListAdapter(Class<T> tClass ) {
        mData =new SortedList<>(tClass, new SortedList.Callback<T>() {
            @Override
            public int compare(T o1, T o2) {
                return compareItems(o1, o2);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(T oldItem, T newItem) {
                return areItemsAndContentsTheSame(oldItem, newItem);
            }

            @Override
            public boolean areItemsTheSame(T item1, T item2) {
                return areItemsEquals(item1, item2);
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    protected boolean areItemsEquals(T item1, T item2) {
        if(item1 instanceof BaseModel && item2 instanceof BaseModel){
            return ((BaseModel) item1).id==((BaseModel) item2).id;
        }
        return item1.equals(item2);
    }

    protected boolean areItemsAndContentsTheSame(T oldItem, T newItem) {
        return oldItem.areTheSame(newItem);
    }

    protected int compareItems(T o1, T o2) {
        return o1.compareTo(o2);
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
        T item = mData.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T get(int position){
        return mData.get(position);
    }

    public int add(T item){
        return mData.add(item);
    }

    public int indexOf(T item){
        int result=-1;
        if(item instanceof BaseModel){
            int size=mData.size();
            int i=0;
           while(i<size&&result==-1){
               T item2=mData.get(i);
               if(((BaseModel) item).id==((BaseModel) item2).id){
                   result=i;
               }

               i++;
           }

        }else{
            result= mData.indexOf(item);
        }

        return result;
    }

    public void updateItemAt(int index, T item){
        mData.updateItemAt(index, item);
    }

    public void addAll(List<T> items) {
        mData.beginBatchedUpdates();
        for (T item : items) {
            mData.add(item);
        }
        mData.endBatchedUpdates();
    }

    public boolean remove(T item){
        return mData.remove(item);
    }

    public T removeItemAt(int index){
        return mData.removeItemAt(index);
    }

    public void clear(){
        mData.beginBatchedUpdates();

        while (mData.size()>0){
            mData.removeItemAt(mData.size()-1);
        }

        mData.endBatchedUpdates();
    }



}
