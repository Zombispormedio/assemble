package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Meeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingsRecyclerViewAdapter extends BaseRecyclerViewAdapter<Meeting, MeetingViewHolder> {

    private IOnClickItemListHandler<Meeting> listener;

    public MeetingsRecyclerViewAdapter(ArrayList<Meeting> data) {
        super(data);
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MeetingViewHolder holder = new MeetingViewHolder(getView(parent,R.layout.list_item_meetings));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<Meeting> listener) {
        this.listener = listener;
    }

    public static class Factory  {

        private IOnClickItemListHandler<Meeting> listener;

        public MeetingsRecyclerViewAdapter make(){
            return make(new ArrayList<Meeting>());
        }

        public MeetingsRecyclerViewAdapter make(ArrayList<Meeting> data) {
            MeetingsRecyclerViewAdapter adapter = new MeetingsRecyclerViewAdapter(data);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<Meeting> listener) {
            this.listener = listener;
        }
    }
}
