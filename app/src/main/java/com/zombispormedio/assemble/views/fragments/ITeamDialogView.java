package com.zombispormedio.assemble.views.fragments;

import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.views.fragments.IFragmentView;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public interface ITeamDialogView extends IFragmentView {

    void bindTeams(ArrayList<Team> data);

}
