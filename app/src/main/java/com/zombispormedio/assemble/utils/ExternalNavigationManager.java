package com.zombispormedio.assemble.utils;


import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;


/**
 * Created by Xavier Serrano on 01/08/2016.
 */
public class ExternalNavigationManager {

    public static final String IMAGE_TYPE = "image/*";

    public static final boolean fromKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

    private class INTERNAL_REQUEST_CODE {

        public static final int GALLERY = 101;

        public static final int GALLERY_MORE_THAN_19 = 102;

        public static final int CAMERA = 105;
    }

    public static class REQUEST_CODE {

        public static final int GALLERY = 0;

        public static final int CAMERA = 1;
    }


    private Activity ctx;

    public ExternalNavigationManager(Activity ctx) {
        this.ctx = ctx;
    }

    public void onDestroy() {
        ctx = null;
    }


    public void dispatchGalleryToSelectImage(int title) {

        if (!fromKitKat) {
            String chooserTitle = ctx.getString(title);
            Intent intent = new Intent();
            intent.setType(IMAGE_TYPE);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            ctx.startActivityForResult(Intent.createChooser(intent, chooserTitle), INTERNAL_REQUEST_CODE.GALLERY);

        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType(IMAGE_TYPE);
            ctx.startActivityForResult(intent, INTERNAL_REQUEST_CODE.GALLERY_MORE_THAN_19);
        }
    }

    public void dispatchTakePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(ctx.getPackageManager()) != null) {
            ctx.startActivityForResult(intent, INTERNAL_REQUEST_CODE.CAMERA);
        }

    }


    public static int getType(int requestCode) {
        int result;

        if (requestCode < INTERNAL_REQUEST_CODE.CAMERA) {
            result = REQUEST_CODE.GALLERY;
        } else {
            result = REQUEST_CODE.CAMERA;
        }

        return result;
    }


    public Uri resolveGalleryPath(int requestCode, @NonNull Intent data) {
        Uri originalUri = data.getData();

        if (requestCode == INTERNAL_REQUEST_CODE.GALLERY_MORE_THAN_19) {
            originalUri = data.getData();
            int takeFlags = data.getFlags();
            takeFlags &= (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            ctx.getContentResolver().takePersistableUriPermission(originalUri, takeFlags);

            ctx.grantUriPermission(ctx.getApplicationContext().getPackageName(), originalUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        }

        return originalUri;
    }

    public Uri resolveCameraPath(@NonNull Intent data) {
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("mData");

        return getImageCameraURI(ctx.getApplicationContext(), imageBitmap);
    }

    private Uri getImageCameraURI(@NonNull Context ctx, @NonNull Bitmap img) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(ctx.getContentResolver(), img, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromCameraUri(@NonNull Uri uri) {
        String result = "";
        String[] fields = {MediaStore.Images.Media.DATA};
        Cursor cursor = ctx.getContentResolver().query(uri, fields, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String path = cursor.getString(idx);
            cursor.close();
            if (path != null) {
                result = path;
            }
        }

        return result;
    }

    public String getPath(@NonNull final Uri uri) {
        String result = "";

        if (fromKitKat && DocumentsContract.isDocumentUri(ctx, uri)) {

            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    result = Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                result = getDataColumn(contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;

                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                result = getDataColumn(contentUri, selection, selectionArgs);
            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                result = uri.getLastPathSegment();
            } else {
                getDataColumn(uri, null, null);
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            result = uri.getPath();
        }

        return result;
    }

    private String getDataColumn(@NonNull Uri uri, String selection, String[] selectionArgs) {
        String result = "";
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = ctx.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                result = cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return result;
    }

    public static boolean isExternalStorageDocument(@NonNull Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(@NonNull Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(@NonNull Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(@NonNull Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}
