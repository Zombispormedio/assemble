package com.zombispormedio.assemble.views.activities;

/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public interface IUpdateBirthdateView extends IBaseView {

    void finishWithResult(String... result);

    void setMaxDate(long time);

    int getDayOfBirthdate();

    int getMonthOfBirthdate();

    int getYearOfBirthdate();

    void setDatepickerValue(int year, int month, int day);

    String getInitBirthdate();

}
