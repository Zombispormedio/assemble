package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 25/09/2016.
 */

public class ParticipantHolder extends AbstractHolder<FriendProfile> {


    private static final int USERNAME_LIMIT = 10;

    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    public ParticipantHolder(View itemView) {
        super(itemView);
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, getView());
    }

    @Override
    public void bind(int position, FriendProfile itemData) {
        renderData(itemData);
    }


    private void renderData(FriendProfile friend) {
        usernameLabel.setText(friend.getLimitedUsername(USERNAME_LIMIT));
        friend.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
    }


}
