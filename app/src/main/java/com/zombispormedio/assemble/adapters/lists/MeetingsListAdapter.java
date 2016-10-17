package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.MeetingHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Meeting;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingsListAdapter extends BaseListAdapter<Meeting, MeetingHolder> {

    private IOnClickItemListHandler<Meeting> listener;

    private MeetingsListAdapter(ArrayList<Meeting> data) {
        super(Meeting.class);
        addAll(data);
    }

    @Override
    public MeetingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MeetingHolder holder = new MeetingHolder(getView(parent,R.layout.list_item_meetings));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }


    @Override
    protected int compareItems(Meeting o1, Meeting o2) {
        return -super.compareItems(o1, o2);
    }

    public void setOnClickListener(
            IOnClickItemListHandler<Meeting> listener) {
        this.listener = listener;
    }



    public static class Factory  {

        private IOnClickItemListHandler<Meeting> listener;

        public MeetingsListAdapter make(){
            return make(new ArrayList<Meeting>());
        }

        public MeetingsListAdapter make(ArrayList<Meeting> data) {
            MeetingsListAdapter adapter = new MeetingsListAdapter(data);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<Meeting> listener) {
            this.listener = listener;
        }
    }
}
