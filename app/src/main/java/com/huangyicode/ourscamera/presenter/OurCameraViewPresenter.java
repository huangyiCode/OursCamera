package com.huangyicode.ourscamera.presenter;

import android.hardware.Camera;

import com.huangyicode.ourscamera.view.IShowMainCameraView;
import com.huangyicode.ourscamera.view.OurCameraSurfaceView;

/**
 * Created by hasee on 2017/8/31.
 */

public class OurCameraViewPresenter implements OnCameraChangedListener,OnShuttingListener, Camera.ShutterCallback, Camera.PictureCallback {

    IShowMainCameraView mIShowMainCameraView;

    public OurCameraViewPresenter(IShowMainCameraView iShowMainCameraView){
      this.mIShowMainCameraView=iShowMainCameraView;
    }



    @Override
    public void onShutting(Camera camera) {
        camera.takePicture(this,this,this);
    }


    @Override
    public void onCameraChanged(int whichCamera,OurCameraSurfaceView ourCameraSurfaceView) {
        ourCameraSurfaceView.getCamera().stopPreview();
        ourCameraSurfaceView.getCamera().release();

        Camera camera;
       if(whichCamera==OnCameraChangedListener.CAMERA_REAR){ //默认
           camera= Camera.open(1);
           mIShowMainCameraView.changeCamera(OnCameraChangedListener.CAMERA_FONT);
       }else{
           camera= Camera.open(0);
           mIShowMainCameraView.changeCamera(OnCameraChangedListener.CAMERA_REAR);
       }

       if(camera==null){
           mIShowMainCameraView.showInfo("不支持切换");
       }else{
           ourCameraSurfaceView.setCamera(camera);
       }
    }




    @Override
    public void onShutter() {
        mIShowMainCameraView.showInfo("onShutter");
    }



    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        mIShowMainCameraView.showInfo("onShutterFinish");
        camera.startPreview();
    }
}
