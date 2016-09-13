package com.zombispormedio.assemble.utils;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.UserProfile;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmObject;

/**
 * Created by Xavier Serrano on 31/07/2016.
 */
public class Utils {

    public static boolean presenceOf(Object obj) {
        return obj != null;
    }

    public static boolean presenceOf(String obj) {
        boolean it_is = true;

        if (obj == null) {
            it_is = false;
        } else {
            if (obj.isEmpty()) {
                it_is = false;
            }
        }
        return it_is;
    }

    public static interface IConversion{
        Object doIt(Object object);

    }


    public static class MergeBuilder<E, R> {

        private Class<? extends E> emissorClass;

        private E emissor;

        private Class<? extends R> recipientClass;

        private IConversion conversion;

        private R recipient;

        public MergeBuilder emite(E emissor) {
            this.emissor = emissor;
            setEmissorClass();
            return this;
        }

        public MergeBuilder receive(R recipient) {
            this.recipient = recipient;
            setRecipientClass();
            return this;
        }

        public MergeBuilder convert(IConversion conversion){
            this.conversion=conversion;
            return this;
        }

        public MergeBuilder() {
            this.recipient = null;
            this.emissor = null;
            this.conversion=null;
        }

        private void setEmissorClass() {
            if (emissor != null) {
                this.emissorClass = (Class<? extends E>) emissor.getClass();
            }
        }

        private void setRecipientClass() {
            if (recipient != null) {
                this.recipientClass = (Class<? extends R>) recipient.getClass();
            }
        }

        public MergeBuilder(E emissor, R recipient) {
            this.emissor = emissor;
            setEmissorClass();
            this.recipient = recipient;
            setRecipientClass();
        }

        public R merge() {

            if (emissor != null && recipient != null) {

                for (Field field : recipientClass.getFields()) {
                    String name = field.getName();
                    Field thisField = null;
                    try {
                        thisField = emissorClass.getField(name);
                        Object value = thisField.get(emissor);

                        if(conversion!=null){
                            Object preValue=conversion.doIt(value);
                            if(preValue!=null){
                                value=preValue;
                            }
                        }

                        field.set(recipient, value);
                    } catch (Exception e) {
                        Logger.d(e.getMessage());
                    }

                }

            }

            return recipient;
        }
    }




}
