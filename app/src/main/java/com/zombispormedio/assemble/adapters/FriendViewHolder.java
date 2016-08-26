package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.FriendProfile;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendViewHolder extends AbstractViewHolder<FriendProfile> {

    private TextView usernameLabel;

    private TextView emailLabel;

    public FriendViewHolder(View itemView) {
        super(itemView);
        usernameLabel = (TextView) itemView.findViewById(R.id.username_label);
        emailLabel = (TextView) itemView.findViewById(R.id.email_label);
    }

    @Override
    public void bind(FriendProfile itemData) {
        usernameLabel.setText(itemData.username);
        emailLabel.setText(itemData.email);
    }
}
