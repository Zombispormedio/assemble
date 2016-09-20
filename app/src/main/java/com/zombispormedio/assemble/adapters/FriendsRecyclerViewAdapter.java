package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.IFriendHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 25/08/2016.
 */
public class FriendsRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<FriendProfile, FriendViewHolder> {

    private IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener;

    public FriendsRecyclerViewAdapter(ArrayList<FriendProfile> data) {
        super(data);
    }


    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        FriendViewHolder holder = new FriendViewHolder(getView(parent, R.layout.list_item_friends));
        if (listener != null) {
            holder.setOnClickListener(listener);
        }

        holder.setRemoveButtonListener(removeButtonListener);
        return holder;
    }

    public void setRemoveButtonListener(
            IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener) {
        this.removeButtonListener = removeButtonListener;
    }

    public static class Factory extends BaseRecyclerViewAdapter.Factory<FriendsRecyclerViewAdapter, FriendProfile>{

        private IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener;

        public FriendsRecyclerViewAdapter make(){
            return  make(new ArrayList<FriendProfile>());
        }

        public FriendsRecyclerViewAdapter make(ArrayList<FriendProfile> data) {
            FriendsRecyclerViewAdapter adapter = new FriendsRecyclerViewAdapter(data);
            adapter.setRemoveButtonListener(removeButtonListener);
            return super.make(adapter);
        }

        public void setRemoveButtonListener(
                IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener) {
            this.removeButtonListener = removeButtonListener;
        }
    }


}
