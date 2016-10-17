package com.zombispormedio.assemble.models;


import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class Message extends BaseModel implements Parcelable, Sorted<Message> {

    public int sender_id;

    public int recipient_id;

    public int chat_id;

    public final Profile sender;

    public Profile recipient;

    public final String content;

    public final boolean is_read;

    public final boolean is_sent;

    public final boolean is_delivered;

    public final String created_at;

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
        this.chat_id = chat_id;
    }

    public Message(String content, Profile sender, String created_at) {
        super(0);
        this.sender = sender;
        this.content = content;
        this.is_read = false;
        this.is_sent = false;
        this.is_delivered = false;
        this.created_at = created_at;
    }

    public Message(int id, String content, boolean is_read, boolean is_sent, boolean is_delivered, String created_at,
            int sender_id, int recipient_id, int chat_id) {
        super(id);
        this.content = content;
        this.is_read = is_read;
        this.is_sent = is_sent;
        this.is_delivered = is_delivered;
        this.created_at = created_at;
        this.sender = null;
        this.recipient = null;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.chat_id = chat_id;
    }


    protected Message(Parcel in) {
        super(in.readInt());
        content = in.readString();
        is_read = in.readByte() != 0;
        is_sent = in.readByte() != 0;
        is_delivered = in.readByte() != 0;
        created_at = in.readString();
        sender_id = in.readInt();
        recipient_id = in.readInt();
        chat_id = in.readInt();
        this.sender = null;
        this.recipient = null;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getLimitedContent(int limit) {
        return content.length() > limit ? ellipseContent(limit) : content;
    }

    private String ellipseContent(int limit) {
        return StringUtils.ellipse(content, limit);
    }

    public boolean isCreatedToday() {
        return DateUtils.isToday(created_at);
    }

    public boolean isCreatedYesterday() {
        return DateUtils.isYesterday(created_at);
    }

    public String formatCreated(String format) {
        return DateUtils.formatISODate(format, created_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(content);
        dest.writeByte((byte) (is_read ? 1 : 0));
        dest.writeByte((byte) (is_sent ? 1 : 0));
        dest.writeByte((byte) (is_delivered ? 1 : 0));
        dest.writeString(created_at);
        dest.writeInt(sender_id);
        dest.writeInt(recipient_id);
        dest.writeInt(chat_id);
    }

    @Override
    public int compareTo(Message o) {
        return DateUtils.compareISODateString(created_at, o.created_at);
    }

    @Override
    public boolean areTheSame(Message obj) {
        return obj != null && id == obj.id;
    }

    public static class Builder {

        public int id;

        public int sender_id;

        public int recipient_id;

        public int chat_id;

        public String content;

        public boolean is_read;

        public boolean is_sent;

        public boolean is_delivered;

        public String created_at;


        public void setId(int id) {
            this.id = id;
        }


        public void setSenderId(int sender_id) {
            this.sender_id = sender_id;
        }

        public void setRecipientId(int recipient_id) {
            this.recipient_id = recipient_id;
        }

        public void setChatId(int chat_id) {
            this.chat_id = chat_id;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setIsRead(boolean is_read) {
            this.is_read = is_read;
        }

        public void setIsSent(boolean is_sent) {
            this.is_sent = is_sent;
        }

        public void setIsDelivered(boolean is_delivered) {
            this.is_delivered = is_delivered;
        }

        public void setCreatedAt(String created_at) {
            this.created_at = created_at;
        }


        public Message build() {
            return new Message(id, content, is_read, is_sent, is_delivered, created_at,
                    sender_id, recipient_id, chat_id);
        }

    }


    public static Message createMessage(HashMap<String, String> messageMap) {
        Builder builder = new Builder();
        for (String key : messageMap.keySet()) {
            String value = messageMap.get(key);
            switch (key) {
                case "id":
                    builder.setId(Integer.parseInt(value));
                    break;

                case "sender_id":
                    builder.setSenderId(Integer.parseInt(value));
                    break;

                case "recipient_id":
                    builder.setRecipientId(Integer.parseInt(value));
                    break;

                case "chat_id":
                    builder.setChatId(Integer.parseInt(value));
                    break;

                case "content":
                    builder.setContent(value);
                    break;

                case "is_read":
                    builder.setIsRead(Boolean.parseBoolean(value));
                    break;

                case "is_sent":
                    builder.setIsSent(Boolean.parseBoolean(value));
                    break;

                case "is_delivered":
                    builder.setIsDelivered(Boolean.parseBoolean(value));
                    break;

                case "created_at":
                    builder.setCreatedAt(value);
                    break;
            }
        }

        return builder.build();
    }
}
