package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class TeamDAO extends RealmObject implements IBaseDAO<Team>{

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


    @Override
    public Team toModel() {
        return new Team(id, name, description, created_at,
                full_image_url, large_image_url, medium_image_url, thumb_image_url);
    }

    @Override
    public TeamDAO fromModel(Team model) {
        return null;
    }
}
