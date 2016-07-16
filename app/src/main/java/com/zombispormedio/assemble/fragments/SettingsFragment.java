package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.SettingsActivity;
import com.zombispormedio.assemble.controllers.SettingsController;
import com.zombispormedio.assemble.views.ISettingsFragmentView;


public class SettingsFragment extends PreferenceFragment implements ISettingsFragmentView{
    private Preference signoutPref;
    private SettingsController ctrl;

    private SettingsActivity activity;


    public SettingsFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_settings);

        signoutPref=findPreference("settings_signout");

        activity=(SettingsActivity) getActivity();
        ctrl=activity.getController();

        ctrl.setFragmentView(this);

        signoutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ctrl.signout();
                return false;
            }
        });

    }

}
