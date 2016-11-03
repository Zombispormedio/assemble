package com.zombispormedio.assemble.adapters;


import com.zombispormedio.assemble.models.Sorted;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Xavier Serrano on 26/08/2016.
 */
public abstract class AbstractHolder<T> extends RecyclerView.ViewHolder {

    public AbstractHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, getView());
    }

    public void bind(int position, T itemData) {
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    protected View getView() {
        return itemView;
    }

    @NonNull
    protected String getString(int id) {
        return getContext().getString(id);
    }

    protected Drawable getDrawable(int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    protected Drawable getDrawable(int id, int width, int height) {
        Drawable drawable = getDrawable(id);

        int inWidth = drawable.getIntrinsicWidth();
        int inHeight = drawable.getIntrinsicHeight();

        int dimDiff = Math.abs(inWidth - width) - Math.abs(inHeight - height);

        float scale = dimDiff > 0 ? width / (float) inWidth : height / (float) inHeight;

        Rect bounds = new Rect(0, 0, (int) (scale * inWidth), (int) (scale * inHeight));

        drawable.setBounds(bounds);

        return drawable;
    }

    protected float getDimen(int id) {
        return getContext().getResources().getDimension(id);
    }


    public static class Container<T extends Sorted<T>> {

        protected   T content;

        protected int index;

        protected boolean clicked;

        public Container(T content) {
            this.content = content;
            index=-1;
            clicked = false;
        }

        public Container(T content, int index) {
            this.content = content;
            this.index = index;
            clicked = false;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public boolean isClicked() {
            return clicked;
        }

        public void setClicked(boolean clicked) {
            this.clicked = clicked;
        }



    }

}
