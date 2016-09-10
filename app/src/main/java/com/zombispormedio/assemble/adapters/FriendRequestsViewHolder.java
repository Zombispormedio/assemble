package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;

import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.Utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsViewHolder extends AbstractViewHolder<FriendRequestProfile> {

    private View view;

    private IOnClickItemListHandler<FriendRequestProfile> listener;

    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.email_label)
    TextView emailLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    public FriendRequestsViewHolder(View view) {
        super(view);
        this.view = view;
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(int position, FriendRequestProfile itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final FriendRequestProfile itemData) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }

            }
        });
    }

    private void bindData(FriendRequestProfile itemData) {
        usernameLabel.setText(itemData.username);
        emailLabel.setText(itemData.email);
        if(Utils.presenceOf(itemData.medium_avatar_url)){
            ImageUtils.applyRoundImage(view.getContext(), itemData.large_avatar_url, imageView);
        }else{

            String letter=String.valueOf(itemData.username.charAt(0));
            ImageUtils.applyRoundLetterImage(letter, imageView);
        }
    }

    public void setOnClickListener(IOnClickItemListHandler<FriendRequestProfile> listener) {
        this.listener = listener;
    }
}
