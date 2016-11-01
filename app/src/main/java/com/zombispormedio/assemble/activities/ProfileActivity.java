package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.IProfileView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;


public class ProfileActivity extends BaseActivity implements IProfileView {

    private ExternalNavigationManager externalNavigationManager;

    private ProfileController ctrl;

    @BindView(R.id.image_profile)
    ImageView imageProfile;

    @BindView(R.id.image_upload_button)
    FloatingActionButton imageFab;

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

    public void hideImageProgressDialog() {
        imageProgressDialog.dismiss();
    }

    public void showImageProgressDialog() {
        imageProgressDialog.show();
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
    public void setBirthDate( ISODate birth, String def) {
        String text = birth != null ? birth.format(getString(R.string.born_at)) : def;
        birthDateText.setText(text);
    }


    @Override
    public void goToUpdateProfile() {
        NavigationManager.UpdateProfile(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
        externalNavigationManager.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ctrl.onStart();
    }

    @Override
    public void setProfileImage(@NonNull ImageUtils.ImageBuilder builder) {
        builder.context(this)
                .imageView(imageProfile)
                .build();
    }


    private void setupImageUploaderBottomSheet() {
        imageUploaderBottomSheet = AndroidUtils.createImageUploaderBottomSheet(this, item -> {
            switch (item.getId()) {
                case R.id.gallery:
                    externalNavigationManager.dispatchGalleryToSelectImage(R.string.select_picture);
                    break;
                case R.id.camera:
                    externalNavigationManager.dispatchTakePicture();
                    break;
            }
        });
    }


    public void openImageBottomSheet() {
        imageUploaderBottomSheet.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
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
