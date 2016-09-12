package com.zombispormedio.assemble.services.interfaces;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.net.Error;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public interface IMeetingService {

    void getAll(IServiceHandler<ArrayList<Meeting>, Error> handler);
}
