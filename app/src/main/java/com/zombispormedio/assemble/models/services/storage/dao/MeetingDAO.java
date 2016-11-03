package com.zombispormedio.assemble.models.services.storage.dao;

import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.services.storage.TeamStorageService;

import android.support.annotation.NonNull;

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

    public TeamDAO team;

    public boolean bookmark;

    @NonNull
    @Override
    public Meeting toModel() {
        return new Meeting(id, name, description, created_at,
                full_image_url, large_image_url, medium_image_url, thumb_image_url, start_at, end_at, team.toModel(),
                bookmark);
    }

    @NonNull
    @Override
    public MeetingDAO fromModel(@NonNull Meeting model) {
        this.id = model.id;
        this.name = model.name;
        this.description = model.description;
        this.created_at = model.created_at;
        this.large_image_url = model.large_image_url;
        this.medium_image_url = model.medium_image_url;
        this.full_image_url = model.full_image_url;
        this.thumb_image_url = model.thumb_image_url;
        this.start_at = model.start_at;
        this.end_at = model.end_at;
        bindTeam(model.team);
        this.bookmark = model.bookmark;
        return this;
    }

    private void bindTeam(@NonNull Team team) {
        TeamStorageService service = new TeamStorageService();
        this.team = service.getStorage()
                .getById(team.id);
    }

    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<MeetingDAO> {

        @NonNull
        @Override
        public MeetingDAO create() {
            return new MeetingDAO();
        }
    }
}
