package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;

import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.views.IFriendRequestHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<FriendRequestProfile, FriendRequestsViewHolder> {

    private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> acceptListener;

    private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> rejectListener;

    private IOnClickItemListHandler<FriendRequestProfile> listener;


    public FriendRequestsRecyclerViewAdapter(ArrayList<FriendRequestProfile> data) {
        super(data);
    }

    @Override
    public FriendRequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FriendRequestsViewHolder holder = new FriendRequestsViewHolder(getView(parent, R.layout.list_item_friend_requests));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }

        holder.setAcceptListener(acceptListener);
        holder.setRejectListener(rejectListener);

        return holder;
    }

    public void setAcceptListener(
            IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> acceptListener) {
        this.acceptListener = acceptListener;
    }

    public void setRejectListener(
            IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> rejectListener) {
        this.rejectListener = rejectListener;
    }

    public void setOnClickListener(
            IOnClickItemListHandler<FriendRequestProfile> listener) {
        this.listener = listener;
    }

    public static class Factory {

        private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> acceptListener;

        private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> rejectListener;

        private IOnClickItemListHandler<FriendRequestProfile> listener;

        public FriendRequestsRecyclerViewAdapter make(){
            return make(new ArrayList<FriendRequestProfile>());
        }

        public FriendRequestsRecyclerViewAdapter make(ArrayList<FriendRequestProfile> data) {
            FriendRequestsRecyclerViewAdapter adapter = new FriendRequestsRecyclerViewAdapter(data);
            adapter.setRejectListener(rejectListener);
            adapter.setAcceptListener(acceptListener);
            adapter.setOnClickListener(listener);
            return adapter;
        }


        public void setAcceptListener(
                IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> acceptListener) {
            this.acceptListener = acceptListener;
        }

        public void setRejectListener(
                IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> rejectListener) {
            this.rejectListener = rejectListener;
        }

        public void setOnClickListener(
                IOnClickItemListHandler<FriendRequestProfile> listener) {
            this.listener = listener;
        }
    }
}
