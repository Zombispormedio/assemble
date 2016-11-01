package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.views.holders.IFriendRequestHolder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public class FriendRequestsHolder extends AbstractHolder<FriendRequestProfile> implements IFriendRequestHolder {


    @Nullable
    private IOnClickItemListHandler<FriendRequestProfile> listener;

    @Nullable
    private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> acceptListener;

    @Nullable
    private IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> rejectListener;

    @Nullable
    @BindView(R.id.username_label)
    TextView usernameLabel;

    @Nullable
    @BindView(R.id.image_view)
    ImageView imageView;

    @Nullable
    @BindView(R.id.accept_fab)
    ImageButton acceptButton;

    @Nullable
    @BindView(R.id.reject_fab)
    ImageButton rejectButton;

    @Nullable
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public FriendRequestsHolder(@NonNull View view) {
        super(view);
        this.listener = null;
        this.rejectListener = null;
        this.acceptListener = null;
    }


    @Override
    public void bind(int position, @NonNull FriendRequestProfile itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
        setupAcceptButton(position, itemData);
        setupRejectButton(position, itemData);
    }


    private void setupOnClickListener(final int position, final FriendRequestProfile itemData) {
        itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }

        });
    }

    private void setupAcceptButton(final int position, final FriendRequestProfile itemData) {
        final IFriendRequestHolder holder = this;
        acceptButton.setOnClickListener(v -> {
            if (acceptListener != null) {
                acceptListener.onClick(position, itemData, holder);
            }
        });
    }

    private void setupRejectButton(final int position, final FriendRequestProfile itemData) {
        final IFriendRequestHolder holder = this;
        rejectButton.setOnClickListener(v -> {
            if (rejectListener != null) {
                rejectListener.onClick(position, itemData, holder);
            }
        });
    }


    private void renderData(@NonNull FriendRequestProfile friendRequest) {
        usernameLabel.setText(friendRequest.username);

        friendRequest.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
    }

    public void setOnClickListener(IOnClickItemListHandler<FriendRequestProfile> listener) {
        this.listener = listener;
    }

    public void setAcceptListener(
            IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> acceptListener) {
        this.acceptListener = acceptListener;
    }

    public void setRejectListener(
            IOnClickComponentItemHandler<FriendRequestProfile, IFriendRequestHolder> rejectListener) {
        this.rejectListener = rejectListener;
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        acceptButton.setVisibility(View.VISIBLE);
        rejectButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        acceptButton.setVisibility(View.GONE);
        rejectButton.setVisibility(View.GONE);
    }
}
