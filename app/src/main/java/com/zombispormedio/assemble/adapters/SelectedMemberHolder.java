package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 23/09/2016.
 */

public class SelectedMemberHolder extends AbstractHolder<SelectedMemberHolder.Container> {


    @BindView(R.id.image_view)
    ImageView imageView;

    private IOnClickItemListHandler<Container> listener;


    public SelectedMemberHolder(View view) {
        super(view);
        this.listener=null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bind(int position, Container itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }


    private void bindData(Container data) {
        FriendProfile itemData=data.getContent();
        String url=itemData.large_avatar_url;
        String letter= StringUtils.firstLetter(itemData.username);
        ImageUtils.ImageBuilder builder=new ImageUtils.ImageBuilder(itemView.getContext(), imageView)
                .letter(letter)
                .circle(true);
        if(Utils.presenceOf(url)){
            builder=builder.url(url);
        }

        builder.build();
    }

    private void setupOnClickListener(final int position, final Container itemData) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(position, itemData);
                }
            }
        });
    }



    public void setOnClickListener(
            IOnClickItemListHandler<Container> listener) {
        this.listener = listener;
    }

    public static class Container{
        private FriendProfile content;
        private int friendIndex;

        public Container(FriendProfile content, int friendIndex) {
            this.content = content;
            this.friendIndex = friendIndex;
        }

        public FriendProfile getContent() {
            return content;
        }

        public int getFriendIndex() {
            return friendIndex;
        }
    }

}
