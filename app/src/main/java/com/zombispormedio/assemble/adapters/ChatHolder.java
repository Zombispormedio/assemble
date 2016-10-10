package com.zombispormedio.assemble.adapters;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;

import org.w3c.dom.Text;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatHolder extends AbstractHolder<Chat> {


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
        bindData(itemData);
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

    private void bindData(Chat itemData) {
        FriendProfile recipient = itemData.recipient;

        String recipientName = recipient.username;
        nameLabel.setText(recipientName);

        bindLastMessage(itemData.messages);

        new ImageUtils.ImageBuilder(itemView.getContext(), imageView)
                .url(recipient.large_avatar_url)
                .letter(StringUtils.firstLetter(recipientName))
                .circle(true)
                .build();
    }

    private void bindLastMessage(Message[] messages) {
        String content = "";
        String formatDate = "";

        if (messages != null) {
            if (messages.length > 0) {
                Message last = messages[0];

                content = last.content;
                if (content.length() > 50) {
                    content = content.substring(0, 50) + "…";
                }

                String date = last.created_at;
                Context ctx = itemView.getContext();

                if (DateUtils.isToday(date)) {
                    formatDate = DateUtils.format(ctx.getString(R.string.simple_hour), date);
                } else {
                    if (DateUtils.isYesterday(date)) {
                        formatDate = ctx.getString(R.string.yesterday);
                    } else {
                        formatDate = DateUtils.format(ctx.getString(R.string.slash_date), date);
                    }
                }

            }

        }

        lastMessage.setText(content);

        dateLabel.setText(formatDate);
    }

    public void setOnClickListener(IOnClickItemListHandler<Chat> listener) {
        this.listener = listener;
    }
}
