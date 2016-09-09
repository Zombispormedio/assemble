package com.zombispormedio.assemble.controllers;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.IUpdateBirthdateView;

import java.text.ParseException;
import java.util.Calendar;


/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class UpdateBirthdateController extends AbstractController {

    private IUpdateBirthdateView ctx;

    public UpdateBirthdateController(IUpdateBirthdateView ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setMaxDate();
        initBirthdate();
    }

    private void initBirthdate() {
        if (ctx != null) {
            String birth = ctx.getInitBirthdate();
            if (Utils.presenceOf(birth)) {
                try {
                    Calendar parsedDate = DateUtils.parse(birth);

                    int year = parsedDate.get(Calendar.YEAR);
                    int month = parsedDate.get(Calendar.MONTH);
                    int day = parsedDate.get(Calendar.DAY_OF_MONTH);

                    ctx.setDatepickerValue(year, month, day);

                } catch (ParseException e) {
                    Logger.d(e.getMessage());
                }
            }
        }
    }


    private void setMaxDate() {
        ctx.setMaxDate(DateUtils.appendYearsToNow(-8));
    }

    public void save() {
        int year = ctx.getYearOfBirthdate();
        int month = ctx.getMonthOfBirthdate();
        int day = ctx.getDayOfBirthdate();
        String date = DateUtils.toString(year, month, day);
        ctx.finishWithResult(date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
