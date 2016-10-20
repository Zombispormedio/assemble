package com.zombispormedio.assemble.activities;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.UpdateProfileController;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.IUpdateProfileView;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateProfileActivity extends BaseActivity implements IUpdateProfileView {

    private UpdateProfileController ctrl;

    @BindView(R.id.username_input)
    EditText usernameInput;

    @BindView(R.id.bio_input)
    EditText bioInput;

    @BindView(R.id.location_input)
    EditText locationInput;

    @BindView(R.id.birthdate_input)
    EditText birthdateInput;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        setupToolbar();
        bindActivity(this);

        ctrl = new UpdateProfileController(this);

        setupProgressDialog();

        ctrl.onCreate();
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.updating_profile_message));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    @OnClick(R.id.save_button)
    public void onSave(View view) {
        ctrl.save();
    }

    @OnClick(R.id.birthdate_input)
    public void onClickBirthDateInput(View view) {
        ctrl.editBirthDate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }

    @Override
    public void setUsername(String username) {
        usernameInput.setText(username);
    }

    @Override
    public void setBio(String bio) {
        bioInput.setText(bio);
    }

    @Override
    public void setBirthDate(ISODate birth, String def) {
        String text=birth!=null?birth.format(getString(R.string.simple_date)):def;
        birthdateInput.setText(text);
    }

    @Override
    public void setLocation(String location) {
        locationInput.setText(location);
    }


    @Override
    public String getUsername() {
        return usernameInput.getText().toString();
    }

    @Override
    public String getBio() {
        return bioInput.getText().toString();
    }


    @Override
    public String getLocation() {
        return locationInput.getText().toString();
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
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.dismiss();
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

        if (requestCode == NavigationManager.ACTIVITY_RESULT_CODE) {
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
