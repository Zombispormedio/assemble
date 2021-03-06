package com.zombispormedio.assemble.views.activities;

import com.zombispormedio.assemble.utils.ISODate;


/**
 * Created by Xavier Serrano on 22/08/2016.
 */
public interface IBaseProfileView extends IBaseView {

    void setUsername(String name);

    void setLocation(String location);

    void setBio(String bio);

    void setBirthDate(ISODate birth, String def);

}
