package com.zombispormedio.assemble.wrappers.realm.dao;

import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Profile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.services.storage.FriendStorageService;
import com.zombispormedio.assemble.models.services.storage.ProfileStorageService;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class MessageDAO extends RealmObject implements IBaseDAO<Message> {

    @PrimaryKey
    public int id;

    public String created_at;

    public int sender_id;

    public int recipient_id;

    public String content;

    public boolean is_read;

    public boolean is_sent;

    public boolean is_delivered;

    public int chat_id;


    @NonNull
    @Override
    public Message toModel() {
        Profile sender, recipient;

        ProfileStorageService profileService = new ProfileStorageService();

        FriendStorageService friendService = new FriendStorageService();

        UserProfile userProfile = profileService.getFirst();

        if (userProfile.id == sender_id) {
            sender = userProfile;
            recipient = friendService.getByID(recipient_id);
        } else {
            recipient = userProfile;
            sender = friendService.getByID(sender_id);
        }

        return new Message(id, content,
                is_read, is_sent, is_delivered, created_at,
                sender, recipient, chat_id);
    }

    @NonNull
    @Override
    public IBaseDAO fromModel(@NonNull Message model) {
        this.id = model.id;
        this.created_at = model.created_at;
        this.sender_id = model.sender_id;
        this.recipient_id = model.recipient_id;
        this.content = model.content;
        this.is_read = model.is_read;
        this.is_sent = model.is_sent;
        this.is_delivered = model.is_delivered;
        this.chat_id = model.chat_id;
        return this;
    }


    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<MessageDAO> {

        @NonNull
        @Override
        public MessageDAO create() {
            return new MessageDAO();
        }
    }
}
