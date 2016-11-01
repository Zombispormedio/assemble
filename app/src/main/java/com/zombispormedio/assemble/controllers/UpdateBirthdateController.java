package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.utils.ISODate;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.activities.IUpdateBirthdateView;

import android.support.annotation.Nullable;


/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class UpdateBirthdateController extends Controller {

    @Nullable
    private IUpdateBirthdateView ctx;

    public UpdateBirthdateController(IUpdateBirthdateView ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setMaxDate();
        initBirthdate();
    }

    private void initBirthdate() {
        String birth = ctx.getInitBirthdate();
        ISODate date = Utils.presenceOf(birth) ? new ISODate(birth) : ISODate.Now();
        ctx.setDatepickerValue(date.getYear(), date.getMonth(), date.getDayOfMonth());
    }


    private void setMaxDate() {
        ISODate date = ISODate.Now();
        date.appendYears(-8);
        ctx.setMaxDate(date.getTimeInMilliseconds());
    }

    public void save() {
        int year = ctx.getYearOfBirthdate();
        int month = ctx.getMonthOfBirthdate();
        int day = ctx.getDayOfBirthdate();
        ISODate date = new ISODate(year, month, day);
        ctx.finishWithResult(date.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
