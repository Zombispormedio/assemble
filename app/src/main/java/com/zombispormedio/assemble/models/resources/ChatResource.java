package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.editors.EditChat;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IChatService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatResource extends ConceptResource<Chat> {

    private IChatService persistence;

    @Inject
    public ChatResource(IChatService persistence, IStorageService<Chat> storage) {
        super(storage);
        this.persistence = persistence;

    }

    public void getAll(IServiceHandler<ArrayList<Chat>, Error> handler){
        persistence.getAll(handler);
    }


    public void create(EditChat chat, final IServiceHandler<Chat, Error> handler){

        persistence.create(chat, new ServiceHandler<Chat, Error>(){
            @Override
            public void onError(Error error) {
                handler.onError(error);
            }

            @Override
            public void onSuccess(Chat result) {
                storage.createOrUpdate(result);
                handler.onSuccess(result);
            }
        });

    }

    public Chat getById(int id){
        return storage.getByID(id);
    }




}
