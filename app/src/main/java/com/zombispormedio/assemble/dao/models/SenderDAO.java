package com.zombispormedio.assemble.dao.models;

import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.models.Sender;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class SenderDAO extends RealmObject implements IBaseDAO<Sender> {

    public String id;

    public String email;

    public String username;

    public String full_avatar_url;

    public String large_avatar_url;

    public String medium_avatar_url;

    public String thumb_avatar_url;


    @Override
    public Sender toModel() {
        return null;
    }

    @Override
    public SenderDAO fromModel(Sender model) {
        return null;
    }
}
