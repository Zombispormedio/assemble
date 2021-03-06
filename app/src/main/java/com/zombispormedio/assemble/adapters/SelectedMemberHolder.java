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
 * Created by Xavier Serrano on 23/09/2016.
 */

public class SelectedMemberHolder extends AbstractHolder<SelectedMemberHolder.Container> {



    @BindView(R.id.username_label)
    TextView usernameLabel;


    @BindView(R.id.image_view)
    ImageView imageView;


    private IOnClickItemListHandler<Container> listener;

    public SelectedMemberHolder(@NonNull View view) {
        super(view);
        this.listener = null;
    }


    @Override
    public void bind(int position, @NonNull Container itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
    }


    private void renderData(@NonNull Container data) {
        FriendProfile friend = data.getContent();
        usernameLabel.setText(friend.username);

        friend.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
    }

    private void setupOnClickListener(final int position, final Container itemData) {
        itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }
        });
    }


    public void setOnClickListener(
            IOnClickItemListHandler<Container> listener) {
        this.listener = listener;
    }

    public static class Container extends AbstractHolder.Container<FriendProfile> implements Sorted<Container> {

        public Container(FriendProfile content, int friendIndex)  {
            super(content, friendIndex);
        }

        @Override
        public boolean areTheSame(Container o) {
            return content.areTheSame(o.getContent());
        }

        @Override
        public int getIdentity() {
            return content.getIdentity();
        }

        @Override
        public int compareTo(@NonNull Container tContainer) {
            return content.compareTo(tContainer.getContent());
        }

    }

}
