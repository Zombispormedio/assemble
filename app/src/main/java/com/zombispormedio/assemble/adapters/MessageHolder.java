package com.zombispormedio.assemble.adapters;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.modules.LoaderModule;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
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

    @BindView(R.id.date_label)
    TextView dateLabel;

    private boolean isRoot;

    public MessageHolder(View itemView) {
        super(itemView);
        isRoot = false;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, Message message) {
        isRoot = true;
        renderData(message);
        renderDate(message.created_at);
    }


    public void bind(int position, Message message, Message previous) {
        registerIfIsRoot(message, previous);
        renderData(message);
       if( message.diffDate(previous)){
           renderDate(message.created_at);
       }
    }

    private void renderDate(String created_at) {


    }


    public void renderData(Message message) {

        if (message.sender instanceof UserProfile) {
            renderUserMessage(message);

        } else {
            renderFriendMessage(message);

        }
        bindDate(message);
        setupOnClick(message);

    }

    private void bindDate(Message message) {
        boolean isToday=message.isCreatedToday();
        boolean isYesterday=message.isCreatedYesterday();

        String formatDate = isToday ? message.formatCreated(getString(R.string.message_date_today))
                : isYesterday ? message.formatCreated(getString(R.string.message_date_yesteday))
                        : message.formatCreated(getString(R.string.message_date));
        messageDateLabel.setText(formatDate);

        int marginLeft;
        if((message.sender instanceof UserProfile) && (message.content.length() < 38)){
            int shortDate=(int)getDimen(R.dimen.short_date_message_margin);
            int nearDate= (int) getDimen(R.dimen.near_date_message_margin);
            marginLeft= isToday||isYesterday?nearDate:shortDate;
        }else{
            marginLeft= (int) getDimen(R.dimen.date_message_margin_left);
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) messageDateLabel.getLayoutParams();
        params.setMargins(marginLeft, params.topMargin, params.rightMargin, params.bottomMargin);
        messageDateLabel.setLayoutParams(params);
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

    private void setupOnClick(Message message) {
        if(message.clicked){
            showDate();
        }else{
            hideDate();
        }

        getView().setOnClickListener(v -> {
            if (message.clicked) {
                message.clicked=false;
                hideDate();
            } else {
                message.clicked=true;
                showDate();
            }
        });
    }

    private void showDate(){
        messageDateLabel.setVisibility(View.VISIBLE);
    }

    private void hideDate(){
        messageDateLabel.setVisibility(View.GONE);
    }

}
