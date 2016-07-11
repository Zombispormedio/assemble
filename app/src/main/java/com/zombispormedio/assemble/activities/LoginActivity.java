package com.zombispormedio.assemble.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zombispormedio.assemble.controllers.LoginController;
import com.zombispormedio.assemble.utils.NavigationUtils;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.ILoginView;

public class LoginActivity extends AppCompatActivity  implements ILoginView{

    private LoginController ctrl;

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private ProgressBar progressBar;
    private TextView linkToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctrl= new LoginController(this);

        emailInput=(EditText) findViewById(R.id.email_input);
        passwordInput = (EditText) findViewById(R.id.pass_input);
        loginButton = (Button) findViewById(R.id.login_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linkToRegister=(TextView) findViewById(R.id.register_link);

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ctrl.onClickLoginButton();
            }
        });

        linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrl.onClickRegisterLink();
            }
        });

    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    public void showForm(){
        emailInput.setVisibility(View.VISIBLE);
        passwordInput.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
        linkToRegister.setVisibility(View.VISIBLE);
    }

    public void hideForm(){
        emailInput.setVisibility(View.GONE);
        passwordInput.setVisibility(View.GONE);
        loginButton.setVisibility(View.GONE);
        linkToRegister.setVisibility(View.GONE);
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
    public void showEmptyEmail() {
        Utils.showAlert(this, R.string.email_empty);
    }

    @Override
    public void showEmptyPassword() {
        Utils.showAlert(this, R.string.pass_empty);
    }

    public void showSuccessfulLogin(){
        Utils.showAlert(this,R.string.login_message);
    }

    public void showAlert(String msg){
        Utils.showAlert(this, msg);
    }


    public void goHome(){
        NavigationUtils.Home(this);
        finish();
    }

    public void goToRegister(){
        NavigationUtils.Register(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ctrl.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
       ctrl.onStop();
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
