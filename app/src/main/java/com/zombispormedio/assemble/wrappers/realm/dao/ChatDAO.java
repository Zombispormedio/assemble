package com.zombispormedio.assemble.wrappers.realm.dao;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.services.storage.FriendStorageService;
import com.zombispormedio.assemble.models.services.storage.MessageStorageService;
import com.zombispormedio.assemble.models.services.storage.ProfileStorageService;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ChatDAO extends RealmObject implements IBaseDAO<Chat> {

    @PrimaryKey
    public int id;

    public String created_at;

    public UserProfileDAO sender;

    public FriendProfileDAO recipient;

    @Override
    public Chat toModel() {
        MessageStorageService messageStorageService=new MessageStorageService();

        Message message=messageStorageService.getLastMessage(id);

        return new Chat(id, created_at, sender.toModel(), recipient.toModel(), message);
    }

    @Override
    public ChatDAO fromModel(Chat model) {

        this.id = model.id;
        this.created_at = model.created_at;
        bindUser(model.owner_id);
        bindFriend(model.friend_id);
        return this;
    }


    private void bindUser(int id) {
        ProfileStorageService service = new ProfileStorageService();
        this.sender = service.getStorage()
                .getById(id);
    }

    private void bindFriend(int id) {
        FriendStorageService service = new FriendStorageService();
        this.recipient = service.getStorage()
                .getById(id);
    }

    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<ChatDAO> {

        @Override
        public ChatDAO create() {
            return new ChatDAO();
        }
    }
}
