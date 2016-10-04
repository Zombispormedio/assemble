package com.zombispormedio.assemble.adapters;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.models.ContentMessage;
import com.zombispormedio.assemble.utils.ImageUtils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 04/10/2016.
 */

public class RightHolder extends AbstractHolder<ContentMessage> {

    @BindView(R.id.content_text)
    TextView content;

    @BindView(R.id.message_state)
    ImageView imageView;

    public RightHolder(View itemView) {
        super(itemView);
        setup();
    }

    private void setup() {
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(int position, ContentMessage itemData) {
        content.setText(itemData.content);

        if(itemData.is_read){
            imageView.setImageResource(R.drawable.message_check_all);
        }else{
            if(itemData.is_sent){
                imageView.setImageResource(R.drawable.message_check);
            }
        }
    }
}
