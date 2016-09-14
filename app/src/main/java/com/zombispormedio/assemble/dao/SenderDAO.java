package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Sender;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class SenderDAO extends RealmObject implements IBaseDAO<Sender> {

    @PrimaryKey
    public int id;

    @Index
    public String email;

    @Index
    public String username;

    public String full_avatar_url;

    public String large_avatar_url;

    public String medium_avatar_url;

    public String thumb_avatar_url;


    @Override
    public Sender toModel(){
        return new Sender(id, email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);
    }

    @Override
    public SenderDAO fromModel(Sender model) {
        this.id = model.id;
        this.email = model.email;
        this.username = model.email;
        this.large_avatar_url = model.large_avatar_url;
        this.medium_avatar_url = model.medium_avatar_url;
        this.full_avatar_url = model.full_avatar_url;
        this.thumb_avatar_url = model.thumb_avatar_url;

        return this;
    }

    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<MeetingDAO>{

        @Override
        public MeetingDAO create() {
            return new MeetingDAO();
        }
    }
}
