package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.lists.ChatsListAdapter;
import com.zombispormedio.assemble.controllers.ChatsController;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.fragments.IChatsView;

import java.util.ArrayList;

import butterknife.BindView;


public class ChatsFragment extends BaseFragment implements IChatsView {

    private HomeActivity view;

    private ChatsController ctrl;

    @BindView(R.id.chats_list)
    RecyclerView chatsList;

    @BindView(R.id.chats_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private ChatsListAdapter.Factory chatsListFactory;

    private ChatsListAdapter chatsListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        chatsListFactory = new ChatsListAdapter.Factory();
        AndroidUtils.createListConfiguration(view, chatsList)
                .divider(true)
                .itemAnimation(true)
                .configure();
        chatsListFactory.setOnClickListener(new IOnClickItemListHandler<Chat>() {
            @Override
            public void onClick(int position, Chat data) {
                ctrl.onChatItem(position, data);
            }
        });
        chatsListAdapter = chatsListFactory.make();
        chatsList.setAdapter(chatsListAdapter);
    }

    private void setupRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ctrl.onRefresh();
            }
        });
    }

    @Override
    public void bindChats(ArrayList<Chat> data) {
        chatsListAdapter.setData(data);
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
