package com.zombispormedio.assemble.dao;

import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.Recipient;
import com.zombispormedio.assemble.models.Sender;
import com.zombispormedio.assemble.utils.Utils;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xavier Serrano on 12/09/2016.
 */
public class ChatDAO extends RealmObject implements IBaseDAO<Chat> {

    @PrimaryKey
    public int id;

    public String created_at;

    public SenderDAO sender;

    public RecipientDAO recipient;


    @Override
    public Chat toModel() {
        return new Chat(id, created_at, sender.toModel(), recipient.toModel());
    }

    @Override
    public ChatDAO fromModel(Chat model) {

        return (ChatDAO) new Utils.MergeBuilder<Chat, ChatDAO>()
                .emite(model)
                .receive(this)
                .convert(new Utils.IConversion(){

                    @Override
                    public Object doIt(Object object) {
                        Object result=null;

                        if(object instanceof Sender){
                            result=new SenderDAO();
                            ((SenderDAO)result).fromModel((Sender) object);
                        }

                        if(object instanceof Recipient){
                            result=new RecipientDAO();
                            ((RecipientDAO)result).fromModel((Recipient) object);
                        }

                        return result;
                    }
                })
                .merge();
    }

    @Override
    public int getId() {
        return id;
    }

    public static class Factory implements IDAOFactory<ChatDAO>{

        @Override
        public ChatDAO create() {
            return new ChatDAO();
        }
    }
}
