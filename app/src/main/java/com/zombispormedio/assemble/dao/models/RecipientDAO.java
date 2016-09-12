package com.zombispormedio.assemble.dao.models;

import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.models.Recipient;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class RecipientDAO extends RealmObject implements IBaseDAO<Recipient>{


    public String id;

    public String email;

    public String username;

    public String full_avatar_url;

    public String large_avatar_url;

    public String medium_avatar_url;

    public String thumb_avatar_url;


    @Override
    public Recipient toModel() {
        return null;
    }

    @Override
    public RecipientDAO fromModel(Recipient model) {
        return null;
    }
}
