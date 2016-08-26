package com.zombispormedio.assemble.utils;

import com.orhanobut.logger.Logger;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Xavier Serrano on 25/08/2016.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable _divider;

    private int _orientation;

    public DividerItemDecoration(Context ctx, int orientation) {
        final TypedArray a = ctx.obtainStyledAttributes(ATTRS);
        _divider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);

    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            Logger.d("invalid orientation");
            _orientation = HORIZONTAL_LIST;
        } else {
            _orientation = orientation;
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (_orientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + _divider.getIntrinsicHeight();
            _divider.setBounds(left, top, right, bottom);
            _divider.draw(c);

        }

    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + _divider.getIntrinsicHeight();
            _divider.setBounds(left, top, right, bottom);
            _divider.draw(c);

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (_orientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, _divider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, _divider.getIntrinsicWidth(), 0);
        }
    }
}
