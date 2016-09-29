package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.UserProfile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 29/09/2016.
 */

public class PreviewFriendHolder extends AbstractHolder<FriendProfile> {

    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    private IOnClickItemListHandler<UserProfile> listener;

    public PreviewFriendHolder(View itemView) {
        super(itemView);
        this.listener = null;
        setup();
    }
    private void setup() {
        ButterKnife.bind(this, itemView);
    }
}
