package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.ChatHolder;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatsListAdapter extends BaseSortedListAdapter<Chat, ChatHolder> {

    private IOnClickItemListHandler<Chat> listener;

    public ChatsListAdapter(ArrayList<Chat> data) {
        super(Chat.class);
        addAll(data);
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChatHolder holder = new ChatHolder(getView(parent, R.layout.list_item_chats));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    @Override
    protected int compareItems(Chat o1, Chat o2) {
        return -super.compareItems(o1, o2);
    }

    public void setOnClickListener(
            IOnClickItemListHandler<Chat> listener) {
        this.listener = listener;
    }

    public static class Factory{

        private IOnClickItemListHandler<Chat> listener;

        public ChatsListAdapter make(){
            return make(new ArrayList<Chat>());
        }

        public ChatsListAdapter make(ArrayList<Chat> data) {
            ChatsListAdapter adapter = new ChatsListAdapter(data);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<Chat> listener) {
            this.listener = listener;
        }
    }
}
