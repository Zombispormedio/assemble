package com.zombispormedio.assemble.services.storage;

import com.zombispormedio.assemble.dao.UserProfileDAO;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.interfaces.IStorageService;
import com.zombispormedio.assemble.wrappers.realm.LocalStorage;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ProfileStorageService implements IStorageService<UserProfile> {

    private LocalStorage<UserProfileDAO> storage;

    public ProfileStorageService() {
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

    @Override
    public ArrayList<UserProfile> getAll() {
        ArrayList<UserProfile> result= new ArrayList<>();

        ArrayList<UserProfileDAO> objects= storage.getAll();

        for (UserProfileDAO object: objects) {
            result.add(object.toModel());
        }

        return result;
    }

    @Override
    public UserProfile getByID(int id) {
        UserProfile result=null;
        UserProfileDAO object=storage.getById(id);

        if(result!=null){
            result=object.toModel();
        }

        return result;
    }


}
