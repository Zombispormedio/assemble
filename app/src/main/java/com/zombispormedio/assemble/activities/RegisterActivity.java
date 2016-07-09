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
import com.zombispormedio.assemble.utils.NavigationTools;
import com.zombispormedio.assemble.R;

import com.zombispormedio.assemble.utils.Tools;
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

    public void Register(View view){
       /* enableRegisterButton(false);

        BasicInput email=createInput(R.id.email_input, R.string.email_empty);

        BasicInput pass=createInput(R.id.pass_input, R.string.pass_empty);
        BasicInput rep_pass=createInput(R.id.repeat_pass_input, R.string.rep_pass_empty);

        if(!Tools.isEmptyForm(email, pass, rep_pass)){

            if(Tools.InputEquals(pass, rep_pass, R.string.pass_equals)){

                FirebaseRegister(email.getValue(), pass.getValue());

            }else{
                enableRegisterButton(true);
            }

        }else{
            enableRegisterButton(true);
        }*/

    }


  /*  private BasicInput createInput(int id, int msg){
        EditText edit=(EditText) findViewById(id);

        return new BasicInput(this, edit, msg);
    }*/

   /* public void FirebaseRegister(String email, String pass){

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            Tools.showAlert(RegisterActivity.this, R.string.error_sign_up);
                            enableRegisterButton(true);
                        }else{
                            Tools.showAlert(RegisterActivity.this, R.string.success_signing_up);
                            FirebaseAuth.getInstance().signOut();
                            goMain();
                        }
                    }
                });
    }*/

    public void goMain(){
        NavigationTools.Main(this);
        finish();
    }

    @Override
    public void showEmptyEmail() {
        Tools.showAlert(this, R.string.email_empty);
    }

    @Override
    public void showEmptyPassword() {
        Tools.showAlert(this, R.string.pass_empty);
    }

    @Override
    public void showEmptyRepPassword() {
        Tools.showAlert(this, R.string.rep_pass_empty);
    }

    @Override
    public void showNotEqualsBothPassword() {

        Tools.showAlert(this,   R.string.pass_equals);
    }

    @Override
    public void showAlert(String msg) {
        Tools.showAlert(this, msg);
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
