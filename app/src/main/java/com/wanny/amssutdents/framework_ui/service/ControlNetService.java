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
import android.text.TextUtils;

import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContralTimeResult;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContrilTime;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.TimeData;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.TimeEntity;
import com.wanny.amssutdents.amsstudent_business.netcontrol_mvp.NetContralPresenter;
import com.wanny.amssutdents.amsstudent_business.netcontrol_mvp.NetControlImpl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        netContralPresenter = new NetContralPresenter(netControl);
    }
    private AppContrilTime time;

    private NetControlImpl netControl = new NetControlImpl() {
        @Override
        public void success(AppContralTimeResult appContralTimeResult) {
            if(appContralTimeResult.isStatus()){
                time = appContralTimeResult.getData();
            }
        }

        @Override
        public void fail(int code, String errorMessage) {

        }

        @Override
        public void loadIng(String title) {

        }

        @Override
        public void hide() {

        }
    };


    private NetContralPresenter netContralPresenter;
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

    public static String[] WEEK = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
    public static final int WEEKDAYS = 7;
    private void regTime(){
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        String s = DateToWeek(date);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        if(s.equals("星期一")){
           if(time.getMonday() != null){
              TimeData timeDatas = time.getMonday();
              if(timeDatas.getTimelist().size() > 0){
                 for(TimeEntity entity: timeDatas.getTimelist()){
                     if(!TextUtils.isEmpty(entity.getEnd_time())){

                     }else{

                     }
                 }
              }
           }
        }else if(s.equals("星期二")){

        }else if(s.equals("星期三")){

        }else if(s.equals("星期四")){

        }else if(s.equals("星期五")){

        }else if(s.equals("星期六")){

        }else if(s.equals("星期日")){

        }

    }
    public static String DateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }

        return WEEK[dayIndex - 1];
    }
    private void closeDataLinked(){
        if(time != null){
            return;
        }
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
