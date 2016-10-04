package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class ContentMessage  extends BaseModel {

    public String content;

    public boolean is_read;

    public boolean is_sent;

    public boolean is_delivered;

    public String created_at;

    public ContentMessage(int id, String content, boolean is_read, boolean is_sent, boolean is_delivered,
            String created_at) {
        super(id);
        this.content = content;
        this.is_read = is_read;
        this.is_sent = is_sent;
        this.is_delivered = is_delivered;
        this.created_at = created_at;
    }
}
