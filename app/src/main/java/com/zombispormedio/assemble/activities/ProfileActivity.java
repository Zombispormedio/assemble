package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.IProfileView;

public class ProfileActivity extends BaseActivity implements IProfileView{

    private ProfileController ctrl;

    private ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar bar = (Toolbar) findViewById(R.id.profile_bar);
        setSupportActionBar(bar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ctrl=new ProfileController(this);

        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        Picasso.with(this).load(R.drawable.profile_image_square).transform(new ImageUtils.CircleTransform()).into(imageProfile);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ctrl.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }

    @Override
    public void setProfileImage(String url) {
        Picasso.with(this)
                .load(url)
                .transform(new ImageUtils.CircleTransform())
                .into(imageProfile);
    }
}
