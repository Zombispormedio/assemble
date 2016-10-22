package com.zombispormedio.assemble.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.zombispormedio.assemble.activities.ChatActivity;
import com.zombispormedio.assemble.activities.CreateChatActivity;
import com.zombispormedio.assemble.activities.CreateMeetingActivity;
import com.zombispormedio.assemble.activities.FirstStepTeamActivity;
import com.zombispormedio.assemble.activities.FriendsActivity;
import com.zombispormedio.assemble.activities.HelpActivity;
import com.zombispormedio.assemble.activities.HomeActivity;
import com.zombispormedio.assemble.activities.LoginActivity;
import com.zombispormedio.assemble.activities.MainActivity;
import com.zombispormedio.assemble.activities.NewFriendActivity;
import com.zombispormedio.assemble.activities.ProfileActivity;
import com.zombispormedio.assemble.activities.RegisterActivity;
import com.zombispormedio.assemble.activities.SecondStepTeamActivity;
import com.zombispormedio.assemble.activities.SettingsActivity;
import com.zombispormedio.assemble.activities.UpdateBirthdateActivity;
import com.zombispormedio.assemble.activities.UpdateProfileActivity;

import java.util.HashMap;
import java.util.Map;

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

    public void onDestroy(){
        ctx=null;
    }


    private static void goTo(Context ctx, Class<?> cls) {
        Intent dst = new Intent(ctx, cls);
        ctx.startActivity(dst);

    }

    private static void goToWithResult(Activity ctx, Class<?> cls) {
        Intent dst = new Intent(ctx, cls);
        ctx.startActivityForResult(dst, ACTIVITY_RESULT_CODE);

    }

     private static void goToWithResult(Activity ctx, Class<?> cls, String... extras) {
        Intent dst = new Intent(ctx, cls);
        dst.putExtra(SIZE, extras.length);

        for (int i = 0; i < extras.length; i++) {
            dst.putExtra(ARGS + i, extras[i]);
        }
        ctx.startActivityForResult(dst, ACTIVITY_RESULT_CODE);

    }


    private static void goWithArg(Context ctx, Class<?> cls, int[] extras){
        Intent dst = new Intent(ctx, cls);
        dst.putExtra(ARGS+0, extras);
        ctx.startActivity(dst);
    }

    private static void goWithArg(Context ctx, Class<?> cls, int extras){
        Intent dst = new Intent(ctx, cls);
        dst.putExtra(ARGS+0, extras);
        ctx.startActivity(dst);
    }

    private static void goWithHash(Context ctx, Class<?> cls, HashMap<String, String> hash) {
        Intent dst = new Intent(ctx, cls);
        for(Map.Entry<String, String> entry : hash.entrySet()){
            dst.putExtra(entry.getKey(), entry.getValue());
        }
        ctx.startActivity(dst);
    }


    public static void Login(Context ctx) {
        goTo(ctx, LoginActivity.class);
    }

    public static void Home(Context ctx) {
        goTo(ctx, HomeActivity.class);
    }

    public static void Register(Context ctx) {
        goTo(ctx, RegisterActivity.class);
    }

    public static void Main(Context ctx) {
        goTo(ctx, MainActivity.class);
    }

    public static void Profile(Context ctx) {
        goTo(ctx, ProfileActivity.class);
    }

    public static void Settings(Context ctx) {
        goTo(ctx, SettingsActivity.class);
    }

    public static void UpdateProfile(Context ctx) {
        goTo(ctx, UpdateProfileActivity.class);
    }

    public static void UpdateBirthdate(Activity ctx) {
        goToWithResult(ctx, UpdateBirthdateActivity.class);
    }

    public static void UpdateBirthdate(Activity ctx, String... extras) {
        goToWithResult(ctx, UpdateBirthdateActivity.class, extras);
    }

    public static void Friends(Context ctx) {
        goTo(ctx, FriendsActivity.class);
    }

    public static void Help(Context ctx) {
        goTo(ctx, HelpActivity.class);
    }

    public static void NewFriend(Context ctx) {
        goTo(ctx, NewFriendActivity.class);
    }

    public static void CreateChat(Context ctx){goTo(ctx, CreateChatActivity.class);}

    public static void FirstStepCreateTeam(Context ctx){goTo(ctx, FirstStepTeamActivity.class);}

    public static void SecondStepCreateTeam(Context ctx, int[] friendIndexes){
        goWithArg(ctx, SecondStepTeamActivity.class, friendIndexes);
    }

    public static void CreateMeeting(Context ctx){goTo(ctx, CreateMeetingActivity.class);}

    public static void Chat(Context ctx, int id){
        HashMap<String, String> hash=new HashMap<>();
        hash.put(CHAT_ID, String.valueOf(id));
        goWithHash(ctx, ChatActivity.class, hash);
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

    public void CreateChat(){NavigationManager.CreateChat(ctx);}

    public void CreateMeeting(){NavigationManager.CreateMeeting(ctx);}

    public void FirstStepCreateTeam(){NavigationManager.FirstStepCreateTeam(ctx);}

    public void SecondStepCreateTeam(int[] friendIndexes){
       NavigationManager.SecondStepCreateTeam(ctx, friendIndexes);
    }

    public void Help() {
        NavigationManager.Help(ctx);
    }

    public static void finishWithResult(Activity ctx, String... args) {
        Intent intent = new Intent();
        intent.putExtra(SIZE, args.length);

        for (int i = 0; i < args.length; i++) {
            intent.putExtra(ARGS + i, args[i]);
        }
        ctx.setResult(Activity.RESULT_OK, intent);
        ctx.finish();

    }

}
