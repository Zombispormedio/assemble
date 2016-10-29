package com.zombispormedio.assemble.adapters;

import com.varunest.sparkbutton.SparkButton;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;

import com.zombispormedio.assemble.models.Team;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamHolder extends AbstractHolder<Team> {

    @BindView(R.id.name_label)
    TextView nameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.card_view)
    CardView cardView;

    @BindView(R.id.star_checker)
    SparkButton starChecker;

    private IOnClickItemListHandler<Team> starCheckerListener;

    private IOnClickItemListHandler<Team> listener;

    public TeamHolder(View view) {
        super(view);
        this.listener = null;
        this.starCheckerListener=null;
    }


    @Override
    public void bind(int position, Team itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
        setupOnStarred(position, itemData);
    }

    private void setupOnStarred(int position, Team itemData) {
        starChecker.setEventListener((button, buttonState) -> {
            if(starCheckerListener!=null){
                starCheckerListener.onClick(position, itemData);
            }
        });
    }

    private void setupOnClickListener(final int position, final Team itemData) {
        cardView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position, itemData);
            }
        });
    }

    private void renderData(Team team) {
        nameLabel.setText(team.name);
        team.getLargeImageBuilder()
                .context(getContext())
                .imageView(imageView)
                .build();
        starChecker.setChecked(team.starred);
    }

    public void setOnClickListener(IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }

    public void setStarCheckerListener(
            IOnClickItemListHandler<Team> starCheckerListener) {
        this.starCheckerListener = starCheckerListener;
    }
}
