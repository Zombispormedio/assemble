package com.zombispormedio.assemble.adapters;

import com.varunest.sparkbutton.SparkButton;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.Utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingHolder extends AbstractHolder<Meeting> {

    @Nullable
    @BindView(R.id.name_label)
    TextView nameLabel;

    @Nullable
    @BindView(R.id.frame_view)
    FrameLayout frameView;

    @Nullable
    @BindView(R.id.date_label)
    TextView dateLabel;

    @Nullable
    @BindView(R.id.team_image)
    ImageView teamImage;

    @Nullable
    @BindView(R.id.team_label)
    TextView teamLabel;

    @Nullable
    @BindView(R.id.meeting_image)
    ImageView meetingImage;

    @Nullable
    @BindView(R.id.card_view)
    CardView cardView;

    @Nullable
    @BindView(R.id.bookmark_button)
    SparkButton bookmarkButton;

    @Nullable
    private IOnClickItemListHandler<Meeting> listener;

    private IOnClickItemListHandler<Meeting> bookmarkListener;

    public MeetingHolder(View view) {
        super(view);
        this.listener = null;
    }


    @Override
    public void bind(int position, @NonNull Meeting itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
        setupOnBookmark(position, itemData);
    }

    private void setupOnBookmark(int position, Meeting itemData) {
        bookmarkButton.setEventListener((button, buttonState) -> {
            if (bookmarkListener != null) {
                bookmarkListener.onClick(position, itemData);
            }
        });

    }

    private void setupOnClickListener(final int position, final Meeting itemData) {
        cardView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }
        });
    }

    private void renderData(@NonNull Meeting meeting) {

        String meetingName = meeting.name;

        nameLabel.setText(meetingName);
        frameView.setBackgroundColor(Utils.getColorByString(meetingName));

        String dateFormatted = meeting.getStartAt().format(getString(R.string.simple_date_with_hours));
        dateLabel.setText(dateFormatted);

        renderMeetingImage(meeting);

        renderTeam(meeting.team);

        bookmarkButton.setChecked(meeting.bookmark);

    }

    private void renderTeam(@NonNull Team team) {

        teamLabel.setText(team.name);

        team.getMediumImageBuilder()
                .context(getContext())
                .imageView(teamImage)
                .build();


    }

    private void renderMeetingImage(@NonNull Meeting meeting) {
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

    public void setOnClickListener(IOnClickItemListHandler<Meeting> listener) {
        this.listener = listener;
    }

    public void setBookmarkListener(
            IOnClickItemListHandler<Meeting> bookmarkListener) {
        this.bookmarkListener = bookmarkListener;
    }
}
