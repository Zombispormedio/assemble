package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.storage.FriendStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 03/10/2016.
 */

public class MessageDAO extends RealmObject implements IBaseDAO<Message> {

    @PrimaryKey
    public int id;

    public String created_at;

    public UserProfileDAO sender;

    public FriendProfileDAO recipient;

    public String content;

    public boolean is_read;

    public boolean is_sent;

    public boolean is_delivered;


    @Override
    public Message toModel() {
        return new Message(id, created_at, sender.toModel(), recipient.toModel(),
                content, is_read, is_sent, is_delivered);
    }

    @Override
    public IBaseDAO fromModel(Message model) {
        this.id=model.id;
        this.created_at=model.created_at;
        bindUser(model.sender);
        bindFriend(model.recipient);
        this.content=model.content;
        this.is_read=model.is_read;
        this.is_sent=model.is_sent;
        this.is_delivered=model.is_delivered;
        return this;
    }

    private void bindUser(UserProfile profile){
        ProfileStorageService service=new ProfileStorageService();
        this.sender =service.getStorage()
                .getById(profile.id);
    }

    private void bindFriend(FriendProfile profile){
        FriendStorageService service=new FriendStorageService();
        this.recipient =service.getStorage()
                .getById(profile.id);
    }

    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<MessageDAO>{
        @Override
        public MessageDAO create() {
            return new MessageDAO();
        }
    }
}
