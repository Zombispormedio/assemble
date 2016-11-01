package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.LoginController;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.ILoginView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {


    @Nullable
    @BindView(R.id.login_button)
    Button loginButton;

    @Nullable
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Nullable
    @BindView(R.id.register_link)
    TextView linkToRegister;

    @Nullable
    @BindView(R.id.email_input)
    EditText emailInput;

    @Nullable
    @BindView(R.id.pass_input)
    EditText passwordInput;

    @Nullable
    @BindView(R.id.email_input_layout)
    TextInputLayout emailInputLayout;

    @Nullable
    @BindView(R.id.pass_input_layout)
    TextInputLayout passwordInputLayout;

    private LoginController ctrl;

    @Nullable
    private AndroidUtils.InputLayoutHelper emailInputHelper;

    @Nullable
    private AndroidUtils.InputLayoutHelper passwordInputHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindActivity(this);

        ctrl = new LoginController(this);

        emailInputHelper = new AndroidUtils.InputLayoutHelper(emailInput, emailInputLayout);
        passwordInputHelper = new AndroidUtils.InputLayoutHelper(passwordInput, passwordInputLayout);

    }

    @OnClick(R.id.login_button)
    public void onClickLoginButton(View view) {
        ctrl.login();
    }

    @OnClick(R.id.register_link)
    public void onClickRegisterLink(View view) {
        ctrl.linkToRegister();
    }


    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void showForm() {
        emailInputHelper.show();
        passwordInputHelper.show();
        loginButton.setVisibility(View.VISIBLE);
        linkToRegister.setVisibility(View.VISIBLE);
    }

    public void hideForm() {
        emailInputHelper.hide();
        passwordInputHelper.hide();
        loginButton.setVisibility(View.GONE);
        linkToRegister.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public String getEmail() {
        return emailInputHelper.getValue();

    }

    @NonNull
    @Override
    public String getPassword() {
        return passwordInputHelper.getValue();
    }


    public void showSuccessfulLogin() {
        AndroidUtils.showAlert(this, R.string.login_message);
    }

    @Override
    public boolean validate() {
        boolean valid = true;

        String email = getEmail();
        String password = getPassword();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputHelper.setError(email.isEmpty() ? getString(R.string.email_empty) : getString(R.string.invalid_email));
            valid = false;
        } else {
            emailInputHelper.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 50) {
            passwordInputHelper
                    .setError(password.isEmpty() ? getString(R.string.pass_empty) : getString(R.string.invalid_password));

            valid = false;
        } else {
            passwordInputHelper.setError(null);
        }

        return valid;
    }

    @Override
    public void showFailValidation() {
        AndroidUtils.showAlert(this, R.string.fail_login);
    }

    public void goHome() {
        NavigationManager.Home(this);
        finish();
    }

    public void goToRegister() {
        NavigationManager.Register(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
        emailInputHelper.preventLeakMemoryOnDestroy();
        passwordInputHelper.preventLeakMemoryOnDestroy();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
