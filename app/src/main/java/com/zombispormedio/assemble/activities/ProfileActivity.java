package com.zombispormedio.assemble.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zombispormedio.assemble.R;

public class ProfileActivity extends AppCompatActivity {
    private Toolbar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bar=(Toolbar) findViewById(R.id.profile_bar);
        setSupportActionBar(bar);

        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
