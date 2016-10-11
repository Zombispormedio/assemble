package com.zombispormedio.assemble.adapters;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatHolder extends AbstractHolder<Chat> {

    private static final int CONTENT_LIMIT=30;

    @BindView(R.id.username_label)
    TextView nameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.last_message_label)
    TextView lastMessage;

    @BindView(R.id.date_label)
    TextView dateLabel;

    private IOnClickItemListHandler<Chat> listener;

    public ChatHolder(View view) {
        super(view);
        this.listener = null;
        setup();

    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, Chat itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Chat itemData) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void renderData(Chat itemData) {
        FriendProfile recipient = itemData.recipient;

        String recipientName = recipient.username;
        nameLabel.setText(recipientName);

        renderLastMessage(itemData.messages);

        new ImageUtils.ImageBuilder(itemView.getContext(), imageView)
                .url(recipient.large_avatar_url)
                .letter(StringUtils.firstLetter(recipientName))
                .circle(true)
                .build();
    }

    private void renderLastMessage(Message[] messages) {
        String content = "";
        String formatDate = "";

        if (messages != null) {
            if (messages.length > 0) {
                Message last = messages[messages.length - 1];
                content = last.getLimitedContent(CONTENT_LIMIT);

                formatDate = last.isCreatedToday() ? last.formatCreated(getString(R.string.simple_hour))
                        : last.isCreatedYesterday() ? getString(R.string.yesterday)
                                : last.formatCreated(getString(R.string.slash_date));

            }
        }

        lastMessage.setText(content);

        dateLabel.setText(formatDate);
    }


    private String getString(int id){
        return itemView.getContext().getString(id);
    }

    public void setOnClickListener(IOnClickItemListHandler<Chat> listener) {
        this.listener = listener;
    }
}
