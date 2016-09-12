package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class FriendRequestProfileDAO extends RealmObject implements IBaseDAO<FriendRequestProfile> {

    @PrimaryKey
    public int friend_request_id;

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
    public FriendRequestProfile toModel() {
        return (FriendRequestProfile) new Utils.MergeBuilder<FriendRequestProfileDAO, FriendRequestProfile>()
                .emite(this)
                .receive(new FriendRequestProfile())
                .merge();
    }

    @Override
    public FriendRequestProfileDAO fromModel(FriendRequestProfile model) {
        return (FriendRequestProfileDAO) new Utils.MergeBuilder<FriendRequestProfile, FriendRequestProfileDAO>()
                .emite(model)
                .receive(this)
                .merge();
    }
}
