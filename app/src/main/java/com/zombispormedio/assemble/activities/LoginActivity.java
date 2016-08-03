package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
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

public class LoginActivity extends BaseActivity implements ILoginView {

    private LoginController ctrl;

    private EditText _emailInput;

    private EditText _passwordInput;

    private Button _loginButton;

    private ProgressBar _progressBar;

    private TextView _linkToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctrl = new LoginController(this);

        _emailInput = (EditText) findViewById(R.id.email_input);
        _passwordInput = (EditText) findViewById(R.id.pass_input);
        _loginButton = (Button) findViewById(R.id.login_button);
        _progressBar = (ProgressBar) findViewById(R.id.progressBar);
        _linkToRegister = (TextView) findViewById(R.id.register_link);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ctrl.onClickLoginButton();
            }
        });

        _linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrl.onClickRegisterLink();
            }
        });

    }

    public void showProgressBar() {
        _progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        _progressBar.setVisibility(View.GONE);
    }

    public void showForm() {
        _emailInput.setVisibility(View.VISIBLE);
        _passwordInput.setVisibility(View.VISIBLE);
        _loginButton.setVisibility(View.VISIBLE);
        _linkToRegister.setVisibility(View.VISIBLE);
    }

    public void hideForm() {
        _emailInput.setVisibility(View.GONE);
        _passwordInput.setVisibility(View.GONE);
        _loginButton.setVisibility(View.GONE);
        _linkToRegister.setVisibility(View.GONE);
    }

    @Override
    public String getEmail() {
        return _emailInput.getText().toString();
    }

    @Override
    public String getPassword() {
        return _passwordInput.getText().toString();
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
                _emailInput.setError(getString(R.string.email_empty));
            } else {
                _emailInput.setError(getString(R.string.invalid_email));
            }

            valid = false;
        } else {
            _emailInput.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 50) {
            if (password.isEmpty()) {
                _passwordInput.setError(getString(R.string.pass_empty));
            } else {
                _passwordInput.setError(getString(R.string.invalid_password));
            }

            valid = false;
        } else {
            _passwordInput.setError(null);
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
