package com.zombispormedio.assemble.views;

import com.zombispormedio.assemble.models.Team;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 03/09/2016.
 */
public interface ITeamsView extends IFragmentView{

     void bindTeams(ArrayList<Team> data );

}
