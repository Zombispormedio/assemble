package com.zombispormedio.assemble.models.resources;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IChatService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatResource extends ConceptResource<Chat> {

    private IChatService persistence;


    public ChatResource(IChatService persistence, IStorageService<Chat> storage) {
        super(storage);
        this.persistence = persistence;

    }

    public void getAll(IServiceHandler<ArrayList<Chat>, Error> handler){
        persistence.getAll(handler);
    }

}
