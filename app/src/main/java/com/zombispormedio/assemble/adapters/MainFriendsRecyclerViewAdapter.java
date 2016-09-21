package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.IMainFriendHolder;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 25/08/2016.
 */
public class MainFriendsRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<FriendProfile, MainFriendViewHolder> {

    private IOnClickComponentItemHandler<FriendProfile, IMainFriendHolder> removeButtonListener;

    private IOnClickItemListHandler<FriendProfile> listener;

    public MainFriendsRecyclerViewAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }


    @Override
    public MainFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MainFriendViewHolder holder = new MainFriendViewHolder(getView(parent, R.layout.list_item_main_friends));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }

        holder.setRemoveButtonListener(removeButtonListener);
        return holder;
    }

    public void setRemoveButtonListener(
            IOnClickComponentItemHandler<FriendProfile, IMainFriendHolder> removeButtonListener) {
        this.removeButtonListener = removeButtonListener;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }

    public static class Factory{

        private IOnClickComponentItemHandler<FriendProfile, IMainFriendHolder> removeButtonListener;

        private IOnClickItemListHandler<FriendProfile> listener;

        public MainFriendsRecyclerViewAdapter make(){
            return  make(new ArrayList<FriendProfile>());
        }

        public MainFriendsRecyclerViewAdapter make(ArrayList<FriendProfile> data) {
            MainFriendsRecyclerViewAdapter adapter = new MainFriendsRecyclerViewAdapter(data);
            adapter.setRemoveButtonListener(removeButtonListener);
            adapter.setOnClickListener(listener);
            return adapter;
        }

        public void setRemoveButtonListener(
                IOnClickComponentItemHandler<FriendProfile, IMainFriendHolder> removeButtonListener) {
            this.removeButtonListener = removeButtonListener;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<FriendProfile> listener) {
            this.listener = listener;
        }
    }


}
