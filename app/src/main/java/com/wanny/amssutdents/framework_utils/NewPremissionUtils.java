package com.wanny.amssutdents.framework_utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * 文件名： NewPremissionUtils
 * 功能：
 * 作者： wanny
 * 时间： 15:55 2016/7/4
 */
public class NewPremissionUtils {

    private Activity context;

    public NewPremissionUtils(Activity context) {
        this.context = context;
    }

//    private static NewPremissionUtils instance;
//
//    private Activity context;
//
//    public static NewPremissionUtils getInstance(Activity context) {
//        synchronized (NewPremissionUtils.class) {
//            if (instance == null) {
//                instance = new NewPremissionUtils(context);
//            }
//        }
//        return instance;
//    }

    /**
     * 判断是不是要获取新的权限
     *
     * @return
     */
    public boolean hasNeedReqset() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        } else {
            return false;
        }
    }

    //网络状态的监听
    public boolean requestNetworkPermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 写文件的操作
     *
     * @param requestCode
     * @return
     */

    public boolean requestWriteSDCardPermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 打电话的权限
     *
     * @param requestCode
     * @return
     */
    public boolean requesCallPhonePermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CALL_PHONE},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 读取SD卡的东西
     *
     * @param requestCode
     * @return
     */
    public boolean requestReadSDCardPermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取相机权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestCamerPermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CAMERA},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    //申请定位和拍照权限；
    public boolean requestLocationCamera(int both, int loactCode, int cameraCode) {
        boolean hasLocation = ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        boolean hasCamera = ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
        if (hasCamera && hasLocation) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA}, both);
            return false;
        } else if (hasLocation) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    loactCode);
            return false;
        } else if (hasCamera) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CAMERA},
                    cameraCode);
            return false;
        } else {
            return true;
        }
    }


    /**
     * request SDCard write andr read premission,requset premission of Camera;
     *
     * @param both       the flag of sdCard and camera all request;
     * @param cameraCode the flag of camera request;
     * @param sdcordCoed the flag of sdcord request;
     * @return
     */
    public boolean requestSDCardCameraPremission(int both, int cameraCode, int sdcordCoed) {
        boolean hasLocation = ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        boolean hasSDCard = ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        if (hasSDCard && hasLocation) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, both);
            return false;
        } else if (hasLocation) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    cameraCode);
            return false;
        } else if (hasSDCard) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    sdcordCoed);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取手机联系人
     *
     * @param requestCode
     * @return
     */

    public boolean requestReadConstantPermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取账户的权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestGET_ACCOUNTSPermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.GET_ACCOUNTS},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 位置信息授权
     *
     * @param requestCode
     * @return
     */
    public boolean requestLocationPermissions(int requestCode) {
        if (ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.ACCESS_NETWORK_STATE},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    public boolean requestVoicePermissions(int requestCode) { //    RECORD_AUDIO
        if (ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

}
