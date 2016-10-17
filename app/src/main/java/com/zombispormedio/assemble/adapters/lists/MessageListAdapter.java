package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.MessageHolder;
import com.zombispormedio.assemble.models.Message;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageHolder> {

    protected ArrayList<Message> data;

    public MessageListAdapter() {
        data=new ArrayList<>();
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_message, parent, false);
        return new MessageHolder(view);
    }


    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        Message item = data.get(position);
        int prev = position - 1;
        if (position > 0) {
            holder.bind(position, item, data.get(prev));
        } else {
            holder.bind(position, item);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(ArrayList<Message> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public int addPending(Message message) {
        int index = data.size();

        data.add(message);

        notifyItemInserted(index);

        return index;

    }

    public void checkMessage(int index, Message message) {

        data.set(index, message);

        notifyItemChanged(index);
    }
}
