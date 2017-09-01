package com.huangyicode.ourscamera

import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import butterknife.Unbinder
import com.huangyicode.ourscamera.presenter.OnCameraChangedListener
import com.huangyicode.ourscamera.presenter.OurCameraViewPresenter
import com.huangyicode.ourscamera.view.IShowMainCameraView
import com.huangyicode.ourscamera.view.OurCameraSurfaceView

class MainActivity : AppCompatActivity(), View.OnClickListener, IShowMainCameraView {

    override fun changeCamera(whichCamera: Int) {
        mIvChooseCamera?.isSelected= whichCamera != OnCameraChangedListener.CAMERA_FONT
    }

    private var REQUEST_CAMERA = 10010

    var  mFloatButton: FloatingActionButton?=null

    var  mIvChooseCamera: ImageView?=null

    var mSurfaceCamera:OurCameraSurfaceView?=null


    var mCameraPresenter:OurCameraViewPresenter?=OurCameraViewPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestCameraPermission()
    }

    /**
     *  request camera
     */
    private fun requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.CAMERA),REQUEST_CAMERA)
            }else{
                setContentView(R.layout.activity_main)
                initView()
            }
        }
    }

    /**
     * request camera premission
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
         if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
             setContentView(R.layout.activity_main)
             initView()
         }
    }

    fun initView(){
        mFloatButton=findViewById(R.id.fbtn_shutting)
        mIvChooseCamera=findViewById(R.id.iv_choose_camera)
        mSurfaceCamera=findViewById(R.id.ov_camera)
        mFloatButton?.setOnClickListener(this)
        mIvChooseCamera?.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when(p0!!.id){
             R.id.fbtn_shutting->mCameraPresenter?.onShutting(mSurfaceCamera?.camera)
             R.id.iv_choose_camera->mCameraPresenter?.onCameraChanged(if(mIvChooseCamera?.isSelected!!){OnCameraChangedListener.CAMERA_REAR}else{OnCameraChangedListener.CAMERA_FONT},mSurfaceCamera)
        }
    }


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showInfo(showInfo: String?) {
        Snackbar.make(mIvChooseCamera as View,showInfo as String,Snackbar.LENGTH_SHORT).show()
    }



}
