package com.zombispormedio.assemble.models.services.storage.dao;

import com.zombispormedio.assemble.models.Team;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class TeamDAO extends RealmObject implements IBaseDAO<Team> {

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

    public boolean starred;


    @NonNull
    @Override
    public Team toModel() {
        return new Team(id, name, description, created_at,
                full_image_url, large_image_url, medium_image_url, thumb_image_url, starred);
    }

    @NonNull
    @Override
    public TeamDAO fromModel(@NonNull Team model) {
        this.id = model.id;
        this.name = model.name;
        this.description = model.description;
        this.created_at = model.created_at;
        this.large_image_url = model.large_image_url;
        this.medium_image_url = model.medium_image_url;
        this.full_image_url = model.full_image_url;
        this.thumb_image_url = model.thumb_image_url;
        this.starred = model.starred;
        return this;
    }

    @Override
    public int getId() {
        return id;
    }


    public static class Factory implements IDAOFactory<TeamDAO> {

        @NonNull
        @Override
        public TeamDAO create() {
            return new TeamDAO();
        }
    }
}
