package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.MessageHolder;
import com.zombispormedio.assemble.models.Message;
import android.view.ViewGroup;


/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageListAdapter extends BaseListAdapter<Message, MessageHolder> {


    public MessageListAdapter() {
        super(Message.class);
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(getView(parent, R.layout.list_item_message));
    }


    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        Message item= mData.get(position);
        int prev=position-1;
        if(position>0){
            holder.bind(position, item, mData.get(prev));
        }else{
            holder.bind(position, item);
        }
    }

    public int addPending(Message message) {
        return add(message);
    }

    public void checkMessage(int index, Message message) {

        updateItemAt(index, message);
    }
}
