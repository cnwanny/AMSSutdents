package com.wanny.amssutdents.framework_ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wanny.amssutdents.framework_ui.DownLoadActivity;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/2 18:33
 */


public class AppInstallReceiver extends BroadcastReceiver {

    public AppInstallReceiver() {

    }

    private DownLoadActivity downLoadActivity;
    public AppInstallReceiver(DownLoadActivity downLoadActivity){
        this.downLoadActivity = downLoadActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(downLoadActivity != null){
            downLoadActivity.updataList();
        }
    }
}

