package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.FriendProfile;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 25/09/2016.
 */
public interface ISecondStepTeamView  extends IBaseView{

    void setParticipantsTitle(int number, int total);

    String getName();

    void bindParticipants(ArrayList<FriendProfile> data);

}
