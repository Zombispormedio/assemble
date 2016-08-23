package com.zombispormedio.assemble.views;

/**
 * Created by Xavier Serrano on 04/08/2016.
 */
public interface IUpdateProfileView extends IBaseProfileView {

    String getUsername();

    String getBio();

    String getLocation();

    void goToUpdateBirthdate(String... args);

}
