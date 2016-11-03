package com.zombispormedio.assemble.controllers;

import com.zombispormedio.assemble.handlers.ServiceHandler;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.editors.TeamEditor;
import com.zombispormedio.assemble.models.resources.FriendResource;
import com.zombispormedio.assemble.models.resources.TeamResource;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.views.activities.ISecondStepTeamView;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 25/09/2016.
 */

public class SecondStepTeamController extends Controller {


    private ISecondStepTeamView ctx;

    private final FriendResource friendResource;

    private final TeamResource teamResource;

    @NonNull
    private final TeamEditor.Builder editor;


    private String imagePath;


    public SecondStepTeamController(ISecondStepTeamView ctx, int[] friendIds) {
        super(ctx);
        this.ctx = ctx;

        friendResource = getResourceComponent().provideFriendResource();
        teamResource = getResourceComponent().provideTeamResource();
        editor = new TeamEditor.Builder()
                .setMembers(friendIds);

        imagePath = null;
    }

    @Override
    public void onCreate() {
        renderParticipants();
    }

    private void renderParticipants() {
        int[] members = editor.getMembers();
        ctx.setParticipantsTitle(members.length, friendResource.countAll());
        ctx.bindParticipants(friendResource.getFriendInArrayofIds(members));
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public void onCreateTeam() {
        editor.setName(ctx.getName());

        if (!editor.getName().isEmpty()) {
            beginCreatingTeam();
        } else {
            ctx.showNameEmpty();
        }
    }

    private void beginCreatingTeam() {
        ctx.showProgress();

        teamResource.create(editor.build(), new ServiceHandler<Team, Error>() {
            @Override
            public void onError(@NonNull Error error) {
                ctx.showAlert(error.msg);
                afterCreateTeam();
            }

            @Override
            public void onSuccess(@NonNull Team result) {
                if (uploadImage(result.id)) {
                    afterCreateTeam();
                }
            }
        });


    }

    private void afterCreateTeam() {
        ctx.hideProgress();
        ctx.goHome();
    }

    private boolean uploadImage(int id) {
        if (imagePath == null) {
            return true;
        }

        teamResource.uploadImage(id, imagePath, new ServiceHandler<Team, Error>() {
            @Override
            public void onError(@NonNull Error error) {
                ctx.showAlert(error.msg);
                afterCreateTeam();
            }

            @Override
            public void onSuccess(Team result) {
                afterCreateTeam();
            }
        });

        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ctx = null;
    }
}
