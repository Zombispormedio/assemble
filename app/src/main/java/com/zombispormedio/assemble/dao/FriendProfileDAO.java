package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class FriendProfileDAO extends RealmObject implements IBaseDAO<FriendProfile>{

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
    public FriendProfile toModel() {
        return  new FriendProfile(id, email, username,
                full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url,
                birth_date, location, bio, sign_up_at);
    }

    @Override
    public FriendProfileDAO fromModel(FriendProfile model)  {
        return (FriendProfileDAO) new Utils.MergeBuilder<FriendProfile, FriendProfileDAO>()
                .emite(model)
                .receive(this)
                .merge();
    }

    public static class Factory implements IDAOFactory<FriendProfileDAO>{

        @Override
        public FriendProfileDAO create() {
            return new FriendProfileDAO();
        }
    }
}
