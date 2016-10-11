package com.zombispormedio.assemble.models;

import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public abstract class Concept extends BaseModel {


    public String name;

    public String description;

    public String created_at;

    public String full_image_url;

    public String large_image_url;

    public String medium_image_url;

    public String thumb_image_url;

    public Concept(int id, String name, String description, String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url) {
        super(id);
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.full_image_url = full_image_url;
        this.large_image_url = large_image_url;
        this.medium_image_url = medium_image_url;
        this.thumb_image_url = thumb_image_url;
    }

    public Concept() {
        super(0);
        this.name = "";
    }


    public boolean haveMediumImage() {
        return have(medium_image_url);
    }

    public boolean haveLargeImage() {
        return have(large_image_url);
    }

    public boolean haveFullImage() {
        return have(full_image_url);
    }

    public boolean haveThumbImage() {
        return have(thumb_image_url);
    }

    private boolean have(String path) {
        return Utils.presenceOf(path);
    }


    public ImageUtils.ImageBuilder getLargeImageBuilder() {
        return getImageBuilder(large_image_url);
    }

    public ImageUtils.ImageBuilder getFullImageBuilder() {
        return getImageBuilder(full_image_url);
    }

    public ImageUtils.ImageBuilder getMediumImageBuilder() {
        return getImageBuilder(medium_image_url);
    }

    public ImageUtils.ImageBuilder getThumbImageBuilder() {
        return getImageBuilder(thumb_image_url);
    }


    private ImageUtils.ImageBuilder getImageBuilder(String path) {
        ImageUtils.ImageBuilder builder = new ImageUtils.ImageBuilder();
        if (Utils.presenceOf(path)) {
            builder.url(path);
        }
        return builder
                .letter(getNameFirstLetter())
                .circle(true);
    }

    public String getNameFirstLetter() {
        return StringUtils.firstLetter(name);
    }

}
