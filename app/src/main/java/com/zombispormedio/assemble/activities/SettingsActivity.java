package com.zombispormedio.assemble.activities;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.SettingsController;
import com.zombispormedio.assemble.fragments.SettingsFragment;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.ISettingsView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;


public class SettingsActivity extends BaseActivity implements ISettingsView {

    private SettingsController ctrl;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupToolbar();

        ctrl = new SettingsController(this);

        SettingsFragment frag = new SettingsFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, frag)
                .commit();

        setupProgressDialog();

    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.loading_app_data));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }


    public SettingsController getController() {
        return ctrl;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ctrl.onDestroy();
    }

    public void showConfirmSignOutDialog(@NonNull ISuccessHandler listener) {
        String msg = getResources().getString(R.string.signout_dialog_msg);

        String positive = getResources().getString(R.string.yes_title);

        String negative = getResources().getString(R.string.no_title);

        AndroidUtils.createConfirmDialog(this, msg, positive, negative, AndroidUtils.createDialogClickListener(listener))
                .show();
    }

    @Override
    public void goToLogin() {
        NavigationManager.Login(this);
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

}
