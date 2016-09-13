package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IChatService;
import com.zombispormedio.assemble.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ChatLoader implements ILoader {

    private IChatService apiService;
    private IStorageService<Chat> storageService;


    public ChatLoader(IChatService apiService,
            IStorageService<Chat> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final ISuccessHandler handler) {
        apiService.getAll(new IServiceHandler<ArrayList<Chat>, Error>() {
            @Override
            public void onError(Error error) {

            }

            @Override
            public void onSuccess(ArrayList<Chat> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }
        });
    }
}
