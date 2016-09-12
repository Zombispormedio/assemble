package com.zombispormedio.assemble.dao.models;

import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.models.FriendProfile;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class FriendProfileDAO extends RealmObject implements IBaseDAO<FriendProfile>{

    public String friend_id;

    public String email;

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
    public FriendProfile toModel() {
        return null;
    }

    @Override
    public FriendProfileDAO fromModel(FriendProfile model) {
        return null;
    }
}
