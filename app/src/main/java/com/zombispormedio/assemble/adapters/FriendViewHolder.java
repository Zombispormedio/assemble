package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;


import android.view.View;
import android.widget.TextView;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendViewHolder extends AbstractViewHolder<FriendProfile> {

    private View ctx;

    private IOnClickItemListHandler<FriendProfile> listener;

    private TextView usernameLabel;

    private TextView emailLabel;

    public FriendViewHolder(View ctx) {
        super(ctx);
        this.ctx = ctx;
        this.listener = null;
        setup();

    }

    private void setup() {
        usernameLabel = (TextView) ctx.findViewById(R.id.username_label);
        emailLabel = (TextView) ctx.findViewById(R.id.email_label);
    }

    @Override
    public void bind(int position, FriendProfile itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final FriendProfile itemData) {
        ctx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }

            }
        });
    }


    private void bindData(FriendProfile itemData) {
        usernameLabel.setText(itemData.username);
        emailLabel.setText(itemData.email);
    }


    public void setOnClickListener(IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }
}
