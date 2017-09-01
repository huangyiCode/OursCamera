package com.huangyicode.ourscamera

import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var REQUEST_CAMERA = 10010;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestCameraPermission();
    }

    /**
     *  request camera
     */
    private fun requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.CAMERA),REQUEST_CAMERA);
            }else{
                setContentView(R.layout.activity_main);
            }
        };
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
         if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
             setContentView(R.layout.activity_main);
    }

}
