package com.zombispormedio.assemble.utils;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.net.ConnectionState;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Xavier Serrano on 11/07/2016.
 */
public class ImageUtils {

    private static class CircleTransform implements Transformation {

        @Override
        public Bitmap transform(@NonNull Bitmap source) {
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

        @NonNull
        @Override
        public String key() {
            return "circle";
        }
    }

    private static void applyRoundLetterImage(@NonNull String letter, @NonNull ImageView imageView) {

        imageView.setImageDrawable(getRoundLetterImage(letter));
    }

    private static void applyLetterImage(@NonNull String letter, @NonNull ImageView imageView) {
        imageView.setImageDrawable(getLetterImage(letter));
    }

    private static Drawable getRoundLetterImage(@NonNull String letter) {
        ColorGenerator generator = ColorGenerator.MATERIAL;

        return TextDrawable.builder()
                .buildRound(letter.toUpperCase(), generator.getColor(letter));
    }

    private static Drawable getLetterImage(@NonNull String letter) {
        ColorGenerator generator = ColorGenerator.MATERIAL;

        return TextDrawable.builder()
                .buildRect(letter.toUpperCase(), generator.getColor(letter));
    }

    public static void applyRoundImage(Context ctx, String url, ImageView imageView) {
        Picasso.with(ctx)
                .load(url)
                .transform(new ImageUtils.CircleTransform())
                .into(imageView);
    }


    public static class ImageBuilder {


        private Context mCtx;

        private ImageView mImageView;


        private String mUrl;


        private String mLetter;

        private boolean isCircle;


        private Drawable mDrawable;


        private ISuccessHandler handler;


        private File mFile;

        private int mDrawableId;

        public ImageBuilder(Context mCtx, ImageView mImageView) {
            this.mCtx = mCtx;
            this.mImageView = mImageView;
            this.mUrl = null;
            this.mLetter = null;
            this.isCircle = false;
            this.mDrawable = null;
            this.handler = null;
            this.mDrawableId = 0;
            this.mFile = null;
        }

        public ImageBuilder() {
        }

        @NonNull
        public ImageBuilder context(Context ctx) {
            this.mCtx = ctx;
            return this;
        }

        @NonNull
        public ImageBuilder imageView(ImageView _imageView) {
            this.mImageView = _imageView;
            return this;
        }

        @NonNull
        public ImageBuilder url(String _url) {
            this.mUrl = _url;
            return this;
        }

        @NonNull
        public ImageBuilder letter(String _letter) {
            this.mLetter = _letter;
            return this;
        }

        @NonNull
        public ImageBuilder file(@NonNull String path) {
            this.mFile = new File(path);
            return this;
        }


        @NonNull
        public ImageBuilder circle(boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        @NonNull
        public ImageBuilder drawable(Drawable _drawable) {
            this.mDrawable = _drawable;
            return this;
        }

        @NonNull
        public ImageBuilder drawableID(int d) {
            this.mDrawableId = d;
            return this;
        }

        @NonNull
        public ImageBuilder handle(ISuccessHandler handler) {
            this.handler = handler;
            return this;
        }

        public void buildDrawableId() {
            RequestCreator config = Picasso.with(mCtx)
                    .load(mDrawableId);

            if (isCircle) {
                config = config.transform(new ImageUtils.CircleTransform());
            }

            config.into(mImageView);
        }

        public void buildFile() {
            RequestCreator config = Picasso.with(mCtx)
                    .load(mFile);

            if (isCircle) {
                config = config.transform(new ImageUtils.CircleTransform());
            }

            config.into(mImageView);
        }


        public void build() {

            if (mCtx != null && mImageView != null) {

                if (mUrl == null) {
                    if (mLetter != null) {
                        buildText();
                    }
                    if (mDrawableId != 0) {
                        buildDrawableId();
                    }

                    if (mFile != null) {
                        buildFile();
                    }

                } else {
                    buildNormal();
                }


            }

            mCtx = null;
        }

        private void buildNormal() {
            Picasso preConfig = Picasso.with(mCtx);

            RequestCreator config = preConfig.load(mUrl);

            if (mDrawable != null) {
                config = config.placeholder(mDrawable);
            }

            if (mLetter != null) {
                Drawable letterDrawable;
                if (isCircle) {
                    letterDrawable = getRoundLetterImage(mLetter);
                } else {
                    letterDrawable = getLetterImage(mLetter);
                }

                config = config.placeholder(letterDrawable);
            }

            if (isCircle) {
                config = config.transform(new ImageUtils.CircleTransform());
            }

            if (config != null) {
                if (ConnectionState.getInstance().isConnected() && handler != null) {

                    config.into(mImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            doHandler();
                        }

                        @Override
                        public void onError() {
                            doHandler();
                        }
                    });
                } else {
                    config.into(mImageView);
                    doHandler();
                }

            } else {
                doHandler();
            }


        }

        private void doHandler() {
            if (handler != null) {
                handler.onSuccess();
            }
        }

        private void buildText() {
            if (isCircle) {
                applyRoundLetterImage(mLetter, mImageView);

            } else {
                applyLetterImage(mLetter, mImageView);
            }

            doHandler();
        }
    }


}
