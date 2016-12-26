package com.zombispormedio.assemble;

import com.zombispormedio.assemble.activities.MainActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class AssembleChatWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
            int appWidgetId) {

        int chatNumber=((AssembleApplication)context.getApplicationContext()).getResourceComponent().provideChatResource().getAll().size();
        CharSequence widgetText = String.format(context.getString(R.string.number_of_chats), chatNumber);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.assemble_chat_widget);
        views.setTextViewText(R.id.number_chats, widgetText);

        Intent  serviceIntent=new Intent(context, WidgetService.class);

        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

        views.setRemoteAdapter(R.id.chats, serviceIntent);

        Intent clickIntent=new Intent(context, MainActivity.class);
        PendingIntent clickPI=PendingIntent.getActivity(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setPendingIntentTemplate(R.id.chats, clickPI);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

