package com.zombispormedio.assemble.dao.models;

import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.models.Meeting;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class MeetingDAO extends RealmObject implements IBaseDAO<Meeting> {
    public String id;

    public String name;

    public String description;

    public String created_at;

    public String full_image_url;

    public String large_image_url;

    public String medium_image_url;

    public String thumb_image_url;

    public String start_at;

    public String end_at;

    @Override
    public Meeting toModel() {
        return null;
    }

    @Override
    public MeetingDAO fromModel(Meeting model) {
        return null;
    }
}
