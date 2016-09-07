package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class Image {
    private final String full_image_url;
    private final String large_image_url;
    private final String medium_image_url;
    private final String thumb_image_url;

    public Image(String full_image_url, String large_image_url, String medium_image_url, String thumb_image_url) {
        this.full_image_url = full_image_url;
        this.large_image_url = large_image_url;
        this.medium_image_url = medium_image_url;
        this.thumb_image_url = thumb_image_url;
    }
}
