package com.zombispormedio.assemble.utils;

import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.CreateChatActivity;
import com.zombispormedio.assemble.activities.CreateMeetingActivity;
import com.zombispormedio.assemble.activities.FirstStepTeamActivity;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.activities.HelpActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.activities.LoginActivity;
import com.zombispormedio.assemble.activities.NewFriendActivity;
import com.zombispormedio.assemble.activities.ProfileActivity;
import com.zombispormedio.assemble.activities.RegisterActivity;
import com.zombispormedio.assemble.activities.SecondStepTeamActivity;
import com.zombispormedio.assemble.activities.SettingsActivity;
import com.zombispormedio.assemble.activities.UpdateBirthdateActivity;
import com.zombispormedio.assemble.activities.UpdateProfileActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.zombispormedio.assemble.utils.AndroidConfig.Keys.CHAT_ID;

/**
 * Created by Xavier Serrano on 25/06/2016.
 */
public final class NavigationManager {


    private Context ctx;

    public static final int ACTIVITY_RESULT_CODE = 5956;

    public static final String ARGS = "args";

    public static final String SIZE = "size";

    public NavigationManager(Context ctx) {
        this.ctx = ctx;

    }

    public void onDestroy() {
        ctx = null;
    }


    private static void goTo(@NonNull Context ctx, Class<?> cls) {
        Intent dst = new Intent(ctx, cls);
        ctx.startActivity(dst);

    }

    private static void goToWithResult(@NonNull Activity ctx, Class<?> cls) {
        Intent dst = new Intent(ctx, cls);
        ctx.startActivityForResult(dst, ACTIVITY_RESULT_CODE);

    }

    private static void goToWithResult(@NonNull Activity ctx, Class<?> cls, @NonNull String... extras) {
        Intent dst = new Intent(ctx, cls);
        dst.putExtra(SIZE, extras.length);

        for (int i = 0; i < extras.length; i++) {
            dst.putExtra(ARGS + i, extras[i]);
        }
        ctx.startActivityForResult(dst, ACTIVITY_RESULT_CODE);

    }


    private static void goWithArg(@NonNull Context ctx, Class<?> cls, int[] extras) {
        Intent dst = new Intent(ctx, cls);
        dst.putExtra(ARGS + 0, extras);
        ctx.startActivity(dst);
    }

    @NonNull
    private static Intent createIntent(Context ctx, Class<?> cls) {
        return new Intent(ctx, cls);
    }

    private static void goByIntent(@NonNull Context ctx, Intent intent) {
        ctx.startActivity(intent);
    }


    public static void Login(@NonNull Context ctx) {
        goTo(ctx, LoginActivity.class);
    }

    public static void Home(@NonNull Context ctx) {
        goTo(ctx, HomeActivity.class);
    }


    public static void Register(@NonNull Context ctx) {
        goTo(ctx, RegisterActivity.class);
    }


    public static void Profile(@NonNull Context ctx) {
        goTo(ctx, ProfileActivity.class);
    }

    public static void Settings(@NonNull Context ctx) {
        goTo(ctx, SettingsActivity.class);
    }

    public static void UpdateProfile(@NonNull Context ctx) {
        goTo(ctx, UpdateProfileActivity.class);
    }

    public static void UpdateBirthdate(@NonNull Activity ctx) {
        goToWithResult(ctx, UpdateBirthdateActivity.class);
    }

    public static void UpdateBirthdate(@NonNull Activity ctx, String... extras) {
        goToWithResult(ctx, UpdateBirthdateActivity.class, extras);
    }

    public static void Friends(@NonNull Context ctx) {
        goTo(ctx, FriendsActivity.class);
    }

    public static void Help(@NonNull Context ctx) {
        goTo(ctx, HelpActivity.class);
    }

    public static void NewFriend(@NonNull Context ctx) {
        goTo(ctx, NewFriendActivity.class);
    }

    public static void CreateChat(@NonNull Context ctx) {
        goTo(ctx, CreateChatActivity.class);
    }

    public static void FirstStepCreateTeam(@NonNull Context ctx) {
        goTo(ctx, FirstStepTeamActivity.class);
    }

    public static void SecondStepCreateTeam(@NonNull Context ctx, int[] friendIndexes) {
        goWithArg(ctx, SecondStepTeamActivity.class, friendIndexes);
    }

    public static void CreateMeeting(@NonNull Context ctx) {
        goTo(ctx, CreateMeetingActivity.class);
    }

    public static void Chat(@NonNull Context ctx, int id) {
        Intent intent = createIntent(ctx, ChatActivity.class);
        intent.putExtra(CHAT_ID, id);
        goByIntent(ctx, intent);
    }

    public void Home() {
        NavigationManager.Home(ctx);
    }

    public void Login() {
        NavigationManager.Login(ctx);
    }

    public void Profile() {
        NavigationManager.Profile(ctx);
    }

    public void Register() {
        NavigationManager.Register(ctx);
    }

    public void Settings() {
        NavigationManager.Settings(ctx);
    }

    public void Friends() {
        NavigationManager.Friends(ctx);
    }

    public void NewFriend() {
        NavigationManager.NewFriend(ctx);
    }

    public void CreateChat() {
        NavigationManager.CreateChat(ctx);
    }

    public void CreateMeeting() {
        NavigationManager.CreateMeeting(ctx);
    }

    public void FirstStepCreateTeam() {
        NavigationManager.FirstStepCreateTeam(ctx);
    }

    public void SecondStepCreateTeam(int[] friendIndexes) {
        NavigationManager.SecondStepCreateTeam(ctx, friendIndexes);
    }

    public void Help() {
        NavigationManager.Help(ctx);
    }

    public static void finishWithResult(@NonNull Activity ctx, @NonNull String... args) {
        Intent intent = new Intent();
        intent.putExtra(SIZE, args.length);

        for (int i = 0; i < args.length; i++) {
            intent.putExtra(ARGS + i, args[i]);
        }
        ctx.setResult(Activity.RESULT_OK, intent);
        ctx.finish();

    }

}
