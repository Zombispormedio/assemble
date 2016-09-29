package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.EditTeam;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.net.Error;
import com.zombispormedio.assemble.views.ISecondStepTeamView;

/**
 * Created by Xavier Serrano on 25/09/2016.
 */

public class SecondStepTeamController extends Controller {

    private ISecondStepTeamView ctx;

    private FriendResource friendResource;

    private TeamResource teamResource;

    private EditTeam.Builder editor;

    private String imagePath;



    public SecondStepTeamController(ISecondStepTeamView ctx, int[] friendIds) {
        super(ctx);
        this.ctx = ctx;

        friendResource=getResourceComponent().provideFriendResource();
        teamResource=getResourceComponent().provideTeamResource();
        editor=new EditTeam.Builder()
                .setMembers(friendIds);

        imagePath=null;
    }

    @Override
    public void onCreate() {
        bindParticipants();
    }

    private void bindParticipants() {
        int[] members=editor.getMembers();
        ctx.setParticipantsTitle(members.length, friendResource.countAll());
        ctx.bindParticipants(friendResource.getFriendInArrayofIds(members));
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void onDestroy() {
        ctx=null;
    }

    public void onCreateTeam() {
        editor.setName(ctx.getName());

        if(!editor.getName().isEmpty()){
            beginCreatingTeam();
        }else{
            ctx.showNameEmpty();
        }
    }

    private void beginCreatingTeam() {
        ctx.showProgress();


        teamResource.create(editor.build(), new ServiceHandler<Team, Error>(){
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterCreateTeam();
            }

            @Override
            public void onSuccess(Team result) {
                if(!uploadImage(result.id)){
                    afterCreateTeam();
                }
            }
        });


    }

    private void afterCreateTeam(){
        ctx.hideProgress();
        ctx.goHome();
    }

    private boolean uploadImage(int id){
        if(imagePath==null)return false;

        teamResource.uploadImage(id, imagePath, new ServiceHandler<Team, Error>(){
            @Override
            public void onError(Error error) {
                ctx.showAlert(error.msg);
                afterCreateTeam();
            }

            @Override
            public void onSuccess(Team result) {
                afterCreateTeam();
            }
        });

        return true;
    }
}
