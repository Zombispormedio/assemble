package com.zombispormedio.assemble.fragments;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.lists.ChatsListAdapter;
import com.zombispormedio.assemble.controllers.ChatsController;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.fragments.IChatsView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;


public class ChatsFragment extends BaseFragment implements IChatsView {

    private HomeActivity view;

    private ChatsController ctrl;

    @Nullable
    @BindView(R.id.chats_list)
    RecyclerView chatsList;

    @Nullable
    @BindView(R.id.chats_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private ChatsListAdapter chatsListAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (HomeActivity) getActivity();
        bindView(this, view);
        ctrl = new ChatsController(this);

        setupChats();

        setupRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        ctrl.onStart();
    }

    private void setupChats() {
        ChatsListAdapter.Factory chatsListFactory = new ChatsListAdapter.Factory();
        AndroidUtils.createListConfiguration(view, chatsList)
                .divider(true)
                .itemAnimation(true)
                .scrolling(false)
                .configure();
        chatsListFactory.setOnClickListener((position, data) -> ctrl.onChatItem(position, data));
        chatsListAdapter = chatsListFactory.make();
        chatsList.setAdapter(chatsListAdapter);
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> ctrl.onRefresh());
    }

    @Override
    public void bindChats(@NonNull ArrayList<Chat> data) {
        chatsListAdapter.addAll(data);
    }

    @Override
    public void updateChat(@NonNull Chat chat) {
        int index = chatsListAdapter.indexOf(chat);
        chatsListAdapter.updateItemAt(index, chat);
    }

    @Override
    public void finishRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void goToChat(int friendId) {
        NavigationManager.Chat(view, friendId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
