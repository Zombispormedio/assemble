package com.zombispormedio.assemble.models.singletons;

import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.services.offline.OfflineProfileService;

import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class CurrentUser {

    private static CurrentUser ourInstance = new CurrentUser();

    public static CurrentUser getInstance() {
        return ourInstance;
    }

    private UserProfile profile;

    private ArrayList<FriendProfile> friends;

    private ArrayList<FriendRequestProfile> friendRequests;

    private ArrayList<Team> teams;

    private ArrayList<Meeting> meetings;

    private ArrayList<Chat> chats;

    private OfflineProfileService offlineProfile;


    private CurrentUser() {
        profile = new UserProfile();
        friends = new ArrayList<>();
        friendRequests = new ArrayList<>();
        teams=new ArrayList<>();
        meetings=new ArrayList<>();
        chats= new ArrayList<>();
        offlineProfile=new OfflineProfileService();
    }

    public UserProfile getProfile() {
        UserProfile off=offlineProfile.getFirst();
        if(off!=null){
            Logger.d(off.username);
        }

        return profile;
    }

    public boolean isProfileEmpty(){return profile.email.isEmpty();}

    public void setProfile(UserProfile profile) {
        this.profile = profile;
        offlineProfile.createOrUpdate(profile);
    }

    public ArrayList<FriendProfile> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<FriendProfile> friends) {
        this.friends = friends;
    }

    public int getFriendsCount() {
        return friends.size();
    }

    public int getFriendRequestsCount() {
        return friendRequests.size();
    }

    public ArrayList<FriendRequestProfile> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<FriendRequestProfile> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public int getTeamsCount(){
        return teams.size();
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
    }

    public int getMeetingsCount(){
        return meetings.size();
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public int getChatsCount(){
        return chats.size();
    }

    public static void reset() {
        ourInstance = new CurrentUser();
    }
}
