package com.zombispormedio.assemble.models.loaders;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.services.interfaces.IChatService;
import com.zombispormedio.assemble.models.services.storage.ChatStorageService;
import com.zombispormedio.assemble.network.Error;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ChatLoader implements ILoader {

    private final IChatService apiService;

    private final ChatStorageService storageService;


    public ChatLoader(IChatService apiService, ChatStorageService storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(@NonNull final SuccessHandler handler) {
        apiService.getAll(new ServiceHandler<ArrayList<Chat>, Error>() {
            @Override
            public void onSuccess(final ArrayList<Chat> result) {
                storageService.createOrUpdateOrDeleteAll(result);
                handler.onSuccess();
            }

            @Override
            public void onError(Error error) {
                handler.onError();
            }

            @Override
            public void onNotConnected() {
                handler.onError();
            }
        });
    }


}
