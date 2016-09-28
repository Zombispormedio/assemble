package com.zombispormedio.assemble.net;

import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.models.EditMeeting;
import com.zombispormedio.assemble.models.EditProfile;
import com.zombispormedio.assemble.models.EditTeam;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.net.responses.AbstractResponse;
import com.zombispormedio.assemble.net.responses.ChatsResponse;
import com.zombispormedio.assemble.net.responses.DefaultResponse;
import com.zombispormedio.assemble.net.responses.FriendRequestsResponse;
import com.zombispormedio.assemble.net.responses.FriendsResponse;
import com.zombispormedio.assemble.net.responses.MeetingResponse;
import com.zombispormedio.assemble.net.responses.MeetingsResponse;
import com.zombispormedio.assemble.net.responses.ProfileResponse;
import com.zombispormedio.assemble.net.responses.TeamResponse;
import com.zombispormedio.assemble.net.responses.TeamsResponse;
import com.zombispormedio.assemble.wrappers.moshi.JSONWrapper;

import java.io.IOException;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class JsonBinder<R extends AbstractResponse> {


    public static DefaultResponse toDefaultResponse(String raw) throws IOException {
        JSONWrapper<DefaultResponse> jsonAdapter = new JSONWrapper<>(DefaultResponse.class);
        return jsonAdapter.fromJSON(raw);
    }

    public static String fromUserProfile(UserProfile userProfile) {
        JSONWrapper<UserProfile> userAdapter = new JSONWrapper<>(UserProfile.class);
        return userAdapter.toJSON(userProfile);
    }

    public static ProfileResponse toProfileResponse(String raw) throws IOException {
        JSONWrapper<ProfileResponse> jsonAdapter = new JSONWrapper<>(ProfileResponse.class);
        return jsonAdapter.fromJSON(raw);
    }

    public static String fromAuth(Auth user) {
        JSONWrapper<Auth> userAdapter = new JSONWrapper<>(Auth.class);
        return userAdapter.toJSON(user);
    }

    public static String fromEditProfile(EditProfile profile) {
        JSONWrapper<EditProfile> editProfileAdapter = new JSONWrapper<>(EditProfile.class);
        return editProfileAdapter.toJSON(profile);
    }

    public static String fromEditTeam(EditTeam team) {
        JSONWrapper<EditTeam> jsonAdapter = new JSONWrapper<>(EditTeam.class);
        return jsonAdapter.toJSON(team);
    }

    public static String fromEditMeeting(EditMeeting meeting) {
        JSONWrapper<EditMeeting> jsonAdapter = new JSONWrapper<>(EditMeeting.class);
        return jsonAdapter.toJSON(meeting);
    }

    public static FriendsResponse toFriendsResponse(String arg) throws IOException {
        JSONWrapper<FriendsResponse> jsonAdapter = new JSONWrapper<>(FriendsResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static FriendRequestsResponse toFriendRequestsResponse(String arg) throws IOException {
        JSONWrapper<FriendRequestsResponse> jsonAdapter = new JSONWrapper<>(FriendRequestsResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static TeamsResponse toTeamsResponse(String arg) throws IOException {
        JSONWrapper<TeamsResponse> jsonAdapter = new JSONWrapper<>(TeamsResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static MeetingsResponse toMeetingsResponse(String arg) throws IOException {
        JSONWrapper<MeetingsResponse> jsonAdapter = new JSONWrapper<>(MeetingsResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static ChatsResponse toChatsResponse(String arg) throws IOException {
        JSONWrapper<ChatsResponse> jsonAdapter=new JSONWrapper<>(ChatsResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static TeamResponse toTeamResponse(String arg) throws IOException {
        JSONWrapper<TeamResponse> jsonAdapter = new JSONWrapper<>(TeamResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static MeetingResponse toMeetingResponse(String arg) throws IOException {
        JSONWrapper<MeetingResponse> jsonAdapter = new JSONWrapper<>(MeetingResponse.class);
        return jsonAdapter.fromJSON(arg);
    }




}
