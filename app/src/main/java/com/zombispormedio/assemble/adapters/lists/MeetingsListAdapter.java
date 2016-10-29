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
public class MeetingsListAdapter extends BaseSortedListAdapter<Meeting, MeetingHolder> {

    private IOnClickItemListHandler<Meeting> listener;

    private IOnClickItemListHandler<Meeting> bookmarkListener;

    private MeetingsListAdapter(ArrayList<Meeting> data) {
        super(Meeting.class);
        addAll(data);
    }

    @Override
    public MeetingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MeetingHolder holder = new MeetingHolder(getView(parent, R.layout.list_item_meetings));

        holder.setOnClickListener(listener);

        holder.setBookmarkListener(bookmarkListener);

        return holder;
    }


    public void setOnClickListener(
            IOnClickItemListHandler<Meeting> listener) {
        this.listener = listener;
    }

    public void setBookmarkListener(
            IOnClickItemListHandler<Meeting> bookmarkListener) {
        this.bookmarkListener = bookmarkListener;
    }

    public static class Factory {

        private IOnClickItemListHandler<Meeting> listener;

        private IOnClickItemListHandler<Meeting> bookmarkListener;


        public MeetingsListAdapter make() {
            return make(new ArrayList<>());
        }

        public MeetingsListAdapter make(ArrayList<Meeting> data) {
            MeetingsListAdapter adapter = new MeetingsListAdapter(data);
            adapter.setOnClickListener(listener);
            adapter.setBookmarkListener(bookmarkListener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<Meeting> listener) {
            this.listener = listener;
        }

        public void setBookmarkListener(
                IOnClickItemListHandler<Meeting> bookmarkListener) {
            this.bookmarkListener = bookmarkListener;
        }
    }
}
