package com.zombispormedio.assemble.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.net.ConnectionState;

import java.io.File;

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

    public static void applyRoundLetterImage(String letter, ImageView imageView) {

        imageView.setImageDrawable(getRoundLetterImage(letter));
    }

    public static void applyLetterImage(String letter, ImageView imageView) {
        imageView.setImageDrawable(getLetterImage(letter));
    }

    public static Drawable getRoundLetterImage(String letter) {
        ColorGenerator generator = ColorGenerator.MATERIAL;

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter.toUpperCase(), generator.getColor(letter));
        return drawable;
    }

    public static Drawable getLetterImage(String letter) {
        ColorGenerator generator = ColorGenerator.MATERIAL;

        TextDrawable drawable = TextDrawable.builder()
                .buildRect(letter.toUpperCase(), generator.getColor(letter));
        return drawable;
    }

    public static void applyRoundImage(Context ctx, String url, ImageView imageView) {
        Picasso.with(ctx)
                .load(url)
                .transform(new ImageUtils.CircleTransform())
                .into(imageView);
    }


    public static class ImageBuilder {

        private Context _ctx;

        private ImageView _imageView;

        private String _url;

        private String _letter;

        private boolean isCircle;

        private Drawable _drawable;

        private ISuccessHandler handler;

        private File _file;

        private int _drawableId;

        public ImageBuilder(Context _ctx, ImageView _imageView) {
            this._ctx = _ctx;
            this._imageView = _imageView;
            this._url = null;
            this._letter = null;
            this.isCircle = false;
            this._drawable = null;
            this.handler = null;
            this._drawableId = 0;
            this._file = null;
        }

        public ImageBuilder() {
        }

        public ImageBuilder context(Context ctx) {
            this._ctx = ctx;
            return this;
        }

        public ImageBuilder imageView(ImageView _imageView) {
            this._imageView = _imageView;
            return this;
        }

        public ImageBuilder url(String _url) {
            this._url = _url;
            return this;
        }

        public ImageBuilder letter(String _letter) {
            this._letter = _letter;
            return this;
        }

        public ImageBuilder file(String path) {
            this._file = new File(path);
            return this;
        }


        public ImageBuilder circle(boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        public ImageBuilder drawable(Drawable _drawable) {
            this._drawable = _drawable;
            return this;
        }

        public ImageBuilder drawableID(int d) {
            this._drawableId = d;
            return this;
        }

        public ImageBuilder handle(ISuccessHandler handler) {
            this.handler = handler;
            return this;
        }

        public void buildDrawableId() {
            RequestCreator config = Picasso.with(_ctx)
                    .load(_drawableId);

            if (isCircle) {
                config = config.transform(new ImageUtils.CircleTransform());
            }

            config.into(_imageView);
        }

        public void buildFile() {
            RequestCreator config = Picasso.with(_ctx)
                    .load(_file);

            if (isCircle) {
                config = config.transform(new ImageUtils.CircleTransform());
            }

            config.into(_imageView);
        }


        public void build() {

            if (_ctx != null && _imageView != null) {

                if (_url == null) {
                    if (_letter != null) {
                        buildText();
                    }
                    if (_drawableId != 0) {
                        buildDrawableId();
                    }

                    if (_file != null) {
                        buildFile();
                    }

                } else {
                    buildNormal();
                }


            }

            _ctx=null;
        }

        private void buildNormal() {
            Picasso preConfig = Picasso.with(_ctx);

            RequestCreator config = preConfig.load(_url);

            if (_drawable != null) {
                config = config.placeholder(_drawable);
            }

            if (_letter != null) {
                Drawable letterDrawable;
                if (isCircle) {
                    letterDrawable = getRoundLetterImage(_letter);
                } else {
                    letterDrawable = getLetterImage(_letter);
                }

                config = config.placeholder(letterDrawable);
            }

            if (isCircle) {
                config = config.transform(new ImageUtils.CircleTransform());
            }

            if (config != null) {
                if (ConnectionState.getInstance().isConnected() && handler != null) {

                    config.into(_imageView, new Callback() {
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
                    config.into(_imageView);
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
                applyRoundLetterImage(_letter, _imageView);

            } else {
                applyLetterImage(_letter, _imageView);
            }

            doHandler();
        }
    }


}
