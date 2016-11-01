package com.zombispormedio.assemble.models;

import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.utils.Utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class Profile<T extends Profile> extends People implements Sorted<T> {

    @NonNull
    public final String birth_date;

    public final String location;

    public final String bio;

    public final String sign_up_at;

    @Nullable
    private final transient ISODate birth;

    public Profile(int id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url, @NonNull String birth_date, String location, String bio,
            String sign_up_at) {
        super(id, email, username, full_avatar_url, large_avatar_url, medium_avatar_url, thumb_avatar_url);
        this.birth_date = birth_date;
        this.location = location;
        this.bio = bio;
        this.sign_up_at = sign_up_at;

        birth = Utils.presenceOf(birth_date) ? new ISODate(birth_date) : null;
    }


    @Override
    public int compareTo(@NonNull T o) {
        return username.compareToIgnoreCase(o.username);
    }

    @Override
    public boolean areTheSame(@NonNull T o) {
        return id == o.id &&
                Utils.safeEquals(email, o.email) &&
                Utils.safeEquals(username, o.username) &&
                Utils.safeEquals(full_avatar_url, o.full_avatar_url) &&
                Utils.safeEquals(large_avatar_url, o.large_avatar_url) &&
                Utils.safeEquals(medium_avatar_url, o.medium_avatar_url) &&
                Utils.safeEquals(thumb_avatar_url, o.thumb_avatar_url) &&
                Utils.safeEquals(birth_date, o.birth_date) &&
                Utils.safeEquals(location, o.location);
    }

    @Override
    public int getIdentity() {
        return id;
    }

    @Nullable
    public ISODate getBirth() {
        return birth;
    }
}
