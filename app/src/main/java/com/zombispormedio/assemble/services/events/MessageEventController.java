package com.zombispormedio.assemble.services.events;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zombispormedio.assemble.activities.BaseActivity;
import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.models.Message;

import org.json.JSONObject;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.MANY_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.SEVERAL_MESSAGE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Actions.SEVERAL_MESSAGE_ACTIVE_ACTION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.FOREGROUND_NOTIFICATION;
import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.MESSAGES;

/**
 * Created by Xavier Serrano on 27/10/2016.
 */

public class MessageEventController implements INotificationEventController {

    private ArrayList<Message> messages;

    private boolean isDistinct;

    @Override
    public void init(@NonNull ArrayList<JSONObject> data) {

        messages = Stream.of(data)
                .map(Message::createMessage)
                .collect(Collectors.toCollection(ArrayList<Message>::new));
        isDistinct = Message.isDistinctSender(messages);
    }

    @NonNull
    @Override
    public Class<? extends BaseActivity> getIntentClass() {
        return isDistinct ? HomeActivity.class : ChatActivity.class;
    }

    @NonNull
    @Override
    public Intent modifyIntent(@NonNull Intent intent, boolean isApplicationActive) {

        if (!isApplicationActive) {
            String action = isDistinct ? SEVERAL_MESSAGE_ACTION : MANY_MESSAGE_ACTION;
            intent.setAction(action);
            intent.putExtra(MESSAGES, messages);

        } else if (isDistinct) {
            intent.setAction(SEVERAL_MESSAGE_ACTIVE_ACTION);
        }

        if (!isDistinct) {
            intent.putExtra(CHAT_ID, messages.get(0).chat_id);
            intent.putExtra(FOREGROUND_NOTIFICATION, true);
        }

        return intent;
    }

    @Override
    public boolean permitIntent() {
        return true;
    }


}
