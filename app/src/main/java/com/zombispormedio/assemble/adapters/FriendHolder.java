package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.holders.IFriendHolder;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendHolder extends AbstractHolder<FriendProfile> implements IFriendHolder {

    private IOnClickItemListHandler<FriendProfile> listener;

    private IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener;

    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.email_label)
    TextView emailLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.remove_friend_button)
    ImageButton removeButton;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public FriendHolder(View view) {
        super(view);
        this.listener = null;
        setup();

    }

    private void setup() {
        ButterKnife.bind(this, getView());
    }

    @Override
    public void bind(int position, FriendProfile itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
        setupOnClickRemoveButton(position, itemData);
    }



    private void setupOnClickListener(final int position, final FriendProfile itemData) {
        itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }

        });
    }

    private void setupOnClickRemoveButton(final int position, final FriendProfile itemData) {
        final IFriendHolder holder=this;
        removeButton.setOnClickListener(v -> {
            if(removeButtonListener!=null){
                removeButtonListener.onClick(position, itemData, holder);
            }
        });
    }


    private void renderData(FriendProfile friend) {
        usernameLabel.setText(friend.username);
        emailLabel.setText(friend.email);

        friend.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
    }


    public void setOnClickListener(IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }

    public void setRemoveButtonListener(
            IOnClickComponentItemHandler<FriendProfile, IFriendHolder> removeButtonListener) {
        this.removeButtonListener = removeButtonListener;
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        removeButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        removeButton.setVisibility(View.GONE);
    }
}
