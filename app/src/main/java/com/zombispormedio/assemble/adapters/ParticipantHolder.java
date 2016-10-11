package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
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
 * Created by Xavier Serrano on 25/09/2016.
 */

public class ParticipantHolder extends AbstractHolder<FriendProfile> {


    @BindView(R.id.username_label)
    TextView usernameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    public ParticipantHolder(View itemView) {
        super(itemView);
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, FriendProfile itemData) {
        renderData(itemData);
    }


    private void renderData(FriendProfile itemData) {
        String username=itemData.username;

        if(username.length()>10){
            username=username.substring(0,10)+"…";
        }

        usernameLabel.setText(username);
        setupImage(itemData.large_avatar_url, StringUtils.firstLetter(username));
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

}
