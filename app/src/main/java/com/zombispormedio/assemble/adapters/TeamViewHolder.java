package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;

import com.zombispormedio.assemble.models.Team;

import android.view.View;
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(Team itemData) {
        nameLabel.setText(itemData.name);
    }
}
