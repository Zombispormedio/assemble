package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.StringUtils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class TeamDialogHolder extends AbstractHolder<Team> {

    @BindView(R.id.name_label)
    TextView nameLabel;

    @BindView(R.id.image_view)
    ImageView imageView;


    private IOnClickItemListHandler<Team> listener;

    public TeamDialogHolder(View view) {
        super(view);
        this.listener = null;
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, Team itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Team itemData) {
        itemView.setOnClickListener(new View.OnClickListener() {
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

        new ImageUtils.ImageBuilder(itemView.getContext(), imageView)
                .letter(StringUtils.firstLetter(teamName))
                .circle(true)
                .url(itemData.large_image_url)
                .build();


    }

    public void setOnClickListener(IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }

}
