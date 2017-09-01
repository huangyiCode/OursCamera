package com.huangyicode.ourscamera.view;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by hasee on 2017/8/31.
 *  Render Camera
 */
public class OurCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Context mContext;

    private Camera mCamera;

    private SurfaceHolder mHolder;

    public OurCameraSurfaceView(Context context) {
        this(context,null);
    }

    public OurCameraSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OurCameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
    }

    /**
     * open camera View
     */
    private void  initView(){
        /**
         * bind SurfaceHolder
         */
        mHolder= getHolder();
        mHolder.addCallback(this);

        mCamera= Camera.open();
        if(mCamera==null){
            Snackbar.make(this,"this device can't support Camera",Snackbar.LENGTH_LONG).show();
            return;
        }
    }




    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            mCamera.setPreviewDisplay(mHolder);
            setCameraDisplayOrientation((Activity) mContext,0,mCamera);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        mCamera.stopPreview();

    }

    /**
     *  set  preview orientation
     * @param activity
     * @param cameraId
     * @param camera
     */
    private static void setCameraDisplayOrientation (Activity activity, int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo (cameraId , info);
        int rotation = activity.getWindowManager ().getDefaultDisplay ().getRotation ();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;   // compensate the mirror
        } else {
            // back-facing
            result = ( info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation (result);
    }
}
