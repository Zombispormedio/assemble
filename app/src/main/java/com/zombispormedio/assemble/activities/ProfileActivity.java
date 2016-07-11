package com.zombispormedio.assemble.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.IProfileView;

public class ProfileActivity extends AppCompatActivity implements IProfileView{

    private ProfileController ctrl;

    private ImageView imageProfile;

    private Toolbar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bar=(Toolbar) findViewById(R.id.profile_bar);
        setSupportActionBar(bar);

        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ctrl=new ProfileController(this);

        imageProfile = (ImageView) findViewById(R.id.imageProfile);

       // Picasso.with(this).load(R.drawable.profile_image_square).transform(new ImageUtils.CircleTransform()).into(imageProfile);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Picasso.with(this).load(R.drawable.profile_image_square).transform(new ImageUtils.CircleTransform()).into(imageProfile);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
