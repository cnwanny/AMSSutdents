package com.wanny.amssutdents.amsstudent_business;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/26 10:27
 */

public class ApplicationInfo implements Parcelable {
    /// <summary>
    /// 应用ID 此主键用于当前用于上传App的日志进行匹配，下载时需使用appId进行下载
    /// </summary>
    public String  appID ;

    /// <summary>
    /// 应用编码
    /// </summary>
    public String appCode ;
    /// <summary>
    /// 应用名称
    /// </summary>
    public  String appName ;
    /// <summary>
    /// 应用包名
    /// </summary>
    public String appPackageName ;

    /// <summary>
    /// SDK名称
    /// </summary>
    public String SDKName ;

    /// <summary>
    /// 是否系统软件
    /// </summary>
    public int isSystemSoft ;
    /// <summary>
    /// 版本号 此方法用于对App进行更新时使用
    /// </summary>
    public String appVersionNo;

    /// <summary>
    /// 版本名称
    /// </summary>
    public String appVersionName;

    /// <summary>
    /// 应用文件路径
    /// </summary>
    public String appPath ;

    /// <summary>
    /// 应用文件名称
    /// </summary>
    public String appFileName ;

    /// <summary>
    /// 上传人
    /// </summary>
    public  String uploador;

    /// <summary>
    /// 上传时间
    /// </summary>
    public String  uploadTime ;

    public String  appIco ;


    public String getAppIco() {
        return appIco;
    }

    public void setAppIco(String appIco) {
        this.appIco = appIco;
    }

    /// <summary>
    /// 是否强制更新
    /// </summary>
    public int isAutoUpdate ;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public String getSDKName() {
        return SDKName;
    }

    public void setSDKName(String SDKName) {
        this.SDKName = SDKName;
    }

    public int getIsSystemSoft() {
        return isSystemSoft;
    }

    public void setIsSystemSoft(int isSystemSoft) {
        this.isSystemSoft = isSystemSoft;
    }

    public String getAppVersionNo() {
        return appVersionNo;
    }

    public void setAppVersionNo(String appVersionNo) {
        this.appVersionNo = appVersionNo;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getAppFileName() {
        return appFileName;
    }

    public void setAppFileName(String appFileName) {
        this.appFileName = appFileName;
    }

    public String getUploador() {
        return uploador;
    }

    public void setUploador(String uploador) {
        this.uploador = uploador;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }


    public ApplicationInfo() {
    }

    public int getIsAutoUpdate() {
        return isAutoUpdate;
    }

    public void setIsAutoUpdate(int isAutoUpdate) {
        this.isAutoUpdate = isAutoUpdate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appID);
        dest.writeString(this.appCode);
        dest.writeString(this.appName);
        dest.writeString(this.appPackageName);
        dest.writeString(this.SDKName);
        dest.writeInt(this.isSystemSoft);
        dest.writeString(this.appVersionNo);
        dest.writeString(this.appVersionName);
        dest.writeString(this.appPath);
        dest.writeString(this.appFileName);
        dest.writeString(this.uploador);
        dest.writeString(this.uploadTime);
        dest.writeString(this.appIco);
        dest.writeInt(this.isAutoUpdate);
    }

    protected ApplicationInfo(Parcel in) {
        this.appID = in.readString();
        this.appCode = in.readString();
        this.appName = in.readString();
        this.appPackageName = in.readString();
        this.SDKName = in.readString();
        this.isSystemSoft = in.readInt();
        this.appVersionNo = in.readString();
        this.appVersionName = in.readString();
        this.appPath = in.readString();
        this.appFileName = in.readString();
        this.uploador = in.readString();
        this.uploadTime = in.readString();
        this.appIco = in.readString();
        this.isAutoUpdate = in.readInt();
    }

    public static final Creator<ApplicationInfo> CREATOR = new Creator<ApplicationInfo>() {
        @Override
        public ApplicationInfo createFromParcel(Parcel source) {
            return new ApplicationInfo(source);
        }

        @Override
        public ApplicationInfo[] newArray(int size) {
            return new ApplicationInfo[size];
        }
    };
}
