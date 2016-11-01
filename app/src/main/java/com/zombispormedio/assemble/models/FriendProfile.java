package com.zombispormedio.assemble.models;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class FriendProfile extends Profile<FriendProfile> {


    public final boolean in_request;

    public FriendProfile(int id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url, @NonNull String birth_date, String location, String bio,
            String sign_up_at, boolean in_request) {
        super(id, email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url, birth_date,
                location,
                bio,
                sign_up_at);

        this.in_request = in_request;
    }

    @Override
    public boolean areTheSame(@NonNull FriendProfile o) {
        return super.areTheSame(o) && in_request == o.in_request;
    }
}
