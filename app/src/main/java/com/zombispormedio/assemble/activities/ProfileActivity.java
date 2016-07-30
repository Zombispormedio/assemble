package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;
import com.zombispormedio.assemble.fragments.ProfileBottomSheetDialogFragment;
import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.IProfileView;

public class ProfileActivity extends BaseActivity implements IProfileView{

    private ProfileController ctrl;

    private ImageView imageProfile;
    private FloatingActionButton imageFab;
    private ProgressBar imageProgressBar;

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


        imageFab = (FloatingActionButton) findViewById(R.id.image_upload_button);
        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        imageProgressBar = (ProgressBar) findViewById(R.id.progress_image);

        imageFab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openImageBottomSheet();

            }
        });

    }

    public void hideImageForm(){
        imageFab.setVisibility(View.GONE);
    }

    public void showImageForm(){
        imageFab.setVisibility(View.VISIBLE);
    }

    public void hideProgressImage(){
        imageProgressBar.setVisibility(View.GONE);
    }

    public void showProgressImage(){
        imageProgressBar.setVisibility(View.VISIBLE);
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
    @Override
    public void setProfileImage(String url, final IPromiseHandler handler) {
        Picasso.with(this)
                .load(url)
                .transform(new ImageUtils.CircleTransform())
                .into(imageProfile, new Callback() {
                    @Override
                    public void onSuccess() {
                        handler.onSuccess();
                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    @Override
    public void setProfileImage(int resourceId) {
        Picasso.with(this)
                .load(resourceId)
                .transform(new ImageUtils.CircleTransform())
                .into(imageProfile);
    }


    public void openImageBottomSheet(){
        ProfileBottomSheetDialogFragment bsdFragment = new ProfileBottomSheetDialogFragment();
        bsdFragment.show(getSupportFragmentManager(), "ImageProfileDialog");

    }




}
