package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.Meeting;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public interface IMeetingsView {

    void bindMeetings(ArrayList<Meeting> data );

    void showAlert(String msg);

}