package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Meeting;


import android.view.View;
import android.widget.TextView;

/**
 * Created by Xavier Serrano on 06/09/2016.
 */
public class MeetingViewHolder extends AbstractViewHolder<Meeting> {
    private View ctx;

    private TextView nameLabel;

    private IOnClickItemListHandler<Meeting> listener;

    public MeetingViewHolder(View ctx) {
        super(ctx);
        this.ctx = ctx;
        this.listener = null;
        setup();
    }

    private void setup() {
        nameLabel = (TextView) ctx.findViewById(R.id.name_label);
    }


    @Override
    public void bind(int position, Meeting itemData) {
        bindData(itemData);
        setupOnClickListener(position, itemData);
    }
    private void setupOnClickListener(final int position, final Meeting itemData) {
        ctx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, itemData);
                }
            }
        });
    }

    private void bindData(Meeting itemData) {
        nameLabel.setText(itemData.name);
    }

}
