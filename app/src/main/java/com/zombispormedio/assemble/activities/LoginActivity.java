package com.zombispormedio.assemble.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.zombispormedio.assemble.BasicInput;
import com.zombispormedio.assemble.FirebaseTools;
import com.zombispormedio.assemble.controllers.LoginController;
import com.zombispormedio.assemble.utils.NavigationTools;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.utils.Tools;
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

    /*

    public void Login(View view){
        enableLoginButton(false);
        BasicInput email=createInput(R.id.email_input, R.string.email_empty);

        BasicInput pass=createInput(R.id.pass_input, R.string.pass_empty);

        if(!Tools.isEmptyForm(email, pass)){
            FirebaseLogin(email.getValue(), pass.getValue());
        }else{
            enableLoginButton(true);
        }


    }

    public void enableLoginButton(boolean e){
        Button b=(Button)findViewById(R.id.login_button);
        b.setEnabled(e);
    }

    public void SignUp(View view){
        NavigationTools.Register(this);
    }


    private BasicInput createInput(int id, int msg){
        return new BasicInput(this, (EditText) findViewById(id), msg);
    }

    private void FirebaseLogin(String email, String pass){

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Tools.showAlert(LoginActivity.this, R.string.login_message);
                            NavigationTools.Home(LoginActivity.this);
                        }else{

                            Tools.showAlert(LoginActivity.this, task.getException().getMessage());
                            enableLoginButton(true);

                        }

                    }
                });
    }*/

    public void goHome(){
        NavigationTools.Home(this);
        finish();
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
