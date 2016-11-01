package com.zombispormedio.assemble.adapters;

import com.varunest.sparkbutton.SparkButton;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.views.holders.INewFriendHolder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

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
    SparkButton addFriendButton;

    public NewFriendHolder(@NonNull View view) {
        super(view);
        this.listener = null;
        this.addFriendListener = null;
    }


    @Override
    public void bind(int position, @NonNull FriendProfile itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
        setupAddFriendButton(position, itemData);

    }

    private void setupOnClickListener(final int position, final FriendProfile itemData) {
        itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }

        });
    }

    private void setupAddFriendButton(final int position, final FriendProfile itemData) {
        final INewFriendHolder holder = this;
        addFriendButton.setEventListener((button, buttonState) -> {
            if (buttonState) {
                if (addFriendListener != null) {
                    addFriendListener.onClick(position, itemData, holder);
                }
            }
        });
    }


    private void renderData(@NonNull FriendProfile friend) {
        usernameLabel.setText(friend.username);
        emailLabel.setText(friend.email);
        friend.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
        setFriendChecked(friend.in_request);
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
            addFriendButton.setChecked(true);
        } else {
            addFriendButton.setEnabled(true);
            addFriendButton.setChecked(false);
        }

    }

}
