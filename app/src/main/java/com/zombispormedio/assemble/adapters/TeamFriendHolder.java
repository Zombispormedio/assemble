package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.Sorted;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 21/09/2016.
 */

public class TeamFriendHolder extends AbstractHolder<TeamFriendHolder.SelectedContainer> {



    private IOnClickItemListHandler<SelectedContainer> listener;


    @BindView(R.id.username_label)
    TextView usernameLabel;


    @BindView(R.id.image_view)
    ImageView imageView;


    @BindView(R.id.selected_icon)
    ImageView selectedIcon;

    public TeamFriendHolder(@NonNull View view) {
        super(view);
        this.listener = null;
    }


    @Override
    public void bind(int position, @NonNull SelectedContainer itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final SelectedContainer itemData) {

        itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }
        });
    }

    private void renderData(@NonNull SelectedContainer data) {
        FriendProfile friend = data.getContent();
        usernameLabel.setText(friend.username);
        friend.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();

        if (data.isClicked()) {
            selectedIcon.setVisibility(View.VISIBLE);
        } else {
            selectedIcon.setVisibility(View.GONE);
        }
    }


    public void setOnClickListener(
            IOnClickItemListHandler<SelectedContainer> listener) {
        this.listener = listener;
    }


    public static class SelectedContainer extends AbstractHolder.Container<FriendProfile> implements Sorted<SelectedContainer> {


        public SelectedContainer(FriendProfile content) {
            super(content);
        }


        @Override
        public boolean areTheSame(@NonNull SelectedContainer o) {
            return content.areTheSame(o.getContent());
        }

        @Override
        public int getIdentity() {
            return content.getIdentity();
        }

        @Override
        public int compareTo(@NonNull SelectedContainer o) {
            return content.compareTo(o.getContent());
        }
    }
}
