package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.utils.DateUtils;
import com.zombispormedio.assemble.views.ICreateMeetingView;

/**
 * Created by Xavier Serrano on 09/09/2016.
 */
public class CreateMeetingController extends Controller {

    private ICreateMeetingView ctx;

    private TeamResource teamResource;

    public CreateMeetingController(ICreateMeetingView ctx) {
        super(ctx);
        this.ctx = ctx;

        teamResource=getResourceComponent().provideTeamResource();
    }

    public void onTeamSelected(Team data) {
        ctx.bindTeam(data.name);
    }

    @Override
    public void onCreate() {
        bindFirstTeam();
        bindDatesWithNow();
    }



    private void bindFirstTeam() {
        Team first=teamResource.getFirst();

        ctx.bindTeam(first.name);
    }


    private void bindDatesWithNow() {

        ctx.setupPickers(DateUtils.Now.YEAR, DateUtils.Now.MONTH, DateUtils.Now.DAY, DateUtils.Now.HOUR, DateUtils.Now.MINUTES);

        String now= DateUtils.Now.toDateString();

        ctx.bindStartDate(now);

        ctx.bindEndDate(now);

        ctx.bindStartHour(now);

        ctx.bindEndHour(now);
    }

}
