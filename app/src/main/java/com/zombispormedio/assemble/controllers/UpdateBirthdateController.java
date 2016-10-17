package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.activities.IUpdateBirthdateView;

import java.util.Calendar;


/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class UpdateBirthdateController extends Controller {

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
        if (ctx != null) {
            String birth = ctx.getInitBirthdate();
            if (Utils.presenceOf(birth)) {
                Calendar parsedDate = DateUtils.parse(birth);
                int year = parsedDate.get(Calendar.YEAR);
                int month = parsedDate.get(Calendar.MONTH);
                int day = parsedDate.get(Calendar.DAY_OF_MONTH);
                ctx.setDatepickerValue(year, month, day);
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
        String date = DateUtils.convertToISOString(year, month, day);
        ctx.finishWithResult(date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
