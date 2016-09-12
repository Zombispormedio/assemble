package com.zombispormedio.assemble.dao.models;

import com.zombispormedio.assemble.dao.IBaseDAO;
import com.zombispormedio.assemble.models.Team;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class TeamDAO extends RealmObject implements IBaseDAO<Team>{

    public String id;

    public String name;

    public String description;

    public String created_at;

    public String full_image_url;

    public String large_image_url;

    public String medium_image_url;

    public String thumb_image_url;


    @Override
    public Team toModel() {
        return null;
    }

    @Override
    public TeamDAO fromModel(Team model) {
        return null;
    }
}
