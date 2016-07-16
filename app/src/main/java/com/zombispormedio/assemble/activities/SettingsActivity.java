package com.zombispormedio.assemble.activities;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.fragments.SettingsFragment;
import com.zombispormedio.assemble.controllers.SettingsController;
import com.zombispormedio.assemble.listeners.IListener;
import com.zombispormedio.assemble.utils.NavigationUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.ISettingsView;


public class SettingsActivity extends AppCompatActivity implements ISettingsView {
    private Toolbar bar;
    private SettingsFragment frag;
    private SettingsController ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        bar=(Toolbar) findViewById(R.id.settings_bar);
        setSupportActionBar(bar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        ctrl= new SettingsController(this);

        frag=new SettingsFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, frag )
                .commit();



    }

    public SettingsController getController(){
        return ctrl;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ctrl.onDestroy();
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

    public void showConfirmSignOutDialog( IListener listener){
        String msg=getResources().getString(R.string.signout_dialog_msg);

        String positive=getResources().getString(R.string.yes_title);

        String negative=getResources().getString(R.string.no_title);

        Utils.createConfirmDialog(this, msg, positive, negative, Utils.createDialogClickListener(listener))
                .show();
    }

    @Override
    public void goToLogin() {
        NavigationUtils.Login(this);
    }
}
