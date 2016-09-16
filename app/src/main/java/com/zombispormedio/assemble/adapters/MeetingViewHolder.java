package com.zombispormedio.assemble.adapters;

import com.amulyakhare.textdrawable.util.ColorGenerator;
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
public class MeetingViewHolder extends AbstractViewHolder<Meeting> {

    private View view;

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

    private IOnClickItemListHandler<Meeting> listener;

    public MeetingViewHolder(View view) {
        super(view);
        this.view = view;
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, view);
    }


    @Override
    public void bind(int position, Meeting itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Meeting itemData) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(Meeting itemData) {

        String meetingName=itemData.name;

        nameLabel.setText(meetingName);
        frameView.setBackgroundColor(Utils.getColorByString(meetingName));

        try {
            String date= DateUtils.format(DateUtils.SIMPLE_SLASH_FORMAT_WITH_HOUR, itemData.start_at);
            dateLabel.setText(date);
        } catch (ParseException e) {
            Logger.d(e.getMessage());
        }


        if(Utils.presenceOf(itemData.large_image_url)){
            new ImageUtils.ImageBuilder(view.getContext(), meetingImage)
                    .url(itemData.large_image_url)
                    .build();
        }else{
            meetingImage.setVisibility(View.INVISIBLE);
        }

        String teamName=itemData.team.name;

        teamLabel.setText(teamName);

        ImageUtils.ImageBuilder teamImageBuilder= new ImageUtils.ImageBuilder(view.getContext(), teamImage)
                .letter(StringUtils.firstLetter(teamName))
                .circle(true);

        String teamImageUrl=itemData.team.medium_image_url;

        if(Utils.presenceOf(teamImageUrl)){
            teamImageBuilder.url(teamImageUrl);
        }

        teamImageBuilder.build();



    }

    public void setOnClickListener(IOnClickItemListHandler<Meeting> listener) {
        this.listener = listener;
    }

}
