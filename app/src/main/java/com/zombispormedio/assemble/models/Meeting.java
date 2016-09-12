package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class Meeting extends Concept {

    public String start_at;

    public String end_at;

    public Meeting(int id, String name, String description, String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url, String start_at, String end_at) {
        super(id, name, description, created_at, full_image_url, large_image_url, medium_image_url, thumb_image_url);
        this.start_at = start_at;
        this.end_at = end_at;
    }

    public Meeting() {
    }
}
