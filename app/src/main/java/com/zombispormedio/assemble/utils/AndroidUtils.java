package com.zombispormedio.assemble.utils;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetItemClickListener;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.views.activities.IBaseProfileView;

import java.util.HashMap;


/**
 * Created by Xavier Serrano on 26/06/2016.
 */
public final class AndroidUtils {


    public static void showAlert(Context ctx, int msg) {
        Toast toast = Toast.makeText(ctx, ctx.getResources().getString(msg), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showAlert(Context ctx, String msg) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static AlertDialog.Builder createConfirmDialog(Context ctx, String msg, String positiveLabel, String negativeLabel,
            DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setMessage(msg)
                .setPositiveButton(positiveLabel, listener)
                .setNegativeButton(negativeLabel, listener);

        return builder;
    }


    public static DialogInterface.OnClickListener createDialogClickListener(final ISuccessHandler listener) {
        return new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        listener.onSuccess();
                        break;


                }
            }
        };
    }

    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }

    public static class InputLayoutHelper {

        private EditText input;

        private TextInputLayout layout;

        public InputLayoutHelper(EditText input, TextInputLayout layout) {
            this.input = input;
            this.layout = layout;
        }

        public EditText getInput() {
            return input;
        }

        public TextInputLayout getLayout() {
            return layout;
        }

        public void hide() {
            input.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
        }

        public void show() {
            input.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
        }

        public String getValue() {
            return input.getText()
                    .toString();
        }

        public void setError(String message) {
            input.setError(message);
        }

        public void preventLeakMemoryOnDestroy() {
            input = null;
            layout = null;
        }
    }

    public static void fillProfile(IBaseProfileView ctx, UserProfile profile) {
        if (ctx != null && profile != null) {

            if (Utils.presenceOf(profile.username)) {
                ctx.setUsername(StringUtils.capitalize(profile.username));
            } else {
                ctx.setUsername("");
            }

            if (Utils.presenceOf(profile.location)) {
                ctx.setLocation(profile.location);
            } else {
                ctx.setLocation("");
            }

            if (Utils.presenceOf(profile.bio)) {
                ctx.setBio(profile.bio);
            } else {
                ctx.setBio("");
            }

            if(Utils.presenceOf(profile.birth_date)){
                String formatedDate=DateUtils.format(ctx.getDateFormat(), profile.birth_date);
                ctx.setBirthDate(formatedDate);
            }else{
                ctx.setBirthDate("");
            }

        }
    }


    public static ListConfiguration createListConfiguration(Context ctx, RecyclerView list) {
        return new ListConfiguration(ctx, list);
    }

    public static class ListConfiguration {

        private Context ctx;

        private RecyclerView list;

        private int orientationType;

        private boolean haveDivider;

        private int dividerOrientation;

        private boolean haveItemAnimation;

        private boolean haveScroll;

        private boolean isGrid;

        private boolean stackFromEnd;

        private int spanCount;

        private RecyclerView.ItemDecoration dividerItemDecoration;

        private RecyclerView.ItemAnimator itemAnimator;


        public ListConfiguration(Context ctx, RecyclerView list) {
            this.list = list;
            this.ctx = ctx;
            orientationType = LinearLayoutManager.VERTICAL;
            haveDivider = false;
            dividerOrientation = LinearLayoutManager.VERTICAL;
            haveItemAnimation = false;
            haveScroll = true;
            dividerItemDecoration = null;
            itemAnimator = null;
            isGrid = false;
            spanCount = 0;
            stackFromEnd = false;
        }

        public ListConfiguration orientation(int orientation) {
            this.orientationType = orientation;
            return this;
        }

        public ListConfiguration divider(boolean haveDivider) {
            this.haveDivider = haveDivider;
            return this;
        }

        public ListConfiguration setDividerOrientation(int dividerOrientation) {
            this.dividerOrientation = dividerOrientation;
            return this;
        }

        public ListConfiguration itemAnimation(boolean haveItemAnimation) {
            this.haveItemAnimation = haveItemAnimation;
            return this;
        }

        public ListConfiguration scrolling(boolean haveScroll) {
            this.haveScroll = haveScroll;
            return this;
        }

        public ListConfiguration grid(boolean g) {
            this.isGrid = g;
            return this;
        }

        public ListConfiguration span(int s) {
            this.spanCount = s;
            return this;
        }

        public ListConfiguration startAtEnd(boolean start) {
            this.stackFromEnd = start;
            return this;
        }


        public ListConfiguration setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
            haveItemAnimation = true;
            this.itemAnimator = itemAnimator;
            return this;
        }

        public ListConfiguration setDividerItemDecoration(RecyclerView.ItemDecoration dividerItemDecoration) {
            haveDivider = true;
            this.dividerItemDecoration = dividerItemDecoration;
            return this;
        }

        private LinearLayoutManager getLinearNoScroll() {
            return new LinearLayoutManager(ctx, orientationType, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }

        private LinearLayoutManager getLinear() {
            return haveScroll ? new LinearLayoutManager(ctx, orientationType, false) : getLinearNoScroll();
        }

        private GridLayoutManager getGridNoScroll() {
            return new GridLayoutManager(ctx, spanCount, orientationType, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }

        private GridLayoutManager getGrid() {
            return haveScroll ? new GridLayoutManager(ctx, spanCount, orientationType, false) : getGridNoScroll();
        }


        public void configure() {

            RecyclerView.LayoutManager layout;

            if (isGrid) {
                layout = getGrid();
            } else {
                layout = getLinear();
                if (stackFromEnd) {
                    ((LinearLayoutManager) layout).setStackFromEnd(true);

                }
            }

            list.setLayoutManager(layout);

            if (haveItemAnimation) {
                if (itemAnimator != null) {
                    list.setItemAnimator(itemAnimator);
                } else {
                    list.setItemAnimator(new DefaultItemAnimator());
                }
            }

            if (haveDivider) {
                if (dividerItemDecoration != null) {
                    list.addItemDecoration(dividerItemDecoration);
                } else {
                    list.addItemDecoration(new DividerItemDecoration(ctx, dividerOrientation));
                }
            }

            ctx = null;


        }
    }

    public static String formatDate(Context ctx, int strID, String date) {
        String format = ctx.getString(strID);
        return DateUtils.format(format, date);
    }


    public static BottomSheetDialog createImageUploaderBottomSheet(Context ctx, BottomSheetItemClickListener listener) {
        return new BottomSheetBuilder(ctx, R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setBackground(R.color.colorWhite)
                .setMenu(R.menu.menu_bottom_sheet)
                .setItemClickListener(listener)
                .createDialog();
    }

    public static HashMap<String, String> convertBundleToStringHashMap(Bundle bundle) {
        HashMap<String, String> hash = new HashMap<>();

        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (value instanceof String) {
                hash.put(key, (String) value);
            }
        }

        return hash;
    }

    public static HashMap<String, String> convertArrayMapToHashMap(ArrayMap<String, String> map) {
        HashMap<String, String> hash = new HashMap<>();

        for (String key : map.keySet()) {
            String value = map.get(key);

            hash.put(key, value);

        }

        return hash;
    }


}
