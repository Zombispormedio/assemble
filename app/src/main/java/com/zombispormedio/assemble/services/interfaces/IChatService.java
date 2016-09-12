package com.zombispormedio.assemble.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public interface IChatService {

    void getAll(IServiceHandler<ArrayList<Chat>, Error> handler);
}
