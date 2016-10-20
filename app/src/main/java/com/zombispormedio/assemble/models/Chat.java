package com.zombispormedio.assemble.models;


import com.zombispormedio.assemble.utils.ISODate;


/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class Chat extends BaseModel implements Sorted<Chat> {

    public final UserProfile sender;

    public final FriendProfile recipient;

    public final Message lastMessage;

    public int owner_id;

    public int friend_id;

    public final String created_at;

    private final transient ISODate createdAt;


    public Chat(int id, String created_at, UserProfile sender, FriendProfile recipient,
            Message lastMessage) {
        super(id);
        this.created_at = created_at;
        this.sender = sender;
        this.recipient = recipient;
        this.lastMessage = lastMessage;

        createdAt = new ISODate(created_at);
    }

    public ISODate getCreatedAt() {
        return createdAt;
    }

    @Override
    public int compareTo(Chat o) {
        int result;
        Message m2 = o.lastMessage;
        ISODate createAt = getCreatedAt();

        if (m2 == null && lastMessage == null) {
            result = createAt.compareTo(o.getCreatedAt());
        } else {
            if (m2 == null) {
                result = lastMessage.getCreatedAt().compareTo(o.getCreatedAt());
            } else if (lastMessage == null) {
                result = createAt.compareTo(m2.getCreatedAt());
            } else {
                result = lastMessage.compareTo(m2);
            }
        }
        return result;
    }

    @Override
    public boolean areTheSame(Chat o) {
        return id == o.id &&
                safeCompareLastMessage(o.lastMessage);
    }

    @Override
    public int getIdentity() {
        return id;
    }

    private boolean safeCompareLastMessage(Message last2) {
        boolean comp = lastMessage != null;
        if (comp) {
            comp = lastMessage.areTheSame(last2);
        }
        return comp;
    }
}
