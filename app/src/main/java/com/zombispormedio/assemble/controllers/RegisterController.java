package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.listeners.IListenerWithArgs;
import com.zombispormedio.assemble.models.builders.ModelBuilder;
import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.views.IRegisterView;

/**
 * Created by Master on 09/07/2016.
 */
public class RegisterController implements IBaseController {

    private IRegisterView ctx;
    private User user;

    public RegisterController(IRegisterView ctx) {
        this.ctx = ctx;

        user= ModelBuilder.createUser();
    }

    public void onClickRegisterButton() {
        whileLogin();

        String email=ctx.getEmail();
        String pass=ctx.getPassword();
        String rep_pass=ctx.getRepPassword();

        if(email.isEmpty() || pass.isEmpty() || rep_pass.isEmpty()){
            afterTryLogin();
            if(email.isEmpty()){
                ctx.showEmptyEmail();
            }else{
                if(pass.isEmpty()){
                    ctx.showEmptyPassword();
                }else{
                    if(rep_pass.isEmpty()){
                        ctx.showEmptyRepPassword();
                    }
                }
            }
        }else{
            if(!pass.equals(rep_pass)){
                afterTryLogin();
                ctx.showNotEqualsBothPassword();

            }else{
                user.create(email, pass, new CreateListener());
            }


        }
    }

    private class CreateListener implements IListenerWithArgs<String> {
        @Override
        public void onError(String... args) {
            String error=args[0];
            ctx.showAlert(error);
            afterTryLogin();
        }

        @Override
        public void onSuccess(String... args) {

            user.signOut();
            ctx.showSuccessfulRegister();
            ctx.goMain();

        }
    }

    private void whileLogin(){
        ctx.hideForm();
        ctx.showProgressBar();
    }

    private void afterTryLogin(){
        ctx.hideProgressBar();
        ctx.showForm();
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


}
