package com.zombispormedio.assemble.models;

import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.utils.Utils;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class Meeting extends Concept implements Sorted<Meeting> {

    public final String start_at;

    public final String end_at;

    public final Team team;

    public boolean bookmark;

    private final transient ISODate startAt;

    private final transient ISODate endAt;



    public Meeting(int id, String name, String description, String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url, String start_at, String end_at,
            Team team, boolean bookmark) {
        super(id, name, description, created_at, full_image_url, large_image_url, medium_image_url, thumb_image_url);
        this.start_at = start_at;
        this.end_at = end_at;
        this.team = team;
        this.bookmark=bookmark;

        startAt=new ISODate(start_at);

        endAt=new ISODate(end_at);
    }

    public ISODate getStartAt() {
        return startAt;
    }

    public ISODate getEndAt() {
        return endAt;
    }

    public int compareTo(@NonNull Meeting o) {
        int result = getStartAt().compareTo(o.getStartAt());
        if (result == 0) {
            result = name.compareToIgnoreCase(o.name);
        }
        if(bookmark!=o.bookmark){
            result=bookmark?-1:1;
        }

        return result;
    }


    @Override
    public boolean areTheSame(Meeting o) {
        return team.id == o.team.id &&
                areContentTheSame(o) &&
                Utils.safeEquals(start_at,o.start_at) &&
                Utils.safeEquals(end_at,o.end_at) &&
                bookmark==o.bookmark;
    }

    @Override
    public int getIdentity() {
        return id;
    }


}
