package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Chat;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Xavier Serrano on 07/09/2016.
 */
public class ChatViewHolder extends AbstractViewHolder<Chat> {

    private View ctx;

    private TextView nameLabel;

    private IOnClickItemListHandler<Chat> listener;

    public ChatViewHolder(View ctx) {
        super(ctx);
        this.ctx = ctx;
        this.listener = null;
        setup();
    }

    private void setup() {
        nameLabel = (TextView) ctx.findViewById(R.id.name_label);
    }

    @Override
    public void bind(int position, Chat itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Chat itemData) {
        ctx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(Chat itemData) {
        nameLabel.setText(itemData.recipient.username);
    }
}
