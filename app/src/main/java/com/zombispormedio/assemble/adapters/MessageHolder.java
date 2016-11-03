package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.utils.ISODate;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class MessageHolder extends AbstractHolder<MessageHolder.Container> {

    @BindView(R.id.message_date_label)
    TextView bottomDateLabel;

    @BindView(R.id.date_label)
    TextView topDateLabel;

    @BindView(R.id.date_label_layout)
    FrameLayout topDateLabelLayout;


    public MessageHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(int position, @NonNull Container container) {
        bind(position, container, null);
    }


    public void bind(int position, @NonNull Container container, Container previousContainer) {
        setupOnClick(container);
        Message message = container.getContent();
        renderData(message);

        if(previousContainer!=null){
            Message previous = previousContainer.getContent();
            if (message.beforeCreated(previous.getCreatedAt())) {
                renderTopDateLabel(message.getCreatedAt());
            } else {
                topDateLabelLayout.setVisibility(View.GONE);
            }
        }else{
            renderTopDateLabel(message.getCreatedAt());
        }

    }

    private void renderTopDateLabel(@NonNull ISODate created) {
        topDateLabelLayout.setVisibility(View.VISIBLE);

        String text = created.isToday() ? getString(R.string.today)
                : created.isYesterday() ? getString(R.string.yesterday) :
                        created.format(getString(R.string.simple_date));
        topDateLabel.setText(text);
    }


    protected void renderData(@NonNull Message message) {
        renderBottomDateLabel(message);
    }

    protected void renderBottomDateLabel(@NonNull Message message) {
        boolean isToday = message.isCreatedToday();
        boolean isYesterday = message.isCreatedYesterday();

        String formatDate = isToday ? message.formatCreated(getString(R.string.message_date_today))
                : isYesterday ? message.formatCreated(getString(R.string.message_date_yesterday))
                        : message.formatCreated(getString(R.string.message_date));
        bottomDateLabel.setText(formatDate);

        int marginLeft;
        if ((message.sender instanceof UserProfile) && (message.content.length() < 38)) {
            int shortDate = (int) getDimen(R.dimen.short_date_message_margin);
            int nearDate = (int) getDimen(R.dimen.near_date_message_margin);
            marginLeft = isToday || isYesterday ? nearDate : shortDate;
        } else {
            marginLeft = (int) getDimen(R.dimen.date_message_margin_left);
        }
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) bottomDateLabel.getLayoutParams();
        params.setMargins(marginLeft, params.topMargin, params.rightMargin, params.bottomMargin);
        bottomDateLabel.setLayoutParams(params);
    }



    private void setupOnClick(@NonNull Container container) {
        if (container.isClicked()) {
            showDate();
        } else {
            hideDate();
        }

        getView().setOnClickListener(v -> {
            if (container.isClicked()) {
                container.setClicked(false);
                hideDate();
            } else {
                container.setClicked(true);
                showDate();
            }
        });
    }

    private void showDate() {
        bottomDateLabel.setVisibility(View.VISIBLE);
    }

    private void hideDate() {
        bottomDateLabel.setVisibility(View.GONE);
    }


    public static class Container extends AbstractHolder.Container<Message> {

        public Container(Message content) {
            super(content);
        }

        public void read() {
            content.is_read = true;
        }

    }

}
