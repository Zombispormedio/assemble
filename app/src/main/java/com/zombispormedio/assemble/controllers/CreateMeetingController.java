package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.editors.EditMeeting;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.MeetingResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.views.activities.ICreateMeetingView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class CreateMeetingController extends Controller {

    private ICreateMeetingView ctx;

    private TeamResource teamResource;

    private MeetingResource meetingResource;

    private EditMeeting.Builder editor;

    private  String imagePath;

    private DateUtils.DateError dateError;



    public CreateMeetingController(ICreateMeetingView ctx) {
        super(ctx);
        this.ctx = ctx;

        teamResource = getResourceComponent().provideTeamResource();

        meetingResource=getResourceComponent().provideMeetingResource();

        editor = new EditMeeting.Builder();

        dateError=null;

        imagePath=null;
    }

    public void onTeamSelected(Team data) {
        editor.setTeam(data.id);
        ctx.bindTeam(data.name);
    }

    @Override
    public void onCreate() {
        bindFirstTeam();
        bindDatesWithNow();
    }

    private void bindFirstTeam() {
        Team first = teamResource.getFirst();
        editor.setTeam(first.id);
        ctx.bindTeam(first.name);
    }




    private void bindDatesWithNow() {

        DateUtils.DateBuilder startDate = editor.getStartAt();
        DateUtils.DateBuilder endDate = editor.getEndAt();

        ctx.setupStartDate(startDate.getYear(), startDate.getMonth(), startDate.getDay());

        ctx.setupEndDate(endDate.getYear(), endDate.getMonth(), endDate.getDay());

        ctx.setupStartHour(startDate.getHour(), startDate.getMinutes());

        ctx.setupEndHour(endDate.getHour(), endDate.getMinutes());

        String start=startDate.build();

        String end=endDate.build();

        ctx.bindStartDate(start);

        ctx.bindEndDate(end);

        ctx.bindStartHour(start);

        ctx.bindEndHour(end);
    }

    public void onStartDateChanged(int year, int month, int dayOfMonth) {
        DateUtils.DateBuilder start = editor.getStartAt();
        start.setYear(year)
                .setMonth(month)
                .setDay(dayOfMonth);
        ctx.bindStartDate(start.build());

        DateUtils.DateBuilder end=editor.getEndAt();
        if(start.compare(end)>0){
            ctx.updateEndDate(year, month, dayOfMonth);
            onEndDateChanged(year, month, dayOfMonth);
        }
    }

    public void onEndDateChanged(int year, int month, int dayOfMonth) {
        DateUtils.DateBuilder end = editor.getEndAt();
        end.setYear(year)
                .setMonth(month)
                .setDay(dayOfMonth);
        ctx.bindEndDate(end.build());

        checkEnd(end, false);
    }

    public void onStartHourChanged(int hourOfDay, int minute) {
        DateUtils.DateBuilder start = editor.getStartAt();
        start.setHour(hourOfDay)
                .setMinutes(minute);
        ctx.bindStartHour(start.build());
        DateUtils.DateBuilder end=editor.getEndAt();
        if(start.compare(end)>0){
            int endHour=(hourOfDay+1)%24;
            ctx.updateEndHour(endHour, 0);
            onEndHourChanged(endHour, 0);
        }
    }

    public void onEndHourChanged(int hourOfDay, int minute) {
        DateUtils.DateBuilder end = editor.getEndAt();
        end.setHour(hourOfDay)
                .setMinutes(minute);
        ctx.bindEndHour(end.build());
        checkEnd(end, true);
    }

    private void checkEnd(DateUtils.DateBuilder end, boolean hour) {
        if(end.compare(editor.getStartAt())<0){
            dateError=new DateUtils.DateError();
            if(hour){
                dateError.hour();
            }
            ctx.showErrorEndDate();
        }else{
            if(dateError!=null){
                dateError=null;
                ctx.hideErrorEndDate();
            }
        }
    }

    public void onAllDayChanged(boolean isChecked) {
        editor.setAllDay(isChecked);
        if(isChecked){
            ctx.hideHours();
        }else{
            ctx.showHours();
        }
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void save() {
        editor.setName(ctx.getName());

        if(dateError==null){
            createMeeting();
        }else{
           boolean isHour=dateError.isHour();

            if(!(isHour&&editor.isAllDay())){
                ctx.showDateErrorAlert();
            }
        }


    }

    private void createMeeting() {
        ctx.showProgress();

        meetingResource.create(editor.build(), new ServiceHandler<Meeting, Error>(){
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterCreateMeeting();
            }

            @Override
            public void onSuccess(Meeting result) {
                if(!uploadImage(result.id)){
                    afterCreateMeeting();
                }
            }
        });

    }

    private void afterCreateMeeting(){
        ctx.hideProgress();
        ctx.goHome();
    }


    private boolean uploadImage(int id){
        if(imagePath==null)return false;

        meetingResource.uploadImage(id, imagePath, new ServiceHandler<Meeting, Error>(){
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterCreateMeeting();
            }

            @Override
            public void onSuccess(Meeting result) {
                afterCreateMeeting();
            }
        });

        return true;
    }
}
