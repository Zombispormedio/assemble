package com.zombispormedio.assemble.adapters;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;


import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingHolder extends AbstractHolder<Meeting> {

    @BindView(R.id.name_label)
    TextView nameLabel;

    @BindView(R.id.frame_view)
    FrameLayout frameView;

    @BindView(R.id.date_label)
    TextView dateLabel;

    @BindView(R.id.team_image)
    ImageView teamImage;

    @BindView(R.id.team_label)
    TextView teamLabel;

    @BindView(R.id.meeting_image)
    ImageView meetingImage;

    @BindView(R.id.card_view)
    CardView cardView;

    private IOnClickItemListHandler<Meeting> listener;

    public MeetingHolder(View view) {
        super(view);
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bind(int position, Meeting itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Meeting itemData) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(Meeting itemData) {

        String meetingName = itemData.name;

        nameLabel.setText(meetingName);
        frameView.setBackgroundColor(Utils.getColorByString(meetingName));

        String format = itemView.getContext().getString(R.string.simple_date_with_hours);
        String date = DateUtils.format(format, itemData.start_at);
        dateLabel.setText(date);

        if (Utils.presenceOf(itemData.large_image_url)) {
            new ImageUtils.ImageBuilder(itemView.getContext(), meetingImage)
                    .url(itemData.large_image_url)
                    .build();
        } else {
            meetingImage.setVisibility(View.INVISIBLE);
        }

        String teamName = itemData.team.name;

        teamLabel.setText(teamName);

        ImageUtils.ImageBuilder teamImageBuilder = new ImageUtils.ImageBuilder(itemView.getContext(), teamImage)
                .letter(StringUtils.firstLetter(teamName))
                .circle(true);

        String teamImageUrl = itemData.team.medium_image_url;

        if (Utils.presenceOf(teamImageUrl)) {
            teamImageBuilder.url(teamImageUrl);
        }

        teamImageBuilder.build();


    }

    public void setOnClickListener(IOnClickItemListHandler<Meeting> listener) {
        this.listener = listener;
    }

}
