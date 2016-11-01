package com.zombispormedio.assemble.utils;

import com.annimon.stream.Stream;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetItemClickListener;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.views.activities.IBaseProfileView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.HashMap;


/**
 * Created by Xavier Serrano on 26/06/2016.
 */
public final class AndroidUtils {


    public static void showAlert(@NonNull Context ctx, int msg) {
        Toast toast = Toast.makeText(ctx, ctx.getResources().getString(msg), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showAlert(Context ctx, String msg) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @NonNull
    public static AlertDialog.Builder createConfirmDialog(Context ctx, String msg, String positiveLabel, String negativeLabel,
            DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setMessage(msg)
                .setPositiveButton(positiveLabel, listener)
                .setNegativeButton(negativeLabel, listener);

        return builder;
    }


    public static DialogInterface.OnClickListener createDialogClickListener(@NonNull final ISuccessHandler listener) {
        return (dialogInterface, i) -> {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    listener.onSuccess();
                    break;


            }
        };
    }

    public static void runOnUiThread(@NonNull final Runnable runnable) {
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

        @NonNull
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

    public static void fillProfile( IBaseProfileView ctx,  UserProfile profile) {
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

            ctx.setBirthDate(profile.getBirth(), "");

        }
    }


    @NonNull
    public static ListConfiguration createListConfiguration(Context ctx, RecyclerView list) {
        return new ListConfiguration(ctx, list);
    }

    public static class ListConfiguration {


        private Context ctx;

        private final RecyclerView list;

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

        @NonNull
        public ListConfiguration orientation(int orientation) {
            this.orientationType = orientation;
            return this;
        }

        @NonNull
        public ListConfiguration divider(boolean haveDivider) {
            this.haveDivider = haveDivider;
            return this;
        }

        @NonNull
        public ListConfiguration setDividerOrientation(int dividerOrientation) {
            this.dividerOrientation = dividerOrientation;
            return this;
        }

        @NonNull
        public ListConfiguration itemAnimation(boolean haveItemAnimation) {
            this.haveItemAnimation = haveItemAnimation;
            return this;
        }

        @NonNull
        public ListConfiguration scrolling(boolean haveScroll) {
            this.haveScroll = haveScroll;
            return this;
        }

        @NonNull
        public ListConfiguration grid(boolean g) {
            this.isGrid = g;
            return this;
        }

        @NonNull
        public ListConfiguration span(int s) {
            this.spanCount = s;
            return this;
        }

        @NonNull
        public ListConfiguration startAtEnd(boolean start) {
            this.stackFromEnd = start;
            return this;
        }


        @NonNull
        public ListConfiguration setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
            haveItemAnimation = true;
            this.itemAnimator = itemAnimator;
            return this;
        }

        @NonNull
        public ListConfiguration setDividerItemDecoration(RecyclerView.ItemDecoration dividerItemDecoration) {
            haveDivider = true;
            this.dividerItemDecoration = dividerItemDecoration;
            return this;
        }

        @NonNull
        private LinearLayoutManager getLinearNoScroll() {
            return new LinearLayoutManager(ctx, orientationType, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }

        @NonNull
        private LinearLayoutManager getLinear() {
            return haveScroll ? new LinearLayoutManager(ctx, orientationType, false) : getLinearNoScroll();
        }

        @NonNull
        private GridLayoutManager getGridNoScroll() {
            return new GridLayoutManager(ctx, spanCount, orientationType, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }

        @NonNull
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


    public static BottomSheetDialog createImageUploaderBottomSheet(Context ctx, BottomSheetItemClickListener listener) {
        return new BottomSheetBuilder(ctx, R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setBackground(R.color.colorWhite)
                .setMenu(R.menu.menu_bottom_sheet)
                .setItemClickListener(listener)
                .createDialog();
    }

    public static HashMap<String, String> convertBundleToStringHashMap(@NonNull Bundle bundle) {

        return Stream.of(bundle.keySet())
                .filter(key -> bundle.get(key) instanceof String)
                .reduce(new HashMap<String, String>(), (memo, key) -> {
                    memo.put(key, (String) bundle.get(key));
                    return memo;
                });

    }

    public static HashMap<String, String> convertArrayMapToHashMap(@NonNull ArrayMap<String, String> map) {

        return Stream.of(map.keySet())
                .reduce(new HashMap<String, String>(), (memo, key) -> {
                    memo.put(key, map.get(key));

                    return memo;
                });

    }


}
