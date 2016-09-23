package com.zombispormedio.assemble.adapters.lists;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.SelectedMemberHolder;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.ISelectedMember;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Xavier Serrano on 23/09/2016.
 */

public class SelectedMembersListAdapter extends BaseListAdapter<
        SelectedMemberHolder.Container, SelectedMemberHolder> {

    private IOnClickComponentItemHandler<SelectedMemberHolder.Container, ISelectedMember> listener;

    private ArrayList<Integer> friendIndexes;

    public SelectedMembersListAdapter() {
        super.setData(new ArrayList<SelectedMemberHolder.Container>());
        friendIndexes=new ArrayList<>();
    }



    @Override
    public SelectedMemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SelectedMemberHolder holder = new    SelectedMemberHolder(getView(parent, R.layout.list_item_selected_members));
        holder.setOnClickListener(listener);
        return holder;
    }

    public void setOnClickListener(
            IOnClickComponentItemHandler<SelectedMemberHolder.Container, ISelectedMember> listener) {
        this.listener = listener;
    }

    public void addMember(FriendProfile content, int friendIndex){
        if(!friendIndexes.contains(friendIndex)){
            int index=data.size();
            data.add(new SelectedMemberHolder.Container(content, friendIndex));
            friendIndexes.add(index,friendIndex);
            notifyItemInserted(index);
        }

    }

    public void removeMember(int index){
        data.remove(index);
        friendIndexes.remove(index);
        notifyItemRemoved(index);
    }

    public void removeMemberByFriend(int friendIndex){
        int index=findIndexByFriend(friendIndex);
        if(index>-1){
            data.remove(index);
            friendIndexes.remove(index);
            notifyItemRemoved(index);
        }
    }

    private int findIndexByFriend(int friendIndex){
        int index=-1;
        if(friendIndexes.contains(friendIndex)){
            int i=0;

            while(i<data.size()&&index==-1){
                SelectedMemberHolder.Container t=data.get(i);
                if(t.getFriendIndex()==friendIndex){
                    index=i;
                }
                i++;
            }

        }

        return index;
    }
}
