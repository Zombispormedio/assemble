package com.zombispormedio.assemble.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Xavier Serrano on 11/07/2016.
 */
public class ImageUtils {

    public static class CircleTransform implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);

            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();

            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();

            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    public static void applyRoundLetterImage(String letter, ImageView imageView){
        ColorGenerator generator = ColorGenerator.MATERIAL;

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter.toUpperCase(),generator.getRandomColor());
        imageView.setImageDrawable(drawable);
    }

    public static void applyRoundImage(Context ctx,String url, ImageView imageView){
        Picasso.with(ctx)
                .load(url)
                .transform(new ImageUtils.CircleTransform())
                .into(imageView);
    }



}
