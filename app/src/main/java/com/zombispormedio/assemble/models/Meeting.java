package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class Meeting extends Imageable {

    public final String id;
    public final String name;
    public final String description;
    public final String created_at;

    public final String start_at;
    public final String end_at;

    public Meeting(String id, String name, String description, String created_at, String start_at, String end_at, String full_image_url, String large_image_url,
            String medium_image_url, String thumb_image_url) {
        super(full_image_url, large_image_url,
                medium_image_url, thumb_image_url);
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.start_at = start_at;
        this.end_at = end_at;
    }
}
