package com.zombispormedio.assemble.activities;

import com.zombispormedio.assemble.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class UpdateBirthdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_birthdate);

        Toolbar bar = (Toolbar) findViewById(R.id.update_birthdate_bar);
        setSupportActionBar(bar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
