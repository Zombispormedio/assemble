package com.zombispormedio.assemble.activities;

import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cocosw.bottomsheet.BottomSheet;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.controllers.ProfileController;
import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.views.IProfileView;

import java.io.ByteArrayOutputStream;

public class ProfileActivity extends BaseActivity implements IProfileView{

    public static final String IMAGE_TYPE = "image/*";
    private static final int SELECT_SINGLE_PICTURE = 101;
    private static final int REQUEST_TAKE_PHOTO=102;
    private ProfileController ctrl;

    private ImageView imageProfile;
    private FloatingActionButton imageFab;
    private ProgressBar imageProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar bar = (Toolbar) findViewById(R.id.profile_bar);
        setSupportActionBar(bar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ctrl=new ProfileController(this);


        imageFab = (FloatingActionButton) findViewById(R.id.image_upload_button);
        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        imageProgressBar = (ProgressBar) findViewById(R.id.progress_image);

        imageFab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openImageBottomSheet();

            }
        });

    }

    public void hideImageForm(){
        imageFab.setVisibility(View.GONE);
    }

    public void showImageForm(){
        imageFab.setVisibility(View.VISIBLE);
    }

    public void hideProgressImage(){
        imageProgressBar.setVisibility(View.GONE);
    }

    public void showProgressImage(){
        imageProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ctrl.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ctrl.onDestroy();
    }

    @Override
    public void setProfileImage(String url, final IPromiseHandler handler) {
        Picasso.with(this)
                .load(url)
                .transform(new ImageUtils.CircleTransform())
                .into(imageProfile, new Callback() {
                    @Override
                    public void onSuccess() {
                        handler.onSuccess();
                    }

                    @Override
                    public void onError() {

                    }
                });

    }



    public void loadDefaultImage( final IPromiseHandler handler) {
        Picasso.with(this)
                .load(R.drawable.profile_image_square)
                .transform(new ImageUtils.CircleTransform())
                .into(imageProfile, new Callback() {
                    @Override
                    public void onSuccess() {
                        handler.onSuccess();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }




    public void openImageBottomSheet(){
        new BottomSheet.Builder(this, R.style.BottomSheet_Dialog)
                .title(R.string.image_profile)
                .sheet(R.menu.menu_bottom_sheet)
                .listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case R.id.gallery:
                                dispatchGalleryToSelectImage();
                                break;
                            case R.id.camera:
                                dispatchTakePicture();
                                break;
                        }

                    }
                }).show();

    }

    private void dispatchGalleryToSelectImage(){
        String chooserTitle=getString(R.string.select_picture);
        Intent intent= new Intent();
        intent.setType(IMAGE_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, chooserTitle), SELECT_SINGLE_PICTURE);
    }

    private void dispatchTakePicture(){
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if(resultCode ==RESULT_OK){
          switch (requestCode){

              case SELECT_SINGLE_PICTURE:{
                  Uri uri = data.getData();
                  Logger.d(uri);
                  break;
              }

              case REQUEST_TAKE_PHOTO:{
                  Bundle extras = data.getExtras();
                  Bitmap imageBitmap = (Bitmap) extras.get("data");
                  Uri tempUri=getImageURI(getApplicationContext(), imageBitmap);
                 Logger.d(getRealPathFromCameraUri(tempUri));
                  break;
              }


          }


      }
    }

    private Uri getImageURI(Context ctx, Bitmap img){
        ByteArrayOutputStream bytes=new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(ctx.getContentResolver(), img, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromCameraUri(Uri uri){
        String[] fields = {MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION};
        Cursor cursor=getContentResolver().query(uri, fields, null, null, null);

        if (cursor == null)
            return "";

        cursor.moveToFirst();
        int idx=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
