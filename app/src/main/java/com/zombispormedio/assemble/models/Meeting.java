package com.zombispormedio.assemble.models;

import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.Utils;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class Meeting extends Concept implements Sorted<Meeting> {

    public final String start_at;

    public final String end_at;

    public final Team team;

    public Meeting(int id, String name, String description, String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url, String start_at, String end_at,
            Team team) {
        super(id, name, description, created_at, full_image_url, large_image_url, medium_image_url, thumb_image_url);
        this.start_at = start_at;
        this.end_at = end_at;
        this.team = team;
    }

    public int compareTo(Meeting o) {
        int result = DateUtils.compareISODateString(start_at, o.start_at);
        if (result == 0) {
            result = name.compareToIgnoreCase(name);
        }

        return result;
    }


    @Override
    public boolean areTheSame(Meeting o) {
        return team.id == o.team.id &&
                areContentTheSame(o) &&
                Utils.safeEquals(start_at,o.start_at) &&
                Utils.safeEquals(end_at,o.end_at);
    }


}
