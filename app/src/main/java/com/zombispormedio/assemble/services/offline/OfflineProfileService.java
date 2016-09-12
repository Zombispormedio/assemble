package com.zombispormedio.assemble.services.offline;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.dao.UserProfileDAO;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.interfaces.IEmbeddedService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class OfflineProfileService implements IEmbeddedService<UserProfile> {

    private LocalStorage<UserProfileDAO> storage;

    public OfflineProfileService() {
        this.storage = new LocalStorage<>(UserProfileDAO.class);
    }

    @Override
    public void create(final UserProfile params) {

        UserProfileDAO object= new UserProfileDAO();

        object.fromModel(params);

        storage.create(object);

    }

    @Override
    public void update(final UserProfile params) {

        UserProfileDAO object=storage.getById(params.id);

        if(object!=null){
            object.fromModel(params);
            storage.update(object);
        }

    }

    @Override
    public void createOrUpdate(UserProfile params) {
        UserProfileDAO object=storage.getById(params.id);

        if(object!=null){
           object.fromModel(params);
           storage.update(object);
        }else{
            create(params);
        }

    }


    @Override
    public UserProfile getFirst() {
        UserProfile result=null;
        UserProfileDAO object=storage.getFirst();
        if(object!=null){
           result=object.toModel();
        }
        return result;
    }


}
