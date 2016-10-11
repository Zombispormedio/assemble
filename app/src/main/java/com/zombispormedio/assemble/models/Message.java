package com.zombispormedio.assemble.models;


import com.zombispormedio.assemble.utils.DateUtils;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class Message extends BaseModel {

    public int sender_id;

    public int recipient_id;

    public int chat_id;

    public Profile sender;

    public Profile recipient;

    public String content;

    public boolean is_read;

    public boolean is_sent;

    public boolean is_delivered;

    public String created_at;

    public Message(int id, String content, boolean is_read, boolean is_sent, boolean is_delivered, String created_at,
            Profile sender, Profile recipient, int chat_id) {
        super(id);
        this.content = content;
        this.is_read = is_read;
        this.is_sent = is_sent;
        this.is_delivered = is_delivered;
        this.created_at = created_at;
        this.sender = sender;
        this.recipient = recipient;
        this.chat_id=chat_id;
    }

    public Message(String content, Profile sender) {
        super(0);
        this.sender = sender;
        this.content=content;
        this.is_read=false;
        this.is_sent = false;
        this.is_delivered = false;
    }


    public String getLimitedContent(int limit){
        return content.length() > 30?ellipseContent(limit):content;
    }

    private String ellipseContent(int limit){
        return content.substring(0, limit) + "…";
    }


    public boolean isCreatedToday(){
        return DateUtils.isToday(created_at);
    }

    public boolean isCreatedYesterday(){
        return DateUtils.isYesterday(created_at);
    }

    public String formatCreated(String format){
        return DateUtils.format(format, created_at);
    }


}
