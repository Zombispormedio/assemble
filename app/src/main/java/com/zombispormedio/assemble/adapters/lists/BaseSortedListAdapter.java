package com.zombispormedio.assemble.adapters.lists;


import com.annimon.stream.Collectors;
import com.annimon.stream.IntStream;
import com.annimon.stream.Stream;
import com.zombispormedio.assemble.adapters.AbstractHolder;
import com.zombispormedio.assemble.models.Sorted;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class BaseSortedListAdapter<T extends Sorted<T>, E extends AbstractHolder<T>> extends RecyclerView.Adapter<E> {


    protected final SortedList<T> mData;

    public BaseSortedListAdapter(Class<T> tClass) {
        mData = new SortedList<>(tClass, new SortedList.Callback<T>() {
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

    private boolean areItemsEquals(T item1, T item2) {
        return item1.getIdentity() == item2.getIdentity();
    }

    private boolean areItemsAndContentsTheSame(T oldItem, T newItem) {
        return oldItem.areTheSame(newItem);
    }

    protected int compareItems(T o1, T o2) {
        return o1.compareTo(o2);
    }

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected View getView(ViewGroup parent, int layout) {
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

    public T get(int position) {
        return mData.get(position);
    }

    public int add(T item) {
        return mData.add(item);
    }

    public int indexOf(T item) {
        return indexByIdentity(item.getIdentity());
    }

    public int indexByIdentity(int identity) {
        int result = -1;
        int size = mData.size();
        int i = 0;
        while (i < size && result == -1) {
            T item2 = mData.get(i);
            if (identity == item2.getIdentity()) {
                result = i;
            }
            i++;
        }

        return result;
    }

    public void updateItemAt(int index, T item) {
        mData.updateItemAt(index, item);
    }

    public void addAll(List<T> items) {
        if (items.size() == 0) {
            clear();
        } else {
            removeComparingWithList(items);

            mData.beginBatchedUpdates();
            Stream.of(items)
                    .forEach(mData::add);
            mData.endBatchedUpdates();
        }
    }

    private void removeComparingWithList(List<T> items) {
        ArrayList<Integer> ids = Stream.of(items)
                .map(Sorted::getIdentity)
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        int[] identitiesToRemove = IntStream.range(0, mData.size())
                .map(i -> mData.get(i).getIdentity())
                .filter(identity -> !ids.contains(identity))
                .toArray();

        mData.beginBatchedUpdates();
        for (int identity : identitiesToRemove) {
            removeByIdentity(identity);
        }
        mData.endBatchedUpdates();
    }


    public boolean remove(T item) {
        int index = indexOf(item);
        return mData.removeItemAt(index) != null;
    }

    public void removeByIdentity(int identity) {
        int index = indexByIdentity(identity);
        mData.removeItemAt(index);
    }

    public void removeItemAt(int index) {
        mData.removeItemAt(index);
    }

    public void clear() {
        mData.beginBatchedUpdates();

        while (mData.size() > 0) {
            mData.removeItemAt(mData.size() - 1);
        }

        mData.endBatchedUpdates();
    }


}
