package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.FriendProfile;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 25/09/2016.
 */

public class ParticipantHolder extends AbstractHolder<FriendProfile> {


    private static final int USERNAME_LIMIT = 10;


    @BindView(R.id.username_label)
    TextView usernameLabel;


    @BindView(R.id.image_view)
    ImageView imageView;

    public ParticipantHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(int position, @NonNull FriendProfile itemData) {
        renderData(itemData);
    }


    private void renderData(@NonNull FriendProfile friend) {
        usernameLabel.setText(friend.getLimitedUsername(USERNAME_LIMIT));
        friend.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
    }


}
