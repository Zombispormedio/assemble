package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.adapters.ChatsRecyclerViewAdapter;
import com.zombispormedio.assemble.controllers.ChatsController;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.services.IChatService;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IChatsView;

import java.util.ArrayList;


public class ChatsFragment extends Fragment implements IChatsView{

    private HomeActivity view;

    private ChatsController ctrl;

    private RecyclerView _listChats;

    private ChatsRecyclerViewAdapter.Factory _listChatsFactory;

    private ChatsRecyclerViewAdapter _listChatsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ctrl = new ChatsController(this);

        view = (HomeActivity) getActivity();

        _listChats = (RecyclerView) view.findViewById(R.id.chats_list);

        _listChatsFactory = new ChatsRecyclerViewAdapter.Factory();
        _listChatsAdapter = null;

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setupChats();

        ctrl.onCreate();
    }

    private void setupChats() {
        AndroidUtils.createListConfiguration(view, _listChats)
                .divider(true)
                .itemAnimation(true)
                .configure();
        _listChatsFactory.setOnClickListener(ctrl.getOnClickOneTeam());
    }

    @Override
    public void bindChats(ArrayList<Chat> data) {
        if (_listChatsAdapter == null) {
            _listChatsAdapter = _listChatsFactory.make(data);
            _listChats.setAdapter(_listChatsAdapter);
        } else {
            _listChatsAdapter.setData(data);
            _listChatsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showAlert(String msg) {
        view.showAlert(msg);
    }
}
