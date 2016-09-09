package com.zombispormedio.assemble.activities;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.UpdateProfileController;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IUpdateProfileView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProfileActivity extends BaseActivity implements IUpdateProfileView {

    private UpdateProfileController ctrl;

    @BindView(R.id.username_input)
    EditText _usernameInput;

    @BindView(R.id.bio_input)
    EditText _bioInput;

    @BindView(R.id.location_input)
    EditText _locationInput;

    @BindView(R.id.birthdate_input)
    EditText _birthdateInput;

    private ProgressDialog _progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        setupToolbar();
        ButterKnife.bind(this);

        _progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        _progressDialog.setMessage(getString(R.string.updating_profile_message));
        _progressDialog.setIndeterminate(true);
        _progressDialog.setCancelable(false);

        ctrl = new UpdateProfileController(this);

        ctrl.onCreate();
    }

    @OnClick(R.id.save_button)
    public void onSave(View view){
        ctrl.save();
    }

    @OnClick(R.id.birthdate_input)
    public void onClickBirthDateInput(View view){
        ctrl.editBirthDate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }

    @Override
    public void setUsername(String username) {
        _usernameInput.setText(username);
    }

    @Override
    public void setBio(String bio) {
        _bioInput.setText(bio);
    }

    @Override
    public void setBirthDate(String birth) {
        _birthdateInput.setText(birth);
    }

    @Override
    public void setLocation(String location) {
        _locationInput.setText(location);
    }


    @Override
    public String getUsername() {
        return _usernameInput.getText().toString();
    }

    @Override
    public String getBio() {
        return _bioInput.getText().toString();
    }


    @Override
    public String getLocation() {
        return _locationInput.getText().toString();
    }


    @Override
    public void goToUpdateBirthdate(String... args) {
        NavigationManager.UpdateBirthdate(this, args);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void openProgressDialog() {
        _progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        _progressDialog.dismiss();
    }

    @Override
    public void showRejectChangesDialog(ISuccessHandler listener) {
        String msg = getResources().getString(R.string.reject_changes_title);

        String positive = getResources().getString(R.string.delete_title);

        String negative = getResources().getString(R.string.cancel_title);

        AndroidUtils.createConfirmDialog(this, msg, positive, negative, AndroidUtils.createDialogClickListener(listener))
                .show();
    }

    @Override
    public void onBackPressed() {
        ctrl.checkChanges();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ctrl.checkChanges();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NavigationManager.UPDATE_BIRTHDATE_CODE) {
            if (resultCode == RESULT_OK) {
                int countArgs = data.getIntExtra(NavigationManager.SIZE, 0);
                if (countArgs > 0) {
                    String birthdate = data.getStringExtra(NavigationManager.ARGS + 0);
                    ctrl.updateBirthdate(birthdate);
                }

            }
        }


    }
}
