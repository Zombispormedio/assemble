package com.zombispormedio.assemble.activities;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.zombispormedio.assemble.controllers.RegisterController;
import com.zombispormedio.assemble.utils.NavigationUtils;
import com.zombispormedio.assemble.R;

import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IRegisterView;


public class RegisterActivity extends AppCompatActivity implements IRegisterView{
    private RegisterController ctrl;

    private EditText emailInput;
    private EditText passwordInput;
    private EditText repPasswordInput;
    private Button registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ctrl= new RegisterController(this);

        emailInput=(EditText) findViewById(R.id.email_input);
        passwordInput = (EditText) findViewById(R.id.pass_input);
        repPasswordInput = (EditText) findViewById(R.id.repeat_pass_input);
        registerButton = (Button) findViewById(R.id.register_button);
        progressBar = (ProgressBar) findViewById(R.id.registerProgressBar);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ctrl.onClickRegisterButton();
            }
        });

    }



    public void goMain(){
        NavigationUtils.Main(this);
        finish();
    }

    @Override
    public void showEmptyEmail() {
        Utils.showAlert(this, R.string.email_empty);
    }

    @Override
    public void showEmptyPassword() {
        Utils.showAlert(this, R.string.pass_empty);
    }

    @Override
    public void showEmptyRepPassword() {
        Utils.showAlert(this, R.string.rep_pass_empty);
    }

    @Override
    public void showNotEqualsBothPassword() {

        Utils.showAlert(this,   R.string.pass_equals);
    }

    @Override
    public void showAlert(String msg) {
        Utils.showAlert(this, msg);
    }

    @Override
    public void showSuccessfulRegister() {
        Utils.showAlert(RegisterActivity.this, R.string.success_signing_up);
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
