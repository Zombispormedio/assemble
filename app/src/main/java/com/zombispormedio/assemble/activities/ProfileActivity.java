package com.zombispormedio.assemble.activities;

import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cocosw.bottomsheet.BottomSheet;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;
import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.rest.FileBody;
import com.zombispormedio.assemble.rest.Request;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.IProfileView;

import java.io.File;


public class ProfileActivity extends BaseActivity implements IProfileView {


    private ExternalNavigationManager externalNavigationManager;

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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        externalNavigationManager = new ExternalNavigationManager(this);

        imageFab = (FloatingActionButton) findViewById(R.id.image_upload_button);
        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        imageProgressBar = (ProgressBar) findViewById(R.id.progress_image);

        ctrl = new ProfileController(this);

        imageFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openImageBottomSheet();

            }
        });


    }

    public void hideImageForm() {
        imageFab.setVisibility(View.GONE);
    }

    public void showImageForm() {
        imageFab.setVisibility(View.VISIBLE);
    }

    public void hideProgressImage() {
        imageProgressBar.setVisibility(View.GONE);
    }

    public void showProgressImage() {
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
    public void setProfileImage(String url, final ISuccessHandler handler) {
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


    public void loadDefaultImage(final ISuccessHandler handler) {
        Picasso.with(this)
                .load(R.drawable.profile_image_square)
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


    public void openImageBottomSheet() {
        new BottomSheet.Builder(this, R.style.BottomSheet_Dialog)
                .title(R.string.image_profile)
                .sheet(R.menu.menu_bottom_sheet)
                .listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case R.id.gallery:
                                externalNavigationManager.dispatchGalleryToSelectImage(R.string.select_picture);
                                break;
                            case R.id.camera:
                                externalNavigationManager.dispatchTakePicture();
                                break;
                        }

                    }
                }).show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            int type = ExternalNavigationManager.getType(requestCode);
            switch (type) {

                case ExternalNavigationManager.REQUEST_CODE.GALLERY: {
                    Uri uri = externalNavigationManager.resolveGalleryPath(requestCode, data);
                    String path = externalNavigationManager.getPath(uri);
                    ctrl.uploadAvatar(path);
                    break;
                }

                case ExternalNavigationManager.REQUEST_CODE.CAMERA: {
                    Uri uri = externalNavigationManager.resolveCameraPath(data);
                    String path = externalNavigationManager.getRealPathFromCameraUri(uri);
                    ctrl.uploadAvatar(path);
                    break;
                }
            }
        }
    }


}
