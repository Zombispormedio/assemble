package com.zombispormedio.assemble.activities;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.CreateMeetingController;
import com.zombispormedio.assemble.fragments.TeamDialogFragment;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.views.ICreateMeetingView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateMeetingActivity extends BaseActivity implements ICreateMeetingView {

    private CreateMeetingController ctrl;

    private TeamDialogFragment teamDialogFragment;

    @BindView(R.id.team_name)
    TextView teamName;

    @BindView(R.id.start_date_label)
    TextView startDateLabel;

    @BindView(R.id.start_hour_label)
    TextView startHourLabel;

    @BindView(R.id.end_date_label)
    TextView endDateLabel;

    @BindView(R.id.end_hour_label)
    TextView endHourLabel;

    @BindView(R.id.is_every_day)
    SwitchCompat isEveryDay;

    private DatePickerDialog startDatePickerDialog;

    private DatePickerDialog endDatePickerDialog;

    private TimePickerDialog startHourPickerDialog;

    private TimePickerDialog endHourPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        setupToolbar();
        setHomeUpIcon(R.drawable.close_home);
        bindActivity(this);

        ctrl = new CreateMeetingController(this);

        setupTeamDialog();

        setupAllDay();

        ctrl.onCreate();
    }




    private void setupTeamDialog() {
        teamDialogFragment = new TeamDialogFragment();
        teamDialogFragment.setOnItemClickedListener(new IOnClickItemListHandler<Team>() {
            @Override
            public void onClick(int position, Team data) {
                ctrl.onTeamSelected(data);
            }
        });
    }

    private void setupAllDay() {
        isEveryDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }


    @Override
    public void setupPickers(int year, int month, int day, int hour, int minutes) {

        startDatePickerDialog=new DatePickerDialog(this,R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        },   year,    month,    day);

        endDatePickerDialog=new DatePickerDialog(this,R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        },   year,    month,    day);

        startHourPickerDialog=new TimePickerDialog(this, R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }, hour, minutes, true);


        endHourPickerDialog=new TimePickerDialog(this, R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }, hour, minutes, true);

    }

    @OnClick(R.id.team_selector)
    public void onTeamSelector() {
        showTeamDialog();
    }


    @OnClick(R.id.save_button)
    public void onSave() {

    }

    @OnClick(R.id.start_date_label)
    public void onStartDateClick(){
        startDatePickerDialog.show();
    }

    @OnClick(R.id.end_date_label)
    public void onEndDateClick(){
        endDatePickerDialog.show();
    }

    @OnClick(R.id.start_hour_label)
    public void onStartHourClick(){
        startHourPickerDialog.show();
    }

    @OnClick(R.id.end_hour_label)
    public void onEndHourClick(){
        endHourPickerDialog.show();
    }


    @Override
    public void showTeamDialog() {
        teamDialogFragment.show(getSupportFragmentManager(), "fragment_team_dialog");
    }

    @Override
    public void bindTeam(String name) {
        String format = String.format(getString(R.string.one_string_complete), name);
        teamName.setText(format);
    }

    @Override
    public void bindStartDate(String start) {

        String formatted = AndroidUtils.formatDate(this, R.string.simple_date_with_name_of_day, start);
        startDateLabel.setText(formatted);

    }

    @Override
    public void bindStartHour(String start) {
        String formatted = AndroidUtils.formatDate(this, R.string.simple_hour, start);
        startHourLabel.setText(formatted);
    }

    @Override
    public void bindEndDate(String start) {
        String formatted = AndroidUtils.formatDate(this, R.string.simple_date_with_name_of_day, start);
        endDateLabel.setText(formatted);
    }

    @Override
    public void bindEndHour(String start) {
        String formatted = AndroidUtils.formatDate(this, R.string.simple_hour, start);
        endHourLabel.setText(formatted);
    }

    @Override
    public boolean getAllDay() {
        return isEveryDay.isChecked();
    }

    @Override
    public void hideHours() {
        startHourLabel.setVisibility(View.GONE);
        endHourLabel.setVisibility(View.GONE);
    }

    @Override
    public void showHours() {
        startHourLabel.setVisibility(View.VISIBLE);
        endHourLabel.setVisibility(View.VISIBLE);
    }


}
