package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.MessageSubscription;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.views.activities.IHomeView;


/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class HomeController extends Controller {



    private IHomeView ctx;

    private final ProfileResource profileResource;

    private ProfileSubscription profileSubscription;

    private boolean isTeamsReady;

    private boolean isProfileReady;

    private boolean isFriendsReady;

    private boolean isFriendRequestsReady;

    private boolean isMeetingsReady;

    private boolean isChatsReady;

    private boolean isMessagesReady;


    private boolean isBackgroundLoading;

    private ProfileSubscriber profileSubscriber;

    public HomeController(IHomeView ctx) {
        super(ctx);
        this.ctx = ctx;

        profileResource = getResourceComponent().provideProfileResource();

        profileSubscription = getResourceComponent().provideProfileSubscription();

        profileResource.setSubscription(profileSubscription);

        profileSubscriber = new ProfileSubscriber();

        uncheckAll();

        isBackgroundLoading = false;
    }

    @Override
    public void onCreate() {
        loadData();
    }


    public void onDrawerOpened() {
    }

    private void bindProfile() {
        UserProfile profile = profileResource.getProfile();

        String username = profile.username;
        ctx.bindUsernameLabel(username);

        ctx.bindEmailLabel(profile.email);

        ctx.bindAvatar(profile.large_avatar_url, StringUtils.firstLetter(username));

    }

    public void onSettingsMenuItem() {
        ctx.goToSettings();
    }

    public void onProfileMenuItem() {
        ctx.goToProfile();
    }

    public void onHelpMenuItem() {
        ctx.goToHelp();
    }

    public void onFriendsMenuItem() {
        ctx.goToFriends();
    }


    private void loadData() {
        if (!ctx.isLoaded()) {

            profileSubscription.addSubscriber(profileSubscriber);

            if (profileResource.getProfile() == null) {
                loading();
            } else {
                bindProfile();
                backgroundLoading();
            }


            loadAllAndaddSubscriptions();
        }else{
            bindProfile();
        }

    }

    private void backgroundLoading() {
        uncheckAll();
        ctx.showBackgroundLoading();
        isBackgroundLoading = true;
    }


    private boolean isReady() {
        return isProfileReady &&isFriendsReady && isFriendRequestsReady && isTeamsReady && isMeetingsReady && isChatsReady && isMessagesReady;
    }

    private void loading() {
        uncheckAll();
        ctx.showProgressDialog();
    }


    private void  loadAllAndaddSubscriptions() {

        profileSubscription.load();

        final FriendSubscription friendSubscription = getResourceComponent().provideFriendSubscription();
        final FriendRequestSubscription friendRequestSubscription = getResourceComponent().provideFriendRequestSubscription();
        final TeamSubscription teamSubscription = getResourceComponent().provideTeamSubscription();
        final MeetingSubscription meetingSubscription = getResourceComponent().provideMeetingSubscription();
        final ChatSubscription chatSubscription = getResourceComponent().provideChatSubscription();
        final MessageSubscription messageSubscription = getResourceComponent().provideMessageSubscription();

        profileSubscription.addSubscriber(new Subscriber(){
            @Override
            public void notifyChange() {
                profileSubscription.removeSubscriber(this.getID());
                readyProfile();
                friendSubscription.load();
            }
        });

        friendSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                friendSubscription.removeSubscriber(this.getID());
                readyFriends();
                friendRequestSubscription.load();
            }
        });

        friendRequestSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                friendRequestSubscription.removeSubscriber(this.getID());
                readyFriendRequests();
                teamSubscription.load();
            }
        });

        teamSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                teamSubscription.removeSubscriber(this.getID());
                readyTeams();
                meetingSubscription.load();
            }
        });

        meetingSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                meetingSubscription.removeSubscriber(this.getID());
                readyMeetings();
                messageSubscription.load();
            }
        });

        messageSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                messageSubscription.removeSubscriber(this.getID());
                readyMessages();
                chatSubscription.load();
            }
        });

        chatSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                chatSubscription.removeSubscriber(this.getID());
                readyChats();
            }
        });

    }


    private void ready() {
        if (isReady()) {
            if (isBackgroundLoading) {
                isBackgroundLoading = false;
                ctx.hideBackgroundLoading();
            } else {
                ctx.hideProgressDialog();
            }
            uncheckAll();
            ctx.notifyLoaded();
        }
    }


    private void uncheckAll() {
        isProfileReady = isFriendsReady=isFriendRequestsReady=isTeamsReady = isMeetingsReady = isChatsReady = isMessagesReady=false;
    }

    private void readyProfile() {
        isProfileReady = true;
        bindProfile();
        ready();
    }

    private void readyFriends() {
        isFriendsReady = true;
        ready();
    }

    private void readyFriendRequests() {
        isFriendRequestsReady = true;
        ready();
    }

    private void readyTeams() {
        isTeamsReady = true;
        ready();
    }


    private void readyMeetings() {
        isMeetingsReady = true;
        ready();
    }

    private void readyChats() {
        isChatsReady = true;
        ready();
    }

    private void readyMessages() {
        isMessagesReady = true;
        ready();
    }


    @Override
    public void onDestroy() {
        ctx = null;
        profileSubscription.removeSubscriber(profileSubscriber);
    }

    private class ProfileSubscriber extends Subscriber {

        @Override
        public void notifyChange() {
            readyProfile();
        }
    }


}
