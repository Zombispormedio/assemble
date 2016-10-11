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

    public TeamFriendHolder(View view) {
        super(view);
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }



    @Override
    public void bind(int position, SelectedContainer itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final SelectedContainer itemData) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void renderData(SelectedContainer data) {
        FriendProfile item=data.getContent();
        String username=item.username;
        usernameLabel.setText(username);
        setupImage(item.large_avatar_url, StringUtils.firstLetter(username));
        if(data.isSelected()){
            selectedIcon.setVisibility(View.VISIBLE);
        }else{
            selectedIcon.setVisibility(View.GONE);
        }
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
            IOnClickItemListHandler<SelectedContainer> listener) {
        this.listener = listener;
    }


    public static class SelectedContainer{
        private FriendProfile content;
        private boolean selected;
        private int seletedMemberIndex;

        public SelectedContainer(FriendProfile content) {
            this.content = content;
            this.selected=false;
            seletedMemberIndex=-1;
        }

        public FriendProfile getContent() {
            return content;
        }


        public void select(){
            selected=true;
        }

        public void deselect(){
            selected=false;
        }

        public boolean isSelected() {
            return selected;
        }

        public int getSeletedMemberIndex() {
            return seletedMemberIndex;
        }

        public void setSeletedMemberIndex(int seletedMemberIndex) {
            this.seletedMemberIndex = seletedMemberIndex;
        }
    }
}
