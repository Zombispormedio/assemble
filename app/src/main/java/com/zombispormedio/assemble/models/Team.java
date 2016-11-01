package com.zombispormedio.assemble.models;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class Team extends Concept implements Sorted<Team> {

    public boolean starred;

    public Team(int id, String name, String description, String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url, boolean starred) {
        super(id, name, description, created_at, full_image_url, large_image_url, medium_image_url, thumb_image_url);
        this.starred = starred;
    }

    @Override
    public boolean areTheSame(@NonNull Team o) {
        return areContentTheSame(o) && starred == o.starred;
    }

    @Override
    public int getIdentity() {
        return id;
    }

    @Override
    public int compareTo(@NonNull Team o) {
        int result = name.compareToIgnoreCase(o.name);
        if (starred != o.starred) {
            result = starred ? -1 : 1;
        }

        return result;
    }
}
