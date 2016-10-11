package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;


import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
        ButterKnife.bind(this, getView());
    }


    @Override
    public void bind(int position, Meeting itemData) {
        renderData(itemData);
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

    private void renderData(Meeting meeting) {

        String meetingName = meeting.name;

        nameLabel.setText(meetingName);
        frameView.setBackgroundColor(Utils.getColorByString(meetingName));

        renderDate(meeting.start_at);

        renderMeetingImage(meeting);

        renderTeam(meeting.team);

    }

    private void renderTeam(Team team) {

        teamLabel.setText(team.name);

        team.getMediumImageBuilder()
                .context(getContext())
                .imageView(teamImage)
                .build();
    }

    private void renderMeetingImage(Meeting meeting) {
        if (meeting.haveLargeImage()) {

            meeting.getLargeImageBuilder()
                    .context(getContext())
                    .circle(false)
                    .imageView(meetingImage)
                    .build();

        } else {
            meetingImage.setVisibility(View.INVISIBLE);
        }
    }

    private void renderDate(String startAt) {
        String format = itemView.getContext().getString(R.string.simple_date_with_hours);
        String date = DateUtils.format(format, startAt);
        dateLabel.setText(date);
    }

    public void setOnClickListener(IOnClickItemListHandler<Meeting> listener) {
        this.listener = listener;
    }

}
