package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.ISelectedFriend;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 21/09/2016.
 */

public class FriendViewHolder extends AbstractViewHolder<FriendProfile> implements ISelectedFriend {

    private View view;

    private IOnClickComponentItemHandler<FriendProfile, ISelectedFriend> listener;

    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    public FriendViewHolder(View View) {
        super(View);
        this.view = view;
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, view);
    }



    @Override
    public void bind(int position, FriendProfile itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final FriendProfile itemData) {
        final ISelectedFriend holder=this;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(position, itemData, holder);
                }
            }
        });
    }

    private void bindData(FriendProfile itemData) {
        usernameLabel.setText(itemData.username);
        setupImage(itemData.large_avatar_url, StringUtils.firstLetter(itemData.username));
    }

    private void setupImage(String url, String letter){
        ImageUtils.ImageBuilder builder=new ImageUtils.ImageBuilder(view.getContext(), imageView)
                .letter(letter)
                .circle(true);
        if(Utils.presenceOf(url)){
            builder=builder.url(url);
        }

        builder.build();
    }


    public void setOnClickListener(
            IOnClickComponentItemHandler<FriendProfile, ISelectedFriend> listener) {
        this.listener = listener;
    }
}
