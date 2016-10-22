package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

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
    }

    @Override
    public void bind(int position, Team itemData) {
        renderData(itemData);
        setupOnClickListener(position, itemData);
    }

    private void setupOnClickListener(final int position, final Team itemData) {
        itemView.setOnClickListener(view -> {
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
    }

    public void setOnClickListener(IOnClickItemListHandler<Team> listener) {
        this.listener = listener;
    }

}
