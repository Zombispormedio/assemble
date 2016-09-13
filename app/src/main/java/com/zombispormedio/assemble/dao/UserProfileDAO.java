package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class UserProfileDAO extends RealmObject implements IBaseDAO<UserProfile> {

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


    @Override
    public UserProfile toModel() {
        return new UserProfile(id, email, username,
                full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url,
                birth_date, location, bio, sign_up_at);
    }

    @Override
    public UserProfileDAO fromModel(UserProfile model) {
        return (UserProfileDAO) new Utils.MergeBuilder<UserProfile, UserProfileDAO>()
                .emite(model)
                .receive(this)
                .merge();
    }

    public static class Factory implements IDAOFactory<UserProfileDAO>{

        @Override
        public UserProfileDAO create() {
            return new UserProfileDAO();
        }
    }
}
