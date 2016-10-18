package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class Team extends Concept implements Sorted<Team> {

    public Team(int id, String name, String description, String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url) {
        super(id, name, description, created_at, full_image_url, large_image_url, medium_image_url, thumb_image_url);
    }

    @Override
    public boolean areTheSame(Team o) {
        return areContentTheSame(o);
    }

    @Override
    public int getIdentity() {
        return id;
    }

    @Override
    public int compareTo(Team o) {
        int result=name.compareToIgnoreCase(o.name);
        return result;
    }
}
