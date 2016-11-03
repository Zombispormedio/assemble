package com.zombispormedio.assemble.models.services.api;

import com.zombispormedio.assemble.handlers.IServiceHandler;
import com.zombispormedio.assemble.handlers.PromiseHandler;
import com.zombispormedio.assemble.models.BaseModel;
import com.zombispormedio.assemble.models.Chat;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.models.FriendRequestProfile;
import com.zombispormedio.assemble.models.Meeting;
import com.zombispormedio.assemble.models.Message;
import com.zombispormedio.assemble.models.Team;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.network.Error;
import com.zombispormedio.assemble.network.JsonBinder;
import com.zombispormedio.assemble.network.Result;
import com.zombispormedio.assemble.network.responses.ArrayResponse;
import com.zombispormedio.assemble.network.responses.ChatResponse;
import com.zombispormedio.assemble.network.responses.ChatsResponse;
import com.zombispormedio.assemble.network.responses.DefaultResponse;
import com.zombispormedio.assemble.network.responses.FriendRequestsResponse;
import com.zombispormedio.assemble.network.responses.FriendsResponse;
import com.zombispormedio.assemble.network.responses.MeetingResponse;
import com.zombispormedio.assemble.network.responses.MeetingsResponse;
import com.zombispormedio.assemble.network.responses.MessageResponse;
import com.zombispormedio.assemble.network.responses.MessagesResponse;
import com.zombispormedio.assemble.network.responses.ProfileResponse;
import com.zombispormedio.assemble.network.responses.TeamResponse;
import com.zombispormedio.assemble.network.responses.TeamsResponse;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Xavier Serrano on 20/09/2016.
 */

class DeferUtils {

    @NonNull
    static PromiseHandler defer(IServiceHandler<Result, Error> handler) {
        return new PromiseHandler<DefaultResponse, Result>(handler);
    }


    @NonNull
    static PromiseHandler deferProfile(IServiceHandler<UserProfile, Error> handler) {
        return new PromiseHandler<ProfileResponse, UserProfile>(handler) {
            @Override
            protected ProfileResponse getResponse(String arg) throws IOException {
                return JsonBinder.toProfileResponse(arg);
            }
        };
    }

    @NonNull
    static PromiseHandler deferFriends(IServiceHandler<ArrayList<FriendProfile>, Error> handler) {
        return new ArrayPromiseHandler<FriendsResponse, FriendProfile>(handler) {
            @Override
            protected FriendsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toFriendsResponse(arg);
            }
        };
    }

    @NonNull
    static PromiseHandler deferFriendRequests(IServiceHandler<ArrayList<FriendRequestProfile>, Error> handler) {

        return new ArrayPromiseHandler<FriendRequestsResponse, FriendRequestProfile>(handler) {
            @Override
            protected FriendRequestsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toFriendRequestsResponse(arg);
            }
        };
    }


    @NonNull
    static PromiseHandler deferChats(IServiceHandler<ArrayList<Chat>, Error> handler) {
        return new ArrayPromiseHandler<ChatsResponse, Chat>(handler) {
            @Override
            protected ChatsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toChatsResponse(arg);
            }
        };
    }

    @NonNull
    static PromiseHandler deferChat(IServiceHandler<Chat, Error> handler) {
        return new PromiseHandler<ChatResponse, Chat>(handler) {
            @Override
            protected ChatResponse getResponse(String arg) throws IOException {
                return JsonBinder.toChatResponse(arg);
            }
        };
    }

    @NonNull
    static PromiseHandler deferMeetings(IServiceHandler<ArrayList<Meeting>, Error> handler) {
        return new ArrayPromiseHandler<MeetingsResponse, Meeting>(handler) {
            @Override
            protected MeetingsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toMeetingsResponse(arg);
            }

        };
    }

    @NonNull
    static PromiseHandler deferMeeting(IServiceHandler<Meeting, Error> handler) {
        return new PromiseHandler<MeetingResponse, Meeting>(handler) {

            @Override
            protected MeetingResponse getResponse(String arg) throws IOException {
                return JsonBinder.toMeetingResponse(arg);
            }
        };
    }

    @NonNull
    static PromiseHandler deferTeams(IServiceHandler<ArrayList<Team>, Error> handler) {
        return new ArrayPromiseHandler<TeamsResponse, Team>(handler) {

            @Override
            protected TeamsResponse getResponse(String arg) throws IOException {
                return JsonBinder.toTeamsResponse(arg);
            }
        };
    }

    @NonNull
    static PromiseHandler deferTeam(IServiceHandler<Team, Error> handler) {
        return new PromiseHandler<TeamResponse, Team>(handler) {

            @Override
            protected TeamResponse getResponse(String arg) throws IOException {
                return JsonBinder.toTeamResponse(arg);
            }
        };
    }


    @NonNull
    static PromiseHandler deferMessages(IServiceHandler<ArrayList<Message>, Error> handler) {
        return new ArrayPromiseHandler<MessagesResponse, Message>(handler) {

            @Override
            protected MessagesResponse getResponse(String arg) throws IOException {
                return JsonBinder.toMessagesResponse(arg);
            }
        };
    }

    @NonNull
    static PromiseHandler deferMessage(IServiceHandler<Message, Error> handler) {
        return new PromiseHandler<MessageResponse, Message>(handler) {

            @Override
            protected MessageResponse getResponse(String arg) throws IOException {
                return JsonBinder.toMessageResponse(arg);
            }
        };
    }


    private static class ArrayPromiseHandler<R extends ArrayResponse<M>, M extends BaseModel>
            extends PromiseHandler<R, ArrayList<M>> {

        ArrayPromiseHandler(
                IServiceHandler<ArrayList<M>, Error> handler) {
            super(handler);
        }

        @NonNull
        @Override
        protected ArrayList<M> getResult(@NonNull R res) {
            return res.getResult();
        }
    }

}
