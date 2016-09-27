package com.zombispormedio.assemble.views;

import java.text.ParseException;

/**
 * Created by Xavier Serrano on 22/08/2016.
 */
public interface IBaseProfileView extends IBaseView {

    void setUsername(String name);

    void setLocation(String location);

    void setBio(String bio);

    void setBirthDate(String birth) throws ParseException;

}
