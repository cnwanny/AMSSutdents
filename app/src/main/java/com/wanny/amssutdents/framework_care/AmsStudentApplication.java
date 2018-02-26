package com.wanny.amssutdents.framework_care;

import android.app.Application;
import android.content.Context;



public class AmsStudentApplication extends Application {


    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);
//        Bugly.init(getApplicationContext(), "0cab56d992", false);
    }

    public static Context getContext(){
        return context;
    }
}
