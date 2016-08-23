package com.zombispormedio.assemble.activities;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.UpdateBirthdateController;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.IUpdateBirthdateView;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;



public class UpdateBirthdateActivity extends BaseActivity implements IUpdateBirthdateView {

    private UpdateBirthdateController ctrl;
    private DatePicker _datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_birthdate);

        Toolbar bar = (Toolbar) findViewById(R.id.update_birthdate_bar);
        setSupportActionBar(bar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ctrl=new UpdateBirthdateController(this);

        _datePicker= (DatePicker) findViewById(R.id.birthdate_picker);

        ((Button) findViewById(R.id.save_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctrl.onSave();
            }
        });

        ctrl.onCreate();
    }


    @Override
    public void finishWithResult(String... result) {
        NavigationManager.finishWithResult(this, result);
    }

    @Override
    public void setMaxDate(long time) {
        _datePicker.setMaxDate(time);
    }

    @Override
    public int getDayOfBirthdate() {
        return _datePicker.getDayOfMonth();
    }

    @Override
    public int getMonthOfBirthdate() {
        return _datePicker.getMonth();
    }

    @Override
    public int getYearOfBirthdate() {
        return _datePicker.getYear();
    }

    @Override
    public void setDatepickerValue(int year, int month, int day) {
        _datePicker.updateDate(year, month, day);
    }

    @Override
    public String getInitBirthdate() {
        return getIntent().getStringExtra(NavigationManager.ARGS+0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
