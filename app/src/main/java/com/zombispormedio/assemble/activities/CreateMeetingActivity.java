package com.zombispormedio.assemble.activities;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.github.rubensousa.bottomsheetbuilder.BottomSheetItemClickListener;
import com.github.rubensousa.bottomsheetbuilder.items.BottomSheetMenuItem;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.CreateMeetingController;
import com.zombispormedio.assemble.fragments.TeamDialogFragment;
import com.zombispormedio.assemble.handlers.IOnClickItemListHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.ICreateMeetingView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateMeetingActivity extends BaseActivity implements ICreateMeetingView {

    private CreateMeetingController ctrl;

    private ExternalNavigationManager externalNavigationManager;

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

    @BindView(R.id.name_input)
    EditText nameInput;


    @BindView(R.id.image_view)
    ImageView imageView;

    private DatePickerDialog startDatePickerDialog;

    private DatePickerDialog endDatePickerDialog;

    private TimePickerDialog startHourPickerDialog;

    private TimePickerDialog endHourPickerDialog;

    private BottomSheetDialog imageUploaderBottomSheet;

    private ProgressDialog progressDialog;

    private int defaultTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        setupToolbar();
        setHomeUpIcon(R.drawable.close_home);
        bindActivity(this);

        ctrl = new CreateMeetingController(this);
        externalNavigationManager = new ExternalNavigationManager(this);

        setupTeamDialog();

        setupAllDay();

        setupImageUploaderBottomSheet();

        setupProgressDialog();

        defaultTextColor = startDateLabel.getTextColors().getDefaultColor();

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
                ctrl.onAllDayChanged(isChecked);
            }
        });
    }

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.creating_meeting));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }


    @OnClick(R.id.team_selector)
    public void onTeamSelector() {
        showTeamDialog();
    }

    @OnClick(R.id.image_frame)
    public void onImage() {
        imageUploaderBottomSheet.show();
    }


    @OnClick(R.id.save_button)
    public void onSave() {
        ctrl.save();
    }

    @Override
    public void setupStartDate(int year, int month, int day) {

        startDatePickerDialog = new DatePickerDialog(this, R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ctrl.onStartDateChanged(year, month, dayOfMonth);
            }
        }, year, month, day);

    }

    @Override
    public void setupEndDate(int year, int month, int day) {
        endDatePickerDialog = new DatePickerDialog(this, R.style.DateDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ctrl.onEndDateChanged(year, month, dayOfMonth);
            }
        }, year, month, day);
    }

    @Override
    public void setupStartHour(int hour, int minutes) {
        startHourPickerDialog = new TimePickerDialog(this, R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                ctrl.onStartHourChanged(hourOfDay, minute);
            }
        }, hour, minutes, true);
    }

    @Override
    public void setupEndHour(int hour, int minutes) {
        endHourPickerDialog = new TimePickerDialog(this, R.style.DateDialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Logger.d(hourOfDay);
                ctrl.onEndHourChanged(hourOfDay, minute);
            }
        }, hour, minutes, true);
    }

    @Override
    public void updateStartDate(int year, int month, int day) {
        startDatePickerDialog.updateDate(year, month, day);
    }

    @Override
    public void updateEndDate(int year, int month, int day) {
        endDatePickerDialog.updateDate(year, month, day);
    }

    @Override
    public void updateStartHour(int hour, int minutes) {
        startHourPickerDialog.updateTime(hour, minutes);
    }

    @Override
    public void updateEndHour(int hour, int minutes) {
        endHourPickerDialog.updateTime(hour, minutes);
    }

    @Override
    public void showErrorEndDate() {
        startDateLabel.setTextColor(Color.RED);
        startHourLabel.setTextColor(Color.RED);
    }

    @Override
    public void hideErrorEndDate() {
        startDateLabel.setTextColor(defaultTextColor);
        startHourLabel.setTextColor(defaultTextColor);
    }

    @Override
    public void bindImage(String path) {
        new ImageUtils.ImageBuilder(this, imageView)
                .circle(true)
                .file(path)
                .build();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void goHome() {
        NavigationManager.Home(this);
    }

    @Override
    public String getName() {
        String value=nameInput.getText().toString();
        return value.isEmpty()?getString(R.string.untitled):value;
    }

    @Override
    public void showDateErrorAlert() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.date_error_message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        }).show();
    }

    @OnClick(R.id.start_date_label)
    public void onStartDateClick() {
        startDatePickerDialog.show();
    }

    @OnClick(R.id.end_date_label)
    public void onEndDateClick() {
        endDatePickerDialog.show();
    }

    @OnClick(R.id.start_hour_label)
    public void onStartHourClick() {
        startHourPickerDialog.show();
    }

    @OnClick(R.id.end_hour_label)
    public void onEndHourClick() {
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

    private void setupImageUploaderBottomSheet() {
        imageUploaderBottomSheet = AndroidUtils.createImageUploaderBottomSheet(this, new BottomSheetItemClickListener() {
            @Override
            public void onBottomSheetItemClick(BottomSheetMenuItem item) {
                switch (item.getId()) {
                    case R.id.gallery:
                        externalNavigationManager.dispatchGalleryToSelectImage(R.string.select_picture);
                        break;
                    case R.id.camera:
                        externalNavigationManager.dispatchTakePicture();
                        break;
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String path = null;
            int type = ExternalNavigationManager.getType(requestCode);
            switch (type) {

                case ExternalNavigationManager.REQUEST_CODE.GALLERY: {
                    Uri uri = externalNavigationManager.resolveGalleryPath(requestCode, data);
                    path = externalNavigationManager.getPath(uri);
                    break;
                }

                case ExternalNavigationManager.REQUEST_CODE.CAMERA: {
                    Uri uri = externalNavigationManager.resolveCameraPath(data);
                    path = externalNavigationManager.getRealPathFromCameraUri(uri);
                    break;
                }
            }

            if (path != null) {
                ctrl.setImagePath(path);
                bindImage(path);
            }
        }
    }


}
