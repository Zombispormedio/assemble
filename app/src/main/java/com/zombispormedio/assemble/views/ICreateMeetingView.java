package com.zombispormedio.assemble.views;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public interface ICreateMeetingView extends IBaseView{

    void showTeamDialog();

    void bindTeam(String name);

    void bindStartDate(String start);

    void bindStartHour(String start);

    void bindEndDate(String start);

    void bindEndHour(String start);

    boolean getAllDay();

    void hideHours();

    void showHours();

    void setupPickers(int year, int month, int day,  int hour, int minutes);


}
