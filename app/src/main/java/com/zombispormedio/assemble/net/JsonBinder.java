package com.zombispormedio.assemble.net;

import com.zombispormedio.assemble.models.Auth;
import com.zombispormedio.assemble.models.editors.ChatEditor;
import com.zombispormedio.assemble.models.editors.GCMEditor;
import com.zombispormedio.assemble.models.editors.MeetingEditor;
import com.zombispormedio.assemble.models.editors.MessageEditor;
import com.zombispormedio.assemble.models.editors.ProfileEditor;
import com.zombispormedio.assemble.models.editors.TeamEditor;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.net.responses.ChatResponse;
import com.zombispormedio.assemble.net.responses.ChatsResponse;
import com.zombispormedio.assemble.net.responses.DefaultResponse;
import com.zombispormedio.assemble.net.responses.FriendRequestsResponse;
import com.zombispormedio.assemble.net.responses.FriendsResponse;
import com.zombispormedio.assemble.net.responses.MeetingResponse;
import com.zombispormedio.assemble.net.responses.MeetingsResponse;
import com.zombispormedio.assemble.net.responses.MessageResponse;
import com.zombispormedio.assemble.net.responses.MessagesResponse;
import com.zombispormedio.assemble.net.responses.ProfileResponse;
import com.zombispormedio.assemble.net.responses.TeamResponse;
import com.zombispormedio.assemble.net.responses.TeamsResponse;
import com.zombispormedio.assemble.wrappers.moshi.JSONWrapper;

import java.io.IOException;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class JsonBinder{

    /***FROM***/

    public static String fromUserProfile(UserProfile userProfile) {
        JSONWrapper<UserProfile> userAdapter = new JSONWrapper<>(UserProfile.class);
        return userAdapter.toJSON(userProfile);
    }

    public static String fromAuth(Auth user) {
        JSONWrapper<Auth> userAdapter = new JSONWrapper<>(Auth.class);
        return userAdapter.toJSON(user);
    }

    public static String fromEditProfile(ProfileEditor profile) {
        JSONWrapper<ProfileEditor> editProfileAdapter = new JSONWrapper<>(ProfileEditor.class);
        return editProfileAdapter.toJSON(profile);
    }

    public static String fromEditTeam(TeamEditor team) {
        JSONWrapper<TeamEditor> jsonAdapter = new JSONWrapper<>(TeamEditor.class);
        return jsonAdapter.toJSON(team);
    }

    public static String fromEditMeeting(MeetingEditor meeting) {
        JSONWrapper<MeetingEditor> jsonAdapter = new JSONWrapper<>(MeetingEditor.class);
        return jsonAdapter.toJSON(meeting);
    }


    public static String fromEditChat(ChatEditor chat) {
        JSONWrapper<ChatEditor> jsonAdapter = new JSONWrapper<>(ChatEditor.class);
        return jsonAdapter.toJSON(chat);
    }

    public static String fromEditMeesage(MessageEditor message) {
        JSONWrapper<MessageEditor> jsonAdapter = new JSONWrapper<>(MessageEditor.class);
        return jsonAdapter.toJSON(message);
    }

    public static String fromEditGCM(GCMEditor gcm) {
        JSONWrapper<GCMEditor> jsonAdapter = new JSONWrapper<>(GCMEditor.class);
        return jsonAdapter.toJSON(gcm);
    }


    /***TO***/
    public static DefaultResponse toDefaultResponse(String raw) throws IOException {
        JSONWrapper<DefaultResponse> jsonAdapter = new JSONWrapper<>(DefaultResponse.class);
        return jsonAdapter.fromJSON(raw);
    }

    public static ProfileResponse toProfileResponse(String raw) throws IOException {
        JSONWrapper<ProfileResponse> jsonAdapter = new JSONWrapper<>(ProfileResponse.class);
        return jsonAdapter.fromJSON(raw);
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

    public static TeamResponse toTeamResponse(String arg) throws IOException {
        JSONWrapper<TeamResponse> jsonAdapter = new JSONWrapper<>(TeamResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static MeetingsResponse toMeetingsResponse(String arg) throws IOException {
        JSONWrapper<MeetingsResponse> jsonAdapter = new JSONWrapper<>(MeetingsResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static MeetingResponse toMeetingResponse(String arg) throws IOException {
        JSONWrapper<MeetingResponse> jsonAdapter = new JSONWrapper<>(MeetingResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static ChatsResponse toChatsResponse(String arg) throws IOException {
        JSONWrapper<ChatsResponse> jsonAdapter=new JSONWrapper<>(ChatsResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static ChatResponse toChatResponse(String arg) throws IOException {
        JSONWrapper<ChatResponse> jsonAdapter=new JSONWrapper<>(ChatResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static MessagesResponse toMessagesResponse(String arg) throws IOException {
        JSONWrapper<MessagesResponse> jsonAdapter=new JSONWrapper<>(MessagesResponse.class);
        return jsonAdapter.fromJSON(arg);
    }

    public static MessageResponse toMessageResponse(String arg) throws IOException {
        JSONWrapper<MessageResponse> jsonAdapter=new JSONWrapper<>(MessageResponse.class);
        return jsonAdapter.fromJSON(arg);
    }








}
