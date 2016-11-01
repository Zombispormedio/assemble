package com.zombispormedio.assemble.views.activities;

import com.zombispormedio.assemble.utils.ISODate;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public interface ICreateMeetingView extends IBaseView {

    void showTeamDialog();

    void bindTeam(String name);

    void bindStartDate(ISODate start);

    void bindStartHour(ISODate start);

    void bindEndDate(ISODate start);

    void bindEndHour(ISODate start);

    boolean getAllDay();

    void hideHours();

    void showHours();

    void setupStartDate(int year, int month, int day);

    void setupEndDate(int year, int month, int day);

    void setupStartHour(int hour, int minutes);

    void setupEndHour(int hour, int minutes);

    void updateStartDate(int year, int month, int day);

    void updateEndDate(int year, int month, int day);

    void updateStartHour(int hour, int minutes);

    void updateEndHour(int hour, int minutes);

    void showErrorEndDate();

    void hideErrorEndDate();

    void bindImage(String path);

    void showProgress();

    void hideProgress();

    void goHome();

    String getName();

    void showDateErrorAlert();
}
