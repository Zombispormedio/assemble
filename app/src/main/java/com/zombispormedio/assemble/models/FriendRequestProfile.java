package com.zombispormedio.assemble.models;

import io.realm.RealmModel;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class FriendRequestProfile extends Profile{

    public int friend_request_id;

    public FriendRequestProfile(int friend_request_id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url, String birth_date, String location, String bio,
            String sign_up_at) {
        super(email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url, birth_date, location,
                bio,
                sign_up_at);
        this.friend_request_id = friend_request_id;
    }

    public FriendRequestProfile() {
        this.friend_request_id = 0;
    }
}
