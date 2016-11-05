package com.zombispormedio.assemble.fragments;


import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.annimon.stream.Stream;
import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.R;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("deprecation")
public class MediaMessageCameraFragment extends Fragment {

    private Camera camera;

    private CameraPreview preview;

    @BindView(R.id.camera_preview)
    FrameLayout cameraPreviewLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_media_message_camera, container, false);

        ButterKnife.bind(this, rootView);

        if (safeOpenCamera(rootView)) {

        }

        return rootView;
    }

    private boolean safeOpenCamera(View rootView) {
        boolean opened = false;

        releaseCameraAndPreview();

        camera = getCameraInstance();

        opened = camera != null;

        if (opened) {
            preview = new CameraPreview(getActivity().getBaseContext(), camera, rootView);
            cameraPreviewLayout.addView(preview);
            preview.startCameraPreview();
        }

        return opened;
    }

    private Camera getCameraInstance() {
        Camera c = null;

        try {
            c = Camera.open();
        } catch (Exception e) {
            Logger.d(e.getMessage());
        }

        return c;
    }

    private void releaseCameraAndPreview() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        if (preview != null) {
            preview.destroyDrawingCache();
            preview.camera = null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseCameraAndPreview();
    }

    private class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

        private Camera camera;

        private SurfaceHolder holder;

        private Context context;

        private Camera.Size previewSize;

        private List<Camera.Size> supportedPreviewSizes;

        private List<String> supportedFlashModes;

        private View cameraView;

        public CameraPreview(Context context, Camera camera, View cameraView) {
            super(context);
            this.camera = camera;
            this.cameraView = cameraView;
            this.context = context;

            setupCamera();

            holder = getHolder();

            holder.addCallback(this);
            holder.setKeepScreenOn(true);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        private void setupCamera() {
            Camera.Parameters parameters = camera.getParameters();
            supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            supportedFlashModes = parameters.getSupportedFlashModes();

            if (supportedFlashModes != null && supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                camera.setParameters(parameters);
            }
            requestLayout();

        }

        public void startCameraPreview() {
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                Logger.d(e.getMessage());
            }
        }


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(holder);
            } catch (IOException e) {
                Logger.d(e.getMessage());
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (holder.getSurface() == null) {
                return;
            }
            try {
                Camera.Parameters parameters = camera.getParameters();

                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

                if (previewSize != null) {
                    parameters.setPreviewSize(previewSize.width, previewSize.height);
                }

                camera.setParameters(parameters);

                camera.startPreview();
            } catch (Exception e) {
                Logger.d(e.getMessage());
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.stopPreview();
            }
        }

      /*  @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            final  int width=resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
            final int height=resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);

            setMeasuredDimension(width, height);

            if(supportedPreviewSizes!=null){
                previewSize=getOptimalPreviewSize(supportedPreviewSizes, width, height);
            }
        }

        private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int width, int height) {


            final double ASPECT_TOLERANCE=0.1;

            double targetRatio=(double) height/width;

            return Stream.of(sizes)
                    .filterNot(size -> size.height!=width)
                    .reduce(null, (memo, size) -> {
                        double ratio = (double) size.width / size.height;
                        if(ratio<=targetRatio+ASPECT_TOLERANCE && ratio>=targetRatio-ASPECT_TOLERANCE){
                            memo=size;
                        }
                        return memo;
                    });


        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            if(changed){
                final int width=right-left;
                final int height=bottom-top;

                int previewWidth=width;
                int previewHeight=height;

                if(previewSize!=null){
                    Display display=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

                    switch (display.getRotation()){
                        case Surface.ROTATION_0:
                            previewWidth=previewSize.height;
                            previewHeight=previewSize.width;
                            camera.setDisplayOrientation(90);
                            break;

                        case Surface.ROTATION_90:
                            previewWidth=previewSize.width;
                            previewHeight=previewSize.height;
                            break;

                        case Surface.ROTATION_180:
                            previewWidth=previewSize.height;
                            previewHeight=previewSize.width;
                            break;

                        case Surface.ROTATION_270:
                            previewWidth=previewSize.width;
                            previewHeight=previewSize.height;
                            camera.setDisplayOrientation(180);
                            break;
                    }
                }

                final int scaledChildHeight=previewHeight*width/previewHeight;

                cameraView.layout(0,height-scaledChildHeight, width, height);
            }
        }*/
    }

}
