package com.zombispormedio.assemble.dao.models;

import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.models.Chat;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ChatDAO  extends RealmObject implements IBaseDAO<Chat>{

    public String id;

    public String created_at;

    public SenderDAO sender;

    public RecipientDAO recipient;


    @Override
    public Chat toModel() {
        return null;
    }

    @Override
    public ChatDAO fromModel(Chat model) {
        return null;
    }
}
