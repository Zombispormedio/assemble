package com.zombispormedio.assemble.models;


import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class People extends BaseModel {

    public String email;

    public String username;

    public String full_avatar_url;

    public String large_avatar_url;

    public String medium_avatar_url;

    public String thumb_avatar_url;

    public People(int id, String email, String username, String full_avatar_url, String large_avatar_url,
            String medium_avatar_url, String thumb_avatar_url) {
        super(id);
        this.email = email;
        this.username = username;
        this.full_avatar_url = full_avatar_url;
        this.large_avatar_url = large_avatar_url;
        this.medium_avatar_url = medium_avatar_url;
        this.thumb_avatar_url = thumb_avatar_url;
    }

    public People() {
        super(0);
        this.email = "";
        this.username = "";
    }

    public ImageUtils.ImageBuilder getLargeImageBuilder() {
        return getImageBuilder(large_avatar_url);
    }

    public ImageUtils.ImageBuilder getFullImageBuilder() {
        return getImageBuilder(full_avatar_url);
    }

    public ImageUtils.ImageBuilder getMediumImageBuilder() {
        return getImageBuilder(medium_avatar_url);
    }

    public ImageUtils.ImageBuilder getThumbImageBuilder() {
        return getImageBuilder(thumb_avatar_url);
    }


    private ImageUtils.ImageBuilder getImageBuilder(String path) {
        ImageUtils.ImageBuilder builder = new ImageUtils.ImageBuilder();
        if (Utils.presenceOf(path)) {
            builder.url(path);
        }
        return builder
                .letter(getUsernameFirstLetter())
                .circle(true);
    }

    private String getUsernameFirstLetter() {
        return StringUtils.firstLetter(username);
    }

    public String getLimitedUsername(int limit){
        return username.length() > limit?ellipseContent(limit):username;
    }

    private String ellipseContent(int limit){
        return StringUtils.ellipse(username, limit);
    }
}
