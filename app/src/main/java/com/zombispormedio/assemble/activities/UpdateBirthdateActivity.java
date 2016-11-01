package com.zombispormedio.assemble.activities;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.UpdateBirthdateController;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.activities.IUpdateBirthdateView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;

import butterknife.BindView;
import butterknife.OnClick;


public class UpdateBirthdateActivity extends BaseActivity implements IUpdateBirthdateView {

    private UpdateBirthdateController ctrl;

    @Nullable
    @BindView(R.id.birthdate_picker)
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_birthdate);
        setupToolbar();
        bindActivity(this);

        ctrl = new UpdateBirthdateController(this);

        ctrl.onCreate();
    }

    @OnClick(R.id.save_button)
    public void OnSave(View view) {
        ctrl.save();
    }


    @Override
    public void finishWithResult(String... result) {
        NavigationManager.finishWithResult(this, result);
    }

    @Override
    public void setMaxDate(long time) {
        datePicker.setMaxDate(time);
    }

    @Override
    public int getDayOfBirthdate() {
        return datePicker.getDayOfMonth();
    }

    @Override
    public int getMonthOfBirthdate() {
        return datePicker.getMonth();
    }

    @Override
    public int getYearOfBirthdate() {
        return datePicker.getYear();
    }

    @Override
    public void setDatepickerValue(int year, int month, int day) {
        datePicker.updateDate(year, month, day);
    }

    @Override
    public String getInitBirthdate() {
        return getIntent().getStringExtra(NavigationManager.ARGS + 0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }
}
