package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class Team extends Imageable{
    public final String id;
    public final String name;
    public final String description;
    public final String created_at;


    public Team(String id, String name, String description, String created_at, String full_image_url, String large_image_url,
            String medium_image_url, String thumb_image_url) {
        super(full_image_url, large_image_url,
                medium_image_url, thumb_image_url);
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
    }
}
