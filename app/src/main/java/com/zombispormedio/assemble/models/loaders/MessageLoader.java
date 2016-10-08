package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.models.services.interfaces.IChatService;
import com.zombispormedio.assemble.models.services.storage.IStorageService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 08/10/2016.
 */

public class MessageLoader implements ILoader {

    private IChatService apiService;
    private IStorageService<Message> storageService;

    public MessageLoader(IChatService apiService, IStorageService<Message> storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final SuccessHandler handler) {
        apiService.getMessages(new IServiceHandler<ArrayList<Message>, Error>() {
            @Override
            public void onError(Error error) {
                handler.onError();
            }

            @Override
            public void onSuccess(ArrayList<Message> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }

            @Override
            public void onNotConnected() {
                handler.onError();
            }
        });
    }
}
