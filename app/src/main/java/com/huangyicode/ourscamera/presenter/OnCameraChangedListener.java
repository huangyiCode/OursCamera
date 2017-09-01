package com.huangyicode.ourscamera.presenter;

import com.huangyicode.ourscamera.view.OurCameraSurfaceView;

/**
 * Created by hasee on 2017/9/1.
 */

public interface OnCameraChangedListener {

    int CAMERA_FONT=1234;

    int CAMERA_REAR=1235;

    void onCameraChanged(int whichCamera, OurCameraSurfaceView ourCameraSurfaceView);
}
