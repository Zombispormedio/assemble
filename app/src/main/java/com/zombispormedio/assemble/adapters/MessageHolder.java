package com.zombispormedio.assemble.adapters;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.modules.LoaderModule;


import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageHolder extends AbstractHolder<Message> {

    @BindView(R.id.friend_layout)
    LinearLayout friendLayout;

    @BindView(R.id.user_layout)
    FrameLayout userLayout;

    @BindView(R.id.friend_image_view)
    ImageView friendImageView;

    @BindView(R.id.friend_content_text)
    TextView friendContent;

    @BindView(R.id.user_content_text)
    TextView userContent;

    @BindView(R.id.message_state)
    ImageView userImageView;

    @BindView(R.id.message_date_label)
    TextView messageDateLabel;


    private boolean isRoot;

    private boolean clicked;

    public MessageHolder(View itemView) {
        super(itemView);
        isRoot = false;
        clicked=false;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
        setupOnClick();
    }

    @Override
    public void bind(int position, Message message) {
        isRoot = true;
        renderData(message);
    }

    public void bind(int position, Message message, Message previous) {
        registerIfIsRoot(message, previous);
        renderData(message);
    }


    public void renderData(Message message) {

        if (message.sender instanceof UserProfile) {
            renderUserMessage(message);

        } else {
            renderFriendMessage(message);
        }

        bindDate(message);
    }

    private void bindDate(Message message) {
        String formatDate = message.isCreatedToday() ? message.formatCreated(getString(R.string.message_date_today))
                : message.isCreatedYesterday() ? message.formatCreated(getString(R.string.message_date_yesteday))
                        : message.formatCreated(getString(R.string.message_date));
        messageDateLabel.setText(formatDate);
    }

    private void renderFriendMessage(Message message) {
        showFriend();

        friendContent.setText(message.content);
        if (isRoot) {
            doIfRootFriendMessage(message);
            setTopToFriendLayout((int) getDimen(R.dimen.root_message_margin_top));

        } else {
            friendImageView.setVisibility(View.INVISIBLE);
        }



    }

    private void setTopToFriendLayout(int top) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) friendLayout.getLayoutParams();
        params.setMargins(params.leftMargin, top, params.rightMargin, params.bottomMargin);
        friendLayout.setLayoutParams(params);
    }


    private void doIfRootFriendMessage(Message message) {
        friendImageView.setVisibility(View.VISIBLE);
        message.sender.getMediumImageBuilder()
                .context(getContext())
                .imageView(friendImageView)
                .build();
    }

    private void renderUserMessage(Message message) {
        showUser();
        userContent.setText(message.content);
        renderUserMessageState(message);
        if (isRoot) {
            setTopToUserLayout((int) getDimen(R.dimen.root_message_margin_top));
        }
    }

    private void setTopToUserLayout(int top) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) userLayout.getLayoutParams();
        params.setMargins(params.leftMargin, top, params.rightMargin, params.bottomMargin);
        userLayout.setLayoutParams(params);
    }

    private void renderUserMessageState(Message message) {
        if (message.is_read) {
            userImageView.setImageResource(R.drawable.message_check_all_layer);
        } else if (message.is_sent) {
            userImageView.setImageResource(R.drawable.message_check_layer);
        }
    }

    private void showFriend() {
        friendLayout.setVisibility(View.VISIBLE);
        userLayout.setVisibility(View.GONE);
    }


    private void showUser() {
        userLayout.setVisibility(View.VISIBLE);
        friendLayout.setVisibility(View.GONE);
    }

    private void registerIfIsRoot(Message that, Message previous) {
        isRoot = that.sender.id != previous.sender.id;
    }

    private void setupOnClick(){
        getView().setOnClickListener(v -> {
            if(clicked){
                messageDateLabel.setVisibility(View.GONE);
                clicked=false;
            }else{
                messageDateLabel.setVisibility(View.VISIBLE);
                clicked=true;
            }
        });
    }

}
