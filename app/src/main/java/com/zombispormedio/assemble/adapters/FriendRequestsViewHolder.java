package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsViewHolder extends AbstractViewHolder<FriendRequestProfile> {

    private TextView usernameLabel;

    private TextView emailLabel;

    public FriendRequestsViewHolder(View itemView) {
        super(itemView);
        usernameLabel = (TextView) itemView.findViewById(R.id.username_label);
        emailLabel = (TextView) itemView.findViewById(R.id.email_label);
    }

    @Override
    public void bind(FriendRequestProfile itemData) {
        usernameLabel.setText(itemData.username);
        emailLabel.setText(itemData.email);
    }
}
