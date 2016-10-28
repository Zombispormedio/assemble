package com.zombispormedio.assemble.services.interceptors;

import com.zombispormedio.assemble.utils.PreferencesManager;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public interface InterceptorInterface {

    boolean isApplicationActive();

    PreferencesManager getPreferencesManager();

    boolean isInHome();

    boolean isInTheSameChat(int chatId);

    void notifyHomeForChat(int chatId);

    void notifyChat(int messageId);

    void notifyHomeForChat(int chatId, boolean read);

}
