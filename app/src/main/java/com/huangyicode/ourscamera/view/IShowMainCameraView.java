package com.huangyicode.ourscamera.view;

/**
 * Created by hasee on 2017/8/31.
 *
 *  main activity camera view action
 *   存放所有关于界面变化的动作
 */
public interface IShowMainCameraView {

    /**
     * 展示信息
     */
    void showInfo(String showInfo);

    /**
     * 展示加载框
     */
    void showLoading();

    /**
     * 改变摄像头位置图标
     */
    void changeCamera(int whichCamera);


}
