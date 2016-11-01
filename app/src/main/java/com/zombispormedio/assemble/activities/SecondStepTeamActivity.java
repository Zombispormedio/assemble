package com.zombispormedio.assemble.activities;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.ParticipantsListAdapter;
import com.zombispormedio.assemble.controllers.SecondStepTeamController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.ISecondStepTeamView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondStepTeamActivity extends BaseActivity implements ISecondStepTeamView {


    private SecondStepTeamController ctrl;

    private ExternalNavigationManager externalNavigationManager;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.participants_label)
    TextView participantsLabel;

    @BindView(R.id.name_input)
    EditText nameInput;

    @BindView(R.id.participants_list)
    RecyclerView participantsList;

    private ParticipantsListAdapter participantsListAdapter;

    private BottomSheetDialog imageUploaderBottomSheet;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_team);
        setupToolbar();
        bindActivity(this);
        setToolbarSubtitle(R.string.add_name);

        Bundle extra = getIntent().getExtras();

        ctrl = new SecondStepTeamController(this, extra.getIntArray(NavigationManager.ARGS + 0));
        externalNavigationManager = new ExternalNavigationManager(this);

        setupParticipants();

        setupImageUploaderBottomSheet();

        setupProgressDialog();

        ctrl.onCreate();
    }

    private void setupParticipants() {
        AndroidUtils.createListConfiguration(this, participantsList)
                .grid(true)
                .span(4)
                .itemAnimation(true)
                .configure();

        participantsListAdapter = new ParticipantsListAdapter();
        participantsList.setAdapter(participantsListAdapter);
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.creating_team));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    @Override
    public void setParticipantsTitle(int number, int total) {
        String participants = String.format(getString(R.string.participants_number), number, total);
        participantsLabel.setText(participants);
    }

    @NonNull
    @Override
    public String getName() {
        return nameInput.getText().toString();
    }

    @Override
    public void bindParticipants(@NonNull ArrayList<FriendProfile> data) {
        participantsListAdapter.addAll(data);
    }

    @Override
    public void bindImage(@NonNull String path) {
        new ImageUtils.ImageBuilder(this, imageView)
                .circle(true)
                .file(path)
                .build();
    }

    @Override
    public void showNameEmpty() {
        showAlert(getString(R.string.name_is_needed));
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void goHome() {
        NavigationManager.Home(this);
        finish();
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

    @OnClick(R.id.image_view)
    public void onClickImage(View view) {
        imageUploaderBottomSheet.show();
    }


    @OnClick(R.id.fab)
    public void onClickFab(View view) {
        ctrl.onCreateTeam();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        if (resultCode == RESULT_OK) {
            String path = null;
            int type = ExternalNavigationManager.getType(requestCode);
            switch (type) {

                case ExternalNavigationManager.REQUEST_CODE.GALLERY: {
                    Uri uri = externalNavigationManager.resolveGalleryPath(requestCode, data);
                    path = externalNavigationManager.getPath(uri);
                    break;
                }

                case ExternalNavigationManager.REQUEST_CODE.CAMERA: {
                    Uri uri = externalNavigationManager.resolveCameraPath(data);
                    path = externalNavigationManager.getRealPathFromCameraUri(uri);
                    break;
                }
            }

            if (path != null) {
                ctrl.setImagePath(path);
                bindImage(path);
            }
        }
    }
}
