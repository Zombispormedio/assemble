package com.zombispormedio.assemble.activities;

import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;


import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;

import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IProfileView;


public class ProfileActivity extends BaseActivity implements IProfileView {

    private ExternalNavigationManager externalNavigationManager;

    private ProfileController ctrl;

    private ImageView _imageProfile;

    private FloatingActionButton _imageFab;

    private ProgressBar _imageProgressBar;

    private TextView _usernameText;
    private TextView _locationText;
    private TextView _bioText;
    private TextView _birthDateText;
    private Button _updateProfileButton;

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

        _imageFab = (FloatingActionButton) findViewById(R.id.image_upload_button);
        _imageProfile = (ImageView) findViewById(R.id.imageProfile);
        _imageProgressBar = (ProgressBar) findViewById(R.id.progress_image);

        _usernameText = (TextView) findViewById(R.id.profile_username_text);
        _locationText = (TextView) findViewById(R.id.profile_location_text);
        _bioText = (TextView) findViewById(R.id.profile_bio_text);
        _birthDateText = (TextView) findViewById(R.id.profile_birth_date_text);
        _updateProfileButton = (Button) findViewById(R.id.update_profile_button);

        ctrl = new ProfileController(this);

        _imageFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openImageBottomSheet();

            }
        });

        _updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrl.updateProfile();
            }
        });

        ctrl.onCreate();
    }

    public void hideImageForm() {
        _imageFab.setVisibility(View.GONE);
    }

    public void showImageForm() {
        _imageFab.setVisibility(View.VISIBLE);
    }

    public void hideProgressImage() {
        _imageProgressBar.setVisibility(View.GONE);
    }

    public void showProgressImage() {
        _imageProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUsername(String name) {
        _usernameText.setText(name);
    }

    @Override
    public void setLocation(String location) {
        _locationText.setText(location);
    }

    @Override
    public void setBio(String bio) {
        _bioText.setText(bio);
    }

    @Override
    public void setBirthDate(String birth) {
        String birthDate=String.format(getString(R.string.born_at), birth);
        _birthDateText.setText(birthDate);
    }

    @Override
    public void goToUpdateProfile() {
        NavigationManager.UpdateProfile(this);
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
                .into(_imageProfile, new Callback() {
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
                .into(_imageProfile, new Callback() {
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
