package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.FriendRequestsHolder;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.views.holders.IFriendRequestHolder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsListAdapter
        extends BaseSortedListAdapter<FriendRequestProfile, FriendRequestsHolder> {

    private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> acceptListener;

    private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> rejectListener;

    private IOnClickItemListHandler<FriendRequestProfile> listener;


    public FriendRequestsListAdapter(@NonNull ArrayList<FriendRequestProfile> data) {
        super(FriendRequestProfile.class);
        addAll(data);
    }

    @NonNull
    @Override
    public FriendRequestsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FriendRequestsHolder holder = new FriendRequestsHolder(getView(parent, R.layout.list_item_friend_requests));
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

        @NonNull
        public FriendRequestsListAdapter make() {
            return make(new ArrayList<>());
        }

        @NonNull
        public FriendRequestsListAdapter make(@NonNull ArrayList<FriendRequestProfile> data) {
            FriendRequestsListAdapter adapter = new FriendRequestsListAdapter(data);
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
