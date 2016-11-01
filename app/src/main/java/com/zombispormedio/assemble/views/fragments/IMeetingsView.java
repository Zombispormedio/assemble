package com.zombispormedio.assemble.views.fragments;

import com.zombispormedio.assemble.models.Meeting;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public interface IMeetingsView extends IFragmentView {

    void bindMeetings(ArrayList<Meeting> data);

    void finishRefresh();

}
