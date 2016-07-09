package com.zombispormedio.assemble.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.zombispormedio.assemble.BasicInput;
import com.zombispormedio.assemble.NavigationAdapter;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.Tools;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "Register";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

    }

    public void Register(View view){
        enableRegisterButton(false);

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
        }

    }

    public void enableRegisterButton(boolean e){
        Button b=(Button)findViewById(R.id.register_button);
        b.setEnabled(e);
    }

    private BasicInput createInput(int id, int msg){
        EditText edit=(EditText) findViewById(id);

        return new BasicInput(this, edit, msg);
    }

    public void FirebaseRegister(String email, String pass){

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
    }

    public void goMain(){
        NavigationAdapter.Main(this);
    }



}
