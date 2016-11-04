package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.MessageListAdapter;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.AndroidUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends Fragment{


    @BindView(R.id.messages_list)
    RecyclerView messagesList;

    private MessageListAdapter messageListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
        ButterKnife.bind(this, rootView);

        setupMessages();

        return rootView;
    }

    private void setupMessages() {
        AndroidUtils.createListConfiguration(getActivity(), messagesList)
                .startAtEnd(true)
                .configure();

        RecyclerView.ItemAnimator animator = messagesList.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        messageListAdapter = new MessageListAdapter();
        messagesList.setAdapter(messageListAdapter);
    }

    public void bindMessages(@NonNull ArrayList<Message> messages) {
        messageListAdapter.addAll(messages);
        messagesList.scrollToPosition(messages.size() - 1);
    }

    public int addPendingMessage(Message message) {
        int index = messageListAdapter.addPending(message);
        messagesList.scrollToPosition(index);
        return index;
    }

    public void updateMessage(int index, Message message) {
        messageListAdapter.checkMessage(index, message);
    }

    public void addMessage(Message message) {
        int index = messageListAdapter.getItemCount();
        messageListAdapter.add(message);
        messagesList.scrollToPosition(index);
    }

    public void read(int id) {
        messageListAdapter.read(id);
    }
}
