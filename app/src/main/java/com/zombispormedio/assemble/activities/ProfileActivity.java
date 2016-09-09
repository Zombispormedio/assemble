package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;

import android.content.Intent;


import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;


import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetItemClickListener;
import com.github.rubensousa.bottomsheetbuilder.items.BottomSheetMenuItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;

import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IProfileView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProfileActivity extends BaseActivity implements IProfileView {

    private ExternalNavigationManager externalNavigationManager;

    private ProfileController ctrl;

    @BindView(R.id.image_profile)
    ImageView _imageProfile;

    @BindView(R.id.image_upload_button)
    FloatingActionButton _imageFab;

    @BindView(R.id.progress_image)
    ProgressBar _imageProgress;

    @BindView(R.id.profile_username_text)
    TextView _usernameText;

    @BindView(R.id.profile_location_text)
    TextView _locationText;

    @BindView(R.id.profile_bio_text)
    TextView _bioText;

    @BindView(R.id.profile_birth_date_text)
    TextView _birthDateText;

    private ProgressDialog _imageProgressDialog;

    private BottomSheetDialog _imageUploaderBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupToolbar();
        ButterKnife.bind(this);

        externalNavigationManager = new ExternalNavigationManager(this);

        _imageProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        _imageProgressDialog.setMessage(getString(R.string.updating_profile_image));
        _imageProgressDialog.setIndeterminate(true);
        _imageProgressDialog.setCancelable(false);

        ctrl = new ProfileController(this);

        setupImageUploaderBottomSheet();

        ctrl.onCreate();
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
        _imageFab.setVisibility(View.INVISIBLE);
    }

    public void showImageForm() {
        _imageFab.setVisibility(View.VISIBLE);
    }

    public void hideImageProgressDialog() {
        _imageProgressDialog.dismiss();
    }

    public void showImageProgressDialog() {
        _imageProgressDialog.show();
    }

    @Override
    public void hideImageProgressBar() {
        _imageProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showImageProgressBar() {
        _imageProgress.setVisibility(View.VISIBLE);
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
        if (!birth.isEmpty()) {
            String birthDate = String.format(getString(R.string.born_at), birth);
            _birthDateText.setText(birthDate);
        } else {
            _birthDateText.setText("");
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

    public void loadLetterImage(String letter, final ISuccessHandler handler) {
        ColorGenerator generator = ColorGenerator.MATERIAL;

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter.toUpperCase(), generator.getRandomColor());

        _imageProfile.setImageDrawable(drawable);

        handler.onSuccess();

    }


    private void setupImageUploaderBottomSheet() {
        _imageUploaderBottomSheet = new BottomSheetBuilder(this, R.style.AppTheme_BottomSheetDialog)
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
        _imageUploaderBottomSheet.show();
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
