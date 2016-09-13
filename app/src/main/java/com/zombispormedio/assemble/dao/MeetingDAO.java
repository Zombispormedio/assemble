package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class MeetingDAO extends RealmObject implements IBaseDAO<Meeting> {

    @PrimaryKey
    public int id;

    @Index
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
    public Meeting toModel(){
        return new Meeting(id, name, description, created_at,
                full_image_url, large_image_url, medium_image_url, thumb_image_url, start_at, end_at);
    }

    @Override
    public MeetingDAO fromModel(Meeting model) {
        return (MeetingDAO) new Utils.MergeBuilder<Meeting, MeetingDAO>()
                .emite(model)
                .receive(this)
                .merge();
    }

    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<MeetingDAO>{

        @Override
        public MeetingDAO create() {
            return new MeetingDAO();
        }
    }
}
