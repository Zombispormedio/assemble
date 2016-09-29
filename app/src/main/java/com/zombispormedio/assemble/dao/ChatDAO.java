package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.storage.FriendStorageService;
import com.zombispormedio.assemble.services.storage.ProfileStorageService;

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
        return new Chat(id, created_at, sender.toModel(), recipient.toModel());
    }

    @Override
    public ChatDAO fromModel(Chat model) {

        this.id=model.id;
        this.created_at=model.created_at;
        bindUser(model.sender);
        bindFriend(model.recipient);
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

    public static class Factory implements IDAOFactory<ChatDAO>{

        @Override
        public ChatDAO create() {
            return new ChatDAO();
        }
    }
}
