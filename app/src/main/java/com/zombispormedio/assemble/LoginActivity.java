package com.zombispormedio.assemble;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener=FirebaseTools.checkAccess(this, NavigationAdapter.Type.LOGIN);
    }


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
        NavigationAdapter.Register(this);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
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
                            NavigationAdapter.Home(LoginActivity.this);
                        }else{

                            Tools.showAlert(LoginActivity.this, task.getException().getMessage());
                            enableLoginButton(true);

                        }

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
