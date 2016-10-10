package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.MessageHolder;
import com.zombispormedio.assemble.models.Message;
import android.view.ViewGroup;


/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageListAdapter extends BaseListAdapter<Message, MessageHolder> {


    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(getView(parent, R.layout.list_item_message));
    }


    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        Message item=data.get(position);
        if(position>0){
            holder.bind(position, item, data.get(position-1));
        }else{
            holder.bind(position, item);
        }
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
