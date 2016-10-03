package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.storage.FriendStorageService;
import com.zombispormedio.assemble.services.storage.MessageStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;

import java.util.ArrayList;

import io.realm.RealmList;
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

    public RealmList<MessageDAO> messages;


    @Override
    public Chat toModel() {
        int messageLen=messages.size();
        Message[] m = new Message[messages.size()];

       for(int i=0; i<messageLen; i++){
           m[i]=messages.get(i).toModel();
       }

        return new Chat(id, created_at, sender.toModel(), recipient.toModel(), m);
    }

    @Override
    public ChatDAO fromModel(Chat model) {

        this.id = model.id;
        this.created_at = model.created_at;
        bindUser(model.sender);
        bindFriend(model.recipient);

        return this;
    }

    public void addMessages(ArrayList<MessageDAO> msgs) {
        messages.clear();
        for (MessageDAO msg : msgs) {
            messages.add(msg);
        }
    }

    private void bindUser(UserProfile profile) {
        ProfileStorageService service = new ProfileStorageService();
        this.sender = service.getStorage()
                .getById(profile.id);
    }

    private void bindFriend(FriendProfile profile) {
        FriendStorageService service = new FriendStorageService();
        this.recipient = service.getStorage()
                .getById(profile.id);
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
