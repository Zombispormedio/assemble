package com.zombispormedio.assemble.models.singletons;


import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.IDataSubscription;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.models.subscriptions.Subscription;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Xavier Serrano on 30/07/2016.
 */
public class CurrentUser {

    private static CurrentUser ourInstance = new CurrentUser();

    public static CurrentUser getInstance() {
        return ourInstance;
    }

    /***** Deprecated ***/
    private UserProfile profile;

    private ArrayList<FriendProfile> friends;

    private ArrayList<FriendRequestProfile> friendRequests;


    /********************/


    private ProfileSubscription profileSubscription;

    private FriendSubscription friendSubscription;

    private FriendRequestSubscription friendRequestSubscription;

    private MeetingSubscription meetingSubscription;

    private TeamSubscription teamSubscription;

    private ChatSubscription chatSubscription;


    private CurrentUser() {
        /***** Deprecated***/
        profile = new UserProfile();
        friends = new ArrayList<>();
        friendRequests = new ArrayList<>();
        /********************/

        profileSubscription = new ProfileSubscription();
        friendSubscription = new FriendSubscription();
        friendRequestSubscription= new FriendRequestSubscription();
        meetingSubscription = new MeetingSubscription();
        teamSubscription = new TeamSubscription();
        chatSubscription = new ChatSubscription();

    }

    /***** Deprecated ***/
    public UserProfile getProfile() {
        return profile;
    }

    public boolean isProfileEmpty() {
        return profile.email.isEmpty();
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
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



    /********************/

    public static void reset() {
        ourInstance = new CurrentUser();
    }

    public ProfileSubscription getProfileSubscription() {
        return profileSubscription;
    }

    public FriendSubscription getFriendSubscription() {
        return friendSubscription;
    }

    public FriendRequestSubscription getFriendRequestSubscription() {
        return friendRequestSubscription;
    }

    public MeetingSubscription getMeetingSubscription() {
        return meetingSubscription;
    }

    public TeamSubscription getTeamSubscription() {
        return teamSubscription;
    }

    public ChatSubscription getChatSubscription() {
        return chatSubscription;
    }


    public void loadAll(){
        ArrayList<IDataSubscription> subscriptions= new ArrayList<IDataSubscription>(
                Arrays.asList(profileSubscription,
                friendSubscription, friendRequestSubscription,
                meetingSubscription, teamSubscription, chatSubscription));

        for(IDataSubscription subscription : subscriptions){
            subscription.load();
        }

    }
}
