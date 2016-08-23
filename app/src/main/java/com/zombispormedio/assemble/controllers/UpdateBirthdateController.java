package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.Profile;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IUpdateBirthdateView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class UpdateBirthdateController extends AbstractController {
    private IUpdateBirthdateView ctx;
    private CurrentUser user;

    public UpdateBirthdateController(IUpdateBirthdateView ctx) {
        this.ctx = ctx;
        user = CurrentUser.getInstance();
    }



    @Override
    public void onCreate() {
        super.onCreate();
        setMaxDate();
        initBirthdate();
    }

    private void initBirthdate() {
        Profile profile=user.getProfile();
        if(ctx!=null && profile !=null){
            if(Utils.presenceOf(profile.birth_date)){
                try {
                    Calendar parsedDate=DateUtils.parse(profile.birth_date);

                    int year=parsedDate.get(Calendar.YEAR);
                    int month=parsedDate.get(Calendar.MONTH);

                    int day=parsedDate.get(Calendar.DAY_OF_MONTH);

                    ctx.setDatepickerValue(year, month, day);

                } catch (ParseException e) {
                    Logger.d(e.getMessage());
                }
            }
        }
    }


    private void setMaxDate(){
        ctx.setMaxDate(DateUtils.appendYearsToNow(-8));
    }

    public void onSave(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx=null;
    }
}
