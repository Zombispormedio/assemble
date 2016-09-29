package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 29/09/2016.
 */

public class PreviewFriendHolder extends AbstractHolder<FriendProfile> {

    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    private IOnClickItemListHandler<FriendProfile> listener;

    public PreviewFriendHolder(View itemView) {
        super(itemView);
        this.listener = null;
        setup();
    }
    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, FriendProfile itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final FriendProfile itemData) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(FriendProfile itemData) {
        usernameLabel.setText(itemData.username);
        setupImage(itemData.large_avatar_url, StringUtils.firstLetter(itemData.username));

    }

    private void setupImage(String url, String letter){
        ImageUtils.ImageBuilder builder=new ImageUtils.ImageBuilder(itemView.getContext(), imageView)
                .letter(letter)
                .circle(true);
        if(Utils.presenceOf(url)){
            builder=builder.url(url);
        }

        builder.build();
    }


    public void setOnClickListener(
            IOnClickItemListHandler<FriendProfile> listener) {
        this.listener = listener;
    }
}
