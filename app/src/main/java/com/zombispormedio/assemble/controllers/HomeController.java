package com.zombispormedio.assemble.controllers;


import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.models.resources.ProfileResource;
import com.zombispormedio.assemble.models.singletons.CurrentUser;
import com.zombispormedio.assemble.models.subscriptions.ChatSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendRequestSubscription;
import com.zombispormedio.assemble.models.subscriptions.FriendSubscription;
import com.zombispormedio.assemble.models.subscriptions.MeetingSubscription;
import com.zombispormedio.assemble.models.subscriptions.ProfileSubscription;
import com.zombispormedio.assemble.models.subscriptions.Subscriber;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.views.IHomeView;


/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class HomeController extends Controller {


    private IHomeView ctx;

    private CurrentUser user;

    private final ProfileResource profileResource;

    private ProfileSubscription profileSubscription;

    private boolean isTeamsReady;

    private boolean isProfileReady;

    private boolean isMeetingsReady;

    private boolean isChatsReady;

    private ProfileSubscriber profileSubscriber;

    public HomeController(IHomeView ctx) {
        super(ctx);
        this.ctx = ctx;

        profileResource = getResourceComponent().provideProfileResource();

        user = CurrentUser.getInstance();

        profileSubscription = user.getProfileSubscription();

        profileSubscriber=new ProfileSubscriber();

        isProfileReady =  isTeamsReady = isMeetingsReady = isChatsReady =false;
    }

    @Override
    public void onCreate() {
        loadData();
    }


    public void onDrawerOpened() {
        setDrawerTitle();
    }

    private void setDrawerTitle() {
        UserProfile profile = profileResource.getProfile();

        String title = "";

        if (!profile.username.isEmpty()) {
            title = profile.username;

        } else {
            if (!profile.email.isEmpty()) {
                title = profile.email;
            }
        }
        if (ctx != null && !title.isEmpty()) {
            ctx.setDrawerTitle(title);
        }
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

        profileSubscription.addSubscriber(profileSubscriber);

        if (profileResource.getProfile() == null) {
            loading();
            addSubscriptions();
        }

        user.loadAll();


    }


    private boolean isReady() {
        return isProfileReady && isTeamsReady && isMeetingsReady && isChatsReady;
    }

    private void loading() {
        uncheckAll();
        ctx.showProgressDialog();
    }


    private void addSubscriptions() {

        final FriendSubscription friendSubscription = user.getFriendSubscription();

        friendSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                friendSubscription.removeSubscriber(this.getID());
            }
        });

        final FriendRequestSubscription friendRequestSubscription = user.getFriendRequestSubscription();

        friendRequestSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                friendRequestSubscription.removeSubscriber(this.getID());
            }
        });

        final TeamSubscription teamSubscription = user.getTeamSubscription();

        teamSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                teamSubscription.removeSubscriber(this.getID());
                readyTeams();
            }
        });

        final MeetingSubscription meetingSubscription = user.getMeetingSubscription();

        meetingSubscription.addSubscriber(new Subscriber() {
            @Override
            public void notifyChange() {
                meetingSubscription.removeSubscriber(this.getID());
                readyMeetings();
            }
        });

        final ChatSubscription chatSubscription = user.getChatSubscription();

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
            ctx.hideProgressDialog();
            uncheckAll();
        }
    }


    private void uncheckAll() {
        isProfileReady = false;
        isTeamsReady = false;
        isMeetingsReady = false;
        isChatsReady = false;
    }

    private void readyProfile() {
        isProfileReady = true;
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
