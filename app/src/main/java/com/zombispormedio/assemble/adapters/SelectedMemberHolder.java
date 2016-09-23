package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickComponentItemHandler;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.ISelectedMember;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 23/09/2016.
 */

public class SelectedMemberHolder extends AbstractHolder<SelectedMemberHolder.Container> implements ISelectedMember {


    @BindView(R.id.image_view)
    ImageView imageView;

    private IOnClickComponentItemHandler<Container, ISelectedMember> listener;


    public SelectedMemberHolder(View view) {
        super(view);
        this.listener=null;
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
        final ISelectedMember holder=this;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(position, itemData, holder);
                }
            }
        });
    }



    public void setOnClickListener(
            IOnClickComponentItemHandler<Container, ISelectedMember> listener) {
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
