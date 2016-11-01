package com.zombispormedio.assemble.models;

import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public abstract class Concept extends BaseModel {

    public final String name;

    public final String description;

    public final String full_image_url;

    public final String large_image_url;

    public final String medium_image_url;

    public final String thumb_image_url;

    @NonNull
    public final String created_at;

    @NonNull
    private final transient ISODate createdAt;


    public Concept(int id, String name, String description, @NonNull String created_at, String full_image_url,
            String large_image_url, String medium_image_url, String thumb_image_url) {
        super(id);
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.full_image_url = full_image_url;
        this.large_image_url = large_image_url;
        this.medium_image_url = medium_image_url;
        this.thumb_image_url = thumb_image_url;

        createdAt = new ISODate(created_at);
    }

    @NonNull
    public ISODate getCreatedAt() {
        return createdAt;
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

    @NonNull
    public ImageUtils.ImageBuilder getLargeImageBuilder() {
        return getImageBuilder(large_image_url);
    }

    @NonNull
    public ImageUtils.ImageBuilder getFullImageBuilder() {
        return getImageBuilder(full_image_url);
    }

    @NonNull
    public ImageUtils.ImageBuilder getMediumImageBuilder() {
        return getImageBuilder(medium_image_url);
    }

    @NonNull
    public ImageUtils.ImageBuilder getThumbImageBuilder() {
        return getImageBuilder(thumb_image_url);
    }


    @NonNull
    private ImageUtils.ImageBuilder getImageBuilder(String path) {
        ImageUtils.ImageBuilder builder = new ImageUtils.ImageBuilder();
        if (Utils.presenceOf(path)) {
            builder.url(path);
        }
        return builder
                .letter(getNameFirstLetter())
                .circle(true);
    }

    @NonNull
    public String getNameFirstLetter() {
        return StringUtils.firstLetter(name);
    }

    public boolean areContentTheSame(@NonNull Concept o) {
        return id == o.id &&
                Utils.safeEquals(name, o.name) &&
                Utils.safeEquals(description, o.description) &&
                Utils.safeEquals(full_image_url, o.full_image_url) &&
                Utils.safeEquals(large_image_url, o.large_image_url) &&
                Utils.safeEquals(medium_image_url, o.medium_image_url) &&
                Utils.safeEquals(thumb_image_url, o.thumb_image_url);
    }

}
