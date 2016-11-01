package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 29/09/2016.
 */

public class PreviewFriendHolder extends AbstractHolder<FriendProfile> {


    @BindView(R.id.username_label)
    TextView usernameLabel;


    @BindView(R.id.image_view)
    ImageView imageView;


    private IOnClickItemListHandler<FriendProfile> listener;

    public PreviewFriendHolder(@NonNull View itemView) {
        super(itemView);
        this.listener = null;
    }

    @Override
    public void bind(int position, @NonNull FriendProfile itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final FriendProfile itemData) {

        itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }
        });
    }

    private void renderData(@NonNull FriendProfile friend) {
        usernameLabel.setText(friend.username);
        friend.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
    }

    public void setOnClickListener(
            IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }
}
