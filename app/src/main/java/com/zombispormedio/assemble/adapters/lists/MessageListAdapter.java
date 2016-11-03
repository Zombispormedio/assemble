package com.zombispormedio.assemble.adapters.lists;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.FriendMessageHolder;
import com.zombispormedio.assemble.adapters.FriendMessageWithImageHolder;
import com.zombispormedio.assemble.adapters.MessageHolder;
import com.zombispormedio.assemble.adapters.UserMessageHolder;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.UnknownServiceException;
import java.util.ArrayList;


/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageHolder> {

    private static final int USER=0;

    private static final int FRIEND=1;

    private static final int FRIEND_WITH_IMAGE=2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({USER, FRIEND, FRIEND_WITH_IMAGE})

    public @interface ViewType{}


    protected ArrayList<MessageHolder.Container> data;

    public MessageListAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, @ViewType int viewType) {
        return viewType==USER? new UserMessageHolder(parent):
                viewType==FRIEND_WITH_IMAGE? new FriendMessageWithImageHolder(parent):
                new FriendMessageHolder(parent);
    }

    @Override
    public int getItemViewType(int position) {
        @ViewType int viewType=FRIEND;
        Message message = data.get(position).getContent();

        if(message.sender instanceof UserProfile){
            viewType=USER;
        }else{
            int prev = position - 1;
            if (position > 0) {
                Message previous=data.get(prev).getContent();
                if(isRoot(message, previous)){
                    viewType=FRIEND_WITH_IMAGE;
                }
            }
        }

        return viewType;
    }

    private boolean isRoot(@NonNull Message m1, Message m2){
        boolean isIt=m2==null;

        if(!isIt){
            if (m1.sender != null) {
                if (m2.sender != null) {
                    isIt=m1.sender.id!=m2.sender.id;
                }
            }
        }

        return isIt;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        MessageHolder.Container item = data.get(position);
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

    public void addAll(@NonNull ArrayList<Message> messages) {
        this.data = Stream.of(messages)
                .map(MessageHolder.Container::new)
                .collect(Collectors.toCollection(ArrayList<MessageHolder.Container>::new));

        notifyDataSetChanged();
    }

    public void add(Message message) {
        int index = data.size();
        data.add(new MessageHolder.Container(message));
        notifyItemRangeChanged(index, data.size());
    }

    public void read(int id) {
        int index = indexOfById(id);
        if (index > -1) {
            MessageHolder.Container container = data.get(index);
            container.read();
            notifyItemChanged(index);
        }

    }


    public int indexOfById(int id) {
        int index = -1;
        int len = data.size();
        int i = 0;
        while (i < len && index == -1) {
            Message message = data.get(i).getContent();
            if (message.id == id) {
                index = i;
            }
            i++;
        }

        return index;
    }

    public int addPending(Message message) {
        int index = data.size();

        data.add(new MessageHolder.Container(message));

        notifyItemInserted(index);

        return index;

    }


    public void checkMessage(int index, Message message) {
        MessageHolder.Container container = data.get(index);
        container.setContent(message);
        data.set(index, container);
        notifyItemChanged(index);
    }
}
