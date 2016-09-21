package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public interface IFirstStepTeamView extends IBaseView {

    void setParticipantsSubtitle(int number, int total);

    void setDefaultSubtitle();

    void bindFriends(ArrayList<FriendProfile> friends);

    void addMember(FriendProfile member);

    void removeMember(int position);


}
