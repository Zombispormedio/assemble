package com.zombispormedio.assemble.adapters;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatHolder extends AbstractHolder<Chat> {

    private static final int CONTENT_LIMIT = 25;

    @Nullable
    @BindView(R.id.username_label)
    TextView nameLabel;

    @Nullable
    @BindView(R.id.image_view)
    ImageView imageView;

    @Nullable
    @BindView(R.id.last_message_label)
    TextView lastMessageLabel;

    @Nullable
    @BindView(R.id.date_label)
    TextView dateLabel;

    @Nullable
    @BindView(R.id.unread_count)
    TextView unreadCountLabel;

    @Nullable
    private IOnClickItemListHandler<Chat> listener;

    public ChatHolder(View view) {
        super(view);
        this.listener = null;
    }

    @Override
    public void bind(int position, @NonNull Chat itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Chat itemData) {
        getView().setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }
        });
    }

    private void renderData(@NonNull Chat chat) {
        FriendProfile recipient = chat.recipient;
        nameLabel.setText(recipient.username);

        recipient.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();

        if (chat.unreadCount > 0) {
            unreadCountLabel.setVisibility(View.VISIBLE);
            unreadCountLabel.setText(String.valueOf(chat.unreadCount));
        } else {
            unreadCountLabel.setVisibility(View.GONE);
        }

        renderLastMessage(chat.lastMessage);

    }

    private void renderLastMessage(@Nullable Message last) {
        String content = "";
        String formatDate = "";

        if (last != null) {

            content = last.getLimitedContent(CONTENT_LIMIT);

            formatDate = last.isCreatedToday() ? last.formatCreated(getString(R.string.simple_hour))
                    : last.isCreatedYesterday() ? getString(R.string.yesterday)
                            : last.formatCreated(getString(R.string.slash_date));

            if (last.sender instanceof UserProfile) {
                int drawableID = R.drawable.message_clock_layer;
                if (last.is_read) {
                    drawableID = R.drawable.message_check_all_layer;
                } else if (last.is_sent) {
                    drawableID = R.drawable.message_check_layer;
                }
                Drawable drawable = getDrawable(drawableID, 20, 20);

                lastMessageLabel.setCompoundDrawables(drawable, null, null, null);
                lastMessageLabel.setCompoundDrawablePadding(5);
            } else {
                lastMessageLabel.setCompoundDrawables(null, null, null, null);
            }

        }

        lastMessageLabel.setText(content);

        dateLabel.setText(formatDate);
    }


    public void setOnClickListener(IOnClickItemListHandler<Chat> listener) {
        this.listener = listener;
    }
}
