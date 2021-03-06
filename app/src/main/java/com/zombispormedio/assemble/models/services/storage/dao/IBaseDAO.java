package com.zombispormedio.assemble.models.services.storage.dao;

import com.zombispormedio.assemble.models.BaseModel;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public interface IBaseDAO<M extends BaseModel> {


    M toModel();

    IBaseDAO fromModel(M model);

    int getId();

}
