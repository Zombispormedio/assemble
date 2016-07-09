package com.zombispormedio.assemble;

import android.content.Context;
import android.support.annotation.NonNull;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zombispormedio.assemble.utils.NavigationTools;

/**
 * Created by Master on 27/06/2016.
 */
public class FirebaseTools {
    private static final String TAG = "FirebaseTools";

    public static FirebaseAuth.AuthStateListener checkAccess(NavigationTools acc) {
        final NavigationTools accesor = acc;
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                accesor.Combo(user != null);


            }
        };
    }

    public static FirebaseAuth.AuthStateListener checkAccess(Context ctx, NavigationTools.Type accesor_type) {
        final NavigationTools accesor = new NavigationTools(ctx, accesor_type);
        return checkAccess(accesor);
    }

    public static FirebaseAuth.AuthStateListener checkAccess(Context ctx) {
        NavigationTools accesor = new NavigationTools(ctx);
        return checkAccess(accesor);
    }


}
