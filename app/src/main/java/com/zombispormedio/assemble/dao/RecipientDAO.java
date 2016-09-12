package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Recipient;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class RecipientDAO extends RealmObject implements IBaseDAO<Recipient> {

    @PrimaryKey
    public int id;

    @Index
    public String email;

    @Index
    public String username;

    public String full_avatar_url;

    public String large_avatar_url;

    public String medium_avatar_url;

    public String thumb_avatar_url;


    @Override
    public Recipient toModel(){
        return (Recipient) new Utils.MergeBuilder<RecipientDAO, Recipient>()
                .emite(this)
                .receive(new Recipient())
                .merge();
    }

    @Override
    public RecipientDAO fromModel(Recipient model) {
        return (RecipientDAO) new Utils.MergeBuilder<Recipient, RecipientDAO>()
                .emite(model)
                .receive(this)
                .merge();
    }
}
