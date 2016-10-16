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
import com.zombispormedio.assemble.models.subscriptions.Subscription;
import com.zombispormedio.assemble.models.subscriptions.TeamSubscription;
import com.zombispormedio.assemble.utils.StringUtils;
import com.zombispormedio.assemble.utils.Utils;
import com.zombispormedio.assemble.views.activities.IHomeView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by Xavier Serrano on 10/07/2016.
 */
public class HomeController extends Controller {


    private IHomeView ctx;

    private final ProfileResource profileResource;

    private boolean isTeamsReady;

    private boolean isProfileReady;

    private boolean isFriendsReady;

    private boolean isFriendRequestsReady;

    private boolean isMeetingsReady;

    private boolean isChatsReady;

    private boolean isMessagesReady;

    private boolean isBackgroundLoading;

    private HashMap<String, Utils.Pair<Subscription, Subscriber>> subscriptions;

    public HomeController(IHomeView ctx) {
        super(ctx);
        this.ctx = ctx;

        profileResource = getResourceComponent().provideProfileResource();

        subscriptions=new HashMap<>();

        uncheckAll();

        isBackgroundLoading = false;
    }

    @Override
    public void onCreate() {
        loadData();
    }


    private void loadData() {
        if (!ctx.isLoaded()) {

            if (profileResource.getProfile() == null) {
                loading();
            } else {
                renderProfile();
                backgroundLoading();
            }

            loadAllAndAddSubscriptions();
        } else {
            renderProfile();
        }

    }


    private void loadAllAndAddSubscriptions() {

        final ProfileSubscription profileSubscription=getResourceComponent().provideProfileSubscription();
        final FriendSubscription friendSubscription = getResourceComponent().provideFriendSubscription();
        final FriendRequestSubscription friendRequestSubscription = getResourceComponent().provideFriendRequestSubscription();
        final TeamSubscription teamSubscription = getResourceComponent().provideTeamSubscription();
        final MeetingSubscription meetingSubscription = getResourceComponent().provideMeetingSubscription();
        final ChatSubscription chatSubscription = getResourceComponent().provideChatSubscription();
        final MessageSubscription messageSubscription = getResourceComponent().provideMessageSubscription();


        addSubscription(profileSubscription, new Subscriber() {
            @Override
            public void notifyChange() {
                removeSubscription(this.getID());
                readyProfile();
                friendSubscription.load();
            }
        });


        addSubscription(friendSubscription, new Subscriber() {
            @Override
            public void notifyChange() {
                removeSubscription(this.getID());
                readyFriends();
                friendRequestSubscription.load();
            }
        });

        addSubscription(friendRequestSubscription, new Subscriber() {
            @Override
            public void notifyChange() {
                removeSubscription(this.getID());
                readyFriendRequests();
                teamSubscription.load();
            }
        });

        addSubscription(teamSubscription, new Subscriber() {
            @Override
            public void notifyChange() {
                removeSubscription(this.getID());
                readyTeams();
                meetingSubscription.load();
            }
        });

        addSubscription(meetingSubscription, new Subscriber() {
            @Override
            public void notifyChange() {
                removeSubscription(this.getID());
                readyMeetings();
                messageSubscription.load();
            }
        });

        addSubscription(meetingSubscription, new Subscriber() {
            @Override
            public void notifyChange() {
                removeSubscription(this.getID());
                readyMessages();
                chatSubscription.load();
            }
        });

        addSubscription(chatSubscription, new Subscriber() {
            @Override
            public void notifyChange() {
                removeSubscription(this.getID());
                readyChats();
            }
        });

        profileSubscription.load();

    }

    private void addSubscription(Subscription subscription, Subscriber subscriber){
        subscription.addSubscriber(subscriber);
        subscriptions.put(subscriber.getID(), new Utils.Pair<>(subscription, subscriber));
    }

    private void removeSubscription(String id){
        Utils.Pair<Subscription, Subscriber> pair=subscriptions.get(id);
        Subscription subscription=pair.first;
        subscription.removeSubscriber(id);
        subscriptions.remove(id);
    }

    private void backgroundLoading() {
        uncheckAll();
        ctx.showBackgroundLoading();
        isBackgroundLoading = true;
    }


    private boolean isReady() {
        return isProfileReady && isFriendsReady && isFriendRequestsReady && isTeamsReady && isMeetingsReady && isChatsReady
                && isMessagesReady;
    }

    private void loading() {
        uncheckAll();
        ctx.showProgressDialog();
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
        isProfileReady = isFriendsReady = isFriendRequestsReady = isTeamsReady = isMeetingsReady = isChatsReady =
                isMessagesReady = false;
    }

    private void readyProfile() {
        isProfileReady = true;
        renderProfile();
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



    private void renderProfile() {
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


    @Override
    public void onDestroy() {
        ctx = null;
        clearSubscriptions();
    }

    private void clearSubscriptions() {
        Set<String> keys=subscriptions.keySet();
        for (String id: keys) {
            removeSubscription(id);
        }
    }

}
