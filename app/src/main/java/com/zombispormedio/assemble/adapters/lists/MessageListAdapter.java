package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.MessageHolder;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Profile;

import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageListAdapter extends BaseListAdapter<Message.Container, MessageHolder> {


    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(getView(parent, R.layout.list_item_message));
    }


    public void bindData(ArrayList<Message> data) {
        ArrayList<Message.Container> containers = new ArrayList<>();

        Message.Container container=null;
        for(Message msg : data){
            if(container==null){
                container=new Message.Container(msg.sender);
                containers.add(container);
                container.addMessage(msg);
            }else{
                if(msg.sender.id==container.getSender().id){
                    container.addMessage(msg);
                }else{
                    container=new Message.Container(msg.sender);
                    containers.add(container);
                    container.addMessage(msg);
                }
            }
        }

        setData(containers);
    }
}
