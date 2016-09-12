package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public abstract class Concept extends BaseModel {

    public int id;

    public String name;

    public String description;

    public String created_at;

    public String full_image_url;

    public String large_image_url;

    public String medium_image_url;

    public String thumb_image_url;

    public Concept(int id, String name, String description, String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.full_image_url = full_image_url;
        this.large_image_url = large_image_url;
        this.medium_image_url = medium_image_url;
        this.thumb_image_url = thumb_image_url;
    }

    public Concept() {
        this.id = 0;
        this.name = "";
    }
}
