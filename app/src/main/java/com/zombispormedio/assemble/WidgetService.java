package com.zombispormedio.assemble;

import com.orhanobut.logger.Logger;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    private final String[] items={"lorem", "ipsum", "dolor",
            "sit", "amet", "consectetuer",
            "adipiscing", "elit", "morbi",
            "vel", "ligula", "vitae",
            "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat",
            "placerat", "ante",
            "porttitor", "sodales",
            "pellentesque", "augue",
            "purus"};


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetFactory(getApplicationContext(), intent);
    }

    private class WidgetFactory implements RemoteViewsFactory{
        private Context ctx;
        private int appWidgetId;

        public WidgetFactory(Context applicationContext, Intent intent) {
            this.ctx=applicationContext;
            appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {
           Logger.d(((AssembleApplication)ctx).getResourceComponent().provideChatResource().getAll());
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews row=new RemoteViews(ctx.getPackageName(), R.layout.list_item_chats_widget);

            row.setTextViewText(R.id.text1, items[i]);

            return row;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
