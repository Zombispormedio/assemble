package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.ContentMessage;
import com.zombispormedio.assemble.models.Message;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class LeftHolder extends AbstractHolder<ContentMessage> {

    @BindView(R.id.content_text)
    TextView content;

    public LeftHolder(View itemView) {
        super(itemView);
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, ContentMessage itemData) {
        content.setText(itemData.content);
    }
}
