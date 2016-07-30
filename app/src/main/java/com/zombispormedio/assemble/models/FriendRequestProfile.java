package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class FriendRequestProfile  extends Profile {
    public final String friend_request_id;

    public FriendRequestProfile(String friend_request_id, String email, String username, String birth_date, String location, String bio, String sign_up_at, String full_avatar_url, String large_avatar_url, String medium_avatar_url, String thumb_avatar_url) {
        super(email,  username,  birth_date,  location,  bio, sign_up_at, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);

        this.friend_request_id = friend_request_id;
    }
}
