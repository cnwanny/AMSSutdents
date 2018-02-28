package com.wanny.amssutdents.framework_ui.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/28 16:54
 */

public class ControlNetService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        closeDataLinked();
        return START_STICKY;
    }



    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            closeDataLinked();
        }
    };



    private void closeDataLinked(){
        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
        TelephonyManager teleManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Class[] getArgArray = null;
        Class[] setArgArray = new Class[] {boolean.class};
        Object[] getArgInvoke = null;
        try {
            Method mGetMethod = teleManager.getClass().getMethod("getDataEnabled", getArgArray);
            Method mSetMethod = teleManager.getClass().getMethod("setDataEnabled", setArgArray);
            boolean isOpen = (Boolean) mGetMethod.invoke(teleManager, getArgInvoke);
            if (isOpen) {
                mSetMethod.invoke(teleManager, false);
            } else {
                mSetMethod.invoke(teleManager, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       mHandler.sendEmptyMessageAtTime(0x001,2000);
    }


}
