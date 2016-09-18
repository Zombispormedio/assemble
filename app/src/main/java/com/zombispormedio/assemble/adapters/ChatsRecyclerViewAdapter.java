package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatsRecyclerViewAdapter extends BaseRecyclerViewAdapter<Chat, ChatViewHolder> {

    public ChatsRecyclerViewAdapter(ArrayList<Chat> data) {
        super(data);
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChatViewHolder holder = new ChatViewHolder(getView(parent, R.layout.list_item_chats));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }
        return holder;
    }

    public static class Factory extends BaseRecyclerViewAdapter.Factory<ChatsRecyclerViewAdapter, Chat>{

        public ChatsRecyclerViewAdapter make(){
            return make(new ArrayList<Chat>());
        }

        public ChatsRecyclerViewAdapter make(ArrayList<Chat> data) {
            ChatsRecyclerViewAdapter adapter = new ChatsRecyclerViewAdapter(data);
            return super.make(adapter);
        }
    }
}
