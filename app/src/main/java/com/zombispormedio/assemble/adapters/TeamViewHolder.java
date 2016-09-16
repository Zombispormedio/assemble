package com.zombispormedio.assemble.adapters;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public class TeamViewHolder extends AbstractViewHolder<Team> {

    private View view;

    @BindView(R.id.name_label)
    TextView nameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.card_view)
    CardView cardView;

    private IOnClickItemListHandler<Team> listener;

    public TeamViewHolder(View view) {
        super(view);
        this.view = view;
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(int position, Team itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Team itemData) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(Team itemData) {
        String teamName=itemData.name;
        nameLabel.setText(teamName);

        new ImageUtils.ImageBuilder(view.getContext(), imageView)
                .letter(StringUtils.firstLetter(teamName))
                .url(itemData.large_image_url)
                .build();


    }

    public void setOnClickListener(IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }
}
