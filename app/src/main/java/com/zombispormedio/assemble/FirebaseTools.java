package com.zombispormedio.assemble;

import android.content.Context;
import android.support.annotation.NonNull;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Master on 27/06/2016.
 */
public class FirebaseTools {
    private static final String TAG = "FirebaseTools";

    public static FirebaseAuth.AuthStateListener checkAccess(Navigation acc) {
        final Navigation accesor = acc;
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                accesor.Combo(user != null);


            }
        };
    }

    public static FirebaseAuth.AuthStateListener checkAccess(Context ctx, Navigation.Type accesor_type) {
        final Navigation accesor = new Navigation(ctx, accesor_type);
        return checkAccess(accesor);
    }

    public static FirebaseAuth.AuthStateListener checkAccess(Context ctx) {
        Navigation accesor = new Navigation(ctx);
        return checkAccess(accesor);
    }


}
