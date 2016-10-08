package com.zombispormedio.assemble.models.loaders;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.services.interfaces.IChatService;
import com.zombispormedio.assemble.services.storage.ChatStorageService;
import com.zombispormedio.assemble.services.storage.IStorageService;
import com.zombispormedio.assemble.services.storage.MessageStorageService;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ResourceTask;

import android.app.IntentService;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 13/09/2016.
 */
public class ChatLoader implements ILoader {

    private IChatService apiService;
    private ChatStorageService storageService;


    public ChatLoader(IChatService apiService, ChatStorageService storageService) {
        this.apiService = apiService;
        this.storageService = storageService;
    }

    @Override
    public void retrieve(final SuccessHandler handler) {
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
