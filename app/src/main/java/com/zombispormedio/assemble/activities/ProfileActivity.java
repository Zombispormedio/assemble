package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;

import android.content.Intent;


import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetItemClickListener;
import com.github.rubensousa.bottomsheetbuilder.items.BottomSheetMenuItem;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;

import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IProfileView;

import butterknife.BindView;
import butterknife.OnClick;


public class ProfileActivity extends BaseActivity implements IProfileView {

    private ExternalNavigationManager externalNavigationManager;

    private ProfileController ctrl;

    @BindView(R.id.image_profile)
    ImageView imageProfile;

    @BindView(R.id.image_upload_button)
    FloatingActionButton imageFab;

    @BindView(R.id.progress_image)
    ProgressBar imageProgress;

    @BindView(R.id.profile_username_text)
    TextView usernameText;

    @BindView(R.id.profile_location_text)
    TextView locationText;

    @BindView(R.id.profile_bio_text)
    TextView bioText;

    @BindView(R.id.profile_birth_date_text)
    TextView birthDateText;

    private ProgressDialog imageProgressDialog;

    private BottomSheetDialog imageUploaderBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupToolbar();
        bindActivity(this);

        ctrl = new ProfileController(this);

        externalNavigationManager = new ExternalNavigationManager(this);

        setupImageProgressDialog();

        setupImageUploaderBottomSheet();

        ctrl.onCreate();
    }

    private void setupImageProgressDialog() {
        imageProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        imageProgressDialog.setMessage(getString(R.string.updating_profile_image));
        imageProgressDialog.setIndeterminate(true);
        imageProgressDialog.setCancelable(false);
    }

    @OnClick(R.id.update_profile_button)
    public void onUpdateProfileButton(View view) {
        ctrl.updateProfile();
    }

    @OnClick(R.id.image_upload_button)
    public void onImageFab(View view) {
        openImageBottomSheet();
    }

    public void hideImageForm() {
        imageFab.setVisibility(View.INVISIBLE);
    }

    public void showImageForm() {
        imageFab.setVisibility(View.VISIBLE);
    }

    public void hideImageProgressDialog() {
        imageProgressDialog.dismiss();
    }

    public void showImageProgressDialog() {
        imageProgressDialog.show();
    }

    @Override
    public void hideImageProgressBar() {
        imageProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showImageProgressBar() {
        imageProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUsername(String name) {
        usernameText.setText(name);
    }

    @Override
    public void setLocation(String location) {
        locationText.setText(location);
    }

    @Override
    public void setBio(String bio) {
        bioText.setText(bio);
    }

    @Override
    public void setBirthDate(String birth) {
        if (!birth.isEmpty()) {
            String birthDate = String.format(getString(R.string.born_at), birth);
            birthDateText.setText(birthDate);
        } else {
            birthDateText.setText("");
        }

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
    protected void onStart() {
        super.onStart();
        ctrl.onStart();
    }

    @Override
    public void setProfileImage(String url, String letter, final ISuccessHandler handler) {
        new ImageUtils.ImageBuilder(this, imageProfile)
                .circle(true)
                .url(url)
                .handle(handler)
                .letter(letter)
                .build();
    }


    public void loadLetterImage(String letter, final ISuccessHandler handler) {
        new ImageUtils.ImageBuilder(this, imageProfile)
                .circle(true)
                .handle(handler)
                .letter(letter)
                .build();
    }


    private void setupImageUploaderBottomSheet() {
        imageUploaderBottomSheet = new BottomSheetBuilder(this, R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setBackground(R.color.colorWhite)
                .setMenu(R.menu.menu_bottom_sheet)
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(BottomSheetMenuItem item) {
                        switch (item.getId()) {
                            case R.id.gallery:
                                externalNavigationManager.dispatchGalleryToSelectImage(R.string.select_picture);
                                break;
                            case R.id.camera:
                                externalNavigationManager.dispatchTakePicture();
                                break;
                        }
                    }
                })
                .createDialog();
    }


    public void openImageBottomSheet() {
        imageUploaderBottomSheet.show();
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
