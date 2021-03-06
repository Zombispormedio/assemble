package com.zombispormedio.assemble.models.services.storage.dao;

import com.zombispormedio.assemble.models.FriendProfile;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class FriendProfileDAO extends RealmObject implements IBaseDAO<FriendProfile> {

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

    public String birth_date;

    public String location;

    public String bio;

    public String sign_up_at;

    public boolean in_request;


    @NonNull
    @Override
    public FriendProfile toModel() {
        return new FriendProfile(id, email, username,
                full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url,
                birth_date, location, bio, sign_up_at, in_request);
    }

    @NonNull
    @Override
    public FriendProfileDAO fromModel(@NonNull FriendProfile model) {
        this.id = model.id;
        this.email = model.email;
        this.username = model.username;
        this.large_avatar_url = model.large_avatar_url;
        this.medium_avatar_url = model.medium_avatar_url;
        this.full_avatar_url = model.full_avatar_url;
        this.thumb_avatar_url = model.thumb_avatar_url;
        this.birth_date = model.birth_date;
        this.location = model.location;
        this.bio = model.bio;
        this.sign_up_at = model.sign_up_at;
        this.in_request = model.in_request;
        return this;
    }

    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<FriendProfileDAO> {

        @NonNull
        @Override
        public FriendProfileDAO create() {
            return new FriendProfileDAO();
        }
    }
}
