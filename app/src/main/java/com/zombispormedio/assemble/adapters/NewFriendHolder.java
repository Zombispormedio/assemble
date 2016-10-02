package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.holders.INewFriendHolder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 17/09/2016.
 */
public class NewFriendHolder extends AbstractHolder<FriendProfile> implements INewFriendHolder {

    private IOnClickItemListHandler<FriendProfile> listener;

    private IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener;

    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.email_label)
    TextView emailLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.add_friend_button)
    ImageButton addFriendButton;

    @BindDrawable(R.drawable.account_check)
    Drawable accountCheckDrawable;

    @BindDrawable(R.drawable.account_plus)
    Drawable accountPlusDrawable;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public NewFriendHolder(View view) {
        super(view);
        this.listener = null;
        this.addFriendListener = null;
        setup();

    }

    private void setup() {
        ButterKnife.bind(this, itemView);
        hideProgress();
    }

    @Override
    public void bind(int position, FriendProfile itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
        setupAddFriendButton(position, itemData);

    }

    private void setupOnClickListener(final int position, final FriendProfile itemData) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }

            }
        });
    }

    private void setupAddFriendButton(final int position, final FriendProfile itemData) {
        final INewFriendHolder holder = this;
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addFriendListener != null) {
                    addFriendListener.onClick(position, itemData, holder);
                }
            }
        });
    }


    private void bindData(FriendProfile itemData) {
        usernameLabel.setText(itemData.username);
        emailLabel.setText(itemData.email);
        setupImage(itemData.large_avatar_url, StringUtils.firstLetter(itemData.username));

        setFriendChecked(itemData.in_request);

    }

    private void setupImage(String url, String letter) {
        ImageUtils.ImageBuilder builder = new ImageUtils.ImageBuilder(itemView.getContext(), imageView)
                .letter(letter)
                .circle(true);
        if (Utils.presenceOf(url)) {
            builder = builder.url(url);
        }

        builder.build();
    }


    public void setOnClickListener(IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }

    public void setAddFriendListener(
            IOnClickComponentItemHandler<FriendProfile, INewFriendHolder> addFriendListener) {
        this.addFriendListener = addFriendListener;
    }

    @Override
    public void setFriendChecked(boolean checked) {

        if (checked) {
            addFriendButton.setEnabled(false);
            addFriendButton.setImageDrawable(accountCheckDrawable);
        } else {
            addFriendButton.setEnabled(true);
            addFriendButton.setImageDrawable(accountPlusDrawable);
        }

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        addFriendButton.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        addFriendButton.setVisibility(View.VISIBLE);
    }
}
