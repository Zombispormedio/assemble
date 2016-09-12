package com.zombispormedio.assemble.dao.models;

import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.models.UserProfile;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class UserProfileDAO extends RealmObject implements IBaseDAO<UserProfile>{

    @PrimaryKey
    public String id;

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



    @Override
    public UserProfile toModel() {
        return null;
    }

    @Override
    public UserProfileDAO fromModel(UserProfile model) {
        return null;
    }
}
