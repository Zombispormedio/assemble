package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.models.User;
import com.zombispormedio.assemble.views.IMainView;
import com.zombispormedio.assemble.wrappers.firebase.FirebaseAuthWrapper;

/**
 * Created by Master on 08/07/2016.
 */
public class MainController implements IBaseController {

    private static final String TAG = MainController.class.getName();

    private IMainView ctx;
    private User user;
    private User.AccessVerifier verifier;
    public MainController(IMainView ctx) {
        this.ctx=ctx;
        user=new User(new FirebaseAuthWrapper());
        verifier=user.createAccessVerifier(new AccessListener());
    }

    public class AccessListener implements IBaseListener<Integer>{

        @Override
        public void onError(Integer... args) {

            ctx.goToLogin();
        }

        @Override
        public void onSuccess(Integer... args) {
            ctx.goHome();
        }
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }

    @Override
    public void onStart() {
        verifier.start();
    }

    @Override
    public void onStop() {
        verifier.stop();
    }
}
