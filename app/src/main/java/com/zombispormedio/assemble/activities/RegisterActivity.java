package com.zombispormedio.assemble.activities;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.zombispormedio.assemble.controllers.RegisterController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.R;

import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.IRegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseActivity implements IRegisterView {

    private RegisterController ctrl;

    @BindView(R.id.email_input)
    EditText emailInput;

    @BindView(R.id.pass_input)
    EditText passwordInput;

    @BindView(R.id.repeat_pass_input)
    EditText repPasswordInput;

    @BindView(R.id.register_button)
    Button registerButton;

    @BindView(R.id.register_progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupToolbar();
        bindActivity(this);
        ((Toolbar) findViewById(R.id.toolbar)).setTitle("");

        ctrl = new RegisterController(this);

    }

    @OnClick(R.id.register_button)
    public void onRegister(View view) {
        ctrl.register();
    }


    public void goToLogin() {
        NavigationManager.Login(this);
        finish();
    }

    @Override
    public void showEmptyEmail() {
        AndroidUtils.showAlert(this, R.string.email_empty);
    }

    @Override
    public void showEmptyPassword() {
        AndroidUtils.showAlert(this, R.string.pass_empty);
    }

    @Override
    public void showEmptyRepPassword() {
        AndroidUtils.showAlert(this, R.string.rep_pass_empty);
    }

    @Override
    public void showNotEqualsBothPassword() {
        AndroidUtils.showAlert(this, R.string.pass_equals);
    }

    @Override
    public void showSuccessfulRegister() {
        AndroidUtils.showAlert(this, R.string.success_signing_up);
    }

    @Override
    public void showUnknowError() {
        AndroidUtils.showAlert(this, R.string.unknow_error);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showForm() {
        emailInput.setVisibility(View.VISIBLE);
        passwordInput.setVisibility(View.VISIBLE);
        repPasswordInput.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideForm() {
        emailInput.setVisibility(View.GONE);
        passwordInput.setVisibility(View.GONE);
        repPasswordInput.setVisibility(View.GONE);
        registerButton.setVisibility(View.GONE);
    }

    @Override
    public String getEmail() {
        return emailInput.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordInput.getText().toString();
    }

    @Override
    public String getRepPassword() {
        return repPasswordInput.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
