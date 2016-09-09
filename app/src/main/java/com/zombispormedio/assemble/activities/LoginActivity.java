package com.zombispormedio.assemble.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.zombispormedio.assemble.controllers.LoginController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.views.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {


    @BindView(R.id.login_button)
    Button _loginButton;

    @BindView(R.id.progress_bar)
    ProgressBar _progressBar;

    @BindView(R.id.register_link)
    TextView _linkToRegister;

    @BindView(R.id.email_input)
    EditText _emailInput;

    @BindView(R.id.pass_input)
    EditText _passwordInput;

    @BindView(R.id.email_input_layout)
    TextInputLayout _emailInputLayout;

    @BindView(R.id.pass_input_layout)
    TextInputLayout _passwordInputLayout;

    private LoginController ctrl;

    private AndroidUtils.InputLayoutHelper _emailInputHelper;

    private AndroidUtils.InputLayoutHelper _passwordInputHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        ctrl = new LoginController(this);

        _emailInputHelper = new AndroidUtils.InputLayoutHelper(_emailInput, _emailInputLayout);
        _passwordInputHelper = new AndroidUtils.InputLayoutHelper(_passwordInput, _passwordInputLayout);

        _linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrl.linkToRegister();
            }
        });

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
        _progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        _progressBar.setVisibility(View.GONE);
    }

    public void showForm() {
        _emailInputHelper.show();
        _passwordInputHelper.show();
        _loginButton.setVisibility(View.VISIBLE);
        _linkToRegister.setVisibility(View.VISIBLE);
    }

    public void hideForm() {
        _emailInputHelper.hide();
        _passwordInputHelper.hide();
        _loginButton.setVisibility(View.GONE);
        _linkToRegister.setVisibility(View.GONE);
    }

    @Override
    public String getEmail() {
        return _emailInputHelper.getValue();

    }

    @Override
    public String getPassword() {
        return _passwordInputHelper.getValue();
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
            if (email.isEmpty()) {
                _emailInputHelper.setError(getString(R.string.email_empty));
            } else {
                _emailInputHelper.setError(getString(R.string.invalid_email));
            }

            valid = false;
        } else {
            _emailInputHelper.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 50) {
            if (password.isEmpty()) {
                _passwordInputHelper.setError(getString(R.string.pass_empty));
            } else {
                _passwordInputHelper.setError(getString(R.string.invalid_password));
            }

            valid = false;
        } else {
            _passwordInputHelper.setError(null);
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
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
