package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Recipient;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class RecipientDAO extends RealmObject implements IBaseDAO<Recipient> {

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
    public Recipient toModel(){
        return  new Recipient(id, email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);
    }

    @Override
    public RecipientDAO fromModel(Recipient model) {
        this.id = model.id;
        this.email = model.email;
        this.username = model.username;
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
