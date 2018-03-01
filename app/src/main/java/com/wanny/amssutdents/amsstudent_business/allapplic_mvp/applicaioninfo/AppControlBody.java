package com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 14:56
 */

public class AppControlBody implements Parcelable {

    private String Mac;
    private String StudentNumber;
    private String appId;


    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Mac);
        dest.writeString(this.StudentNumber);
        dest.writeString(this.appId);
    }

    public AppControlBody() {
    }

    protected AppControlBody(Parcel in) {
        this.Mac = in.readString();
        this.StudentNumber = in.readString();
        this.appId = in.readString();
    }

    public static final Parcelable.Creator<AppControlBody> CREATOR = new Parcelable.Creator<AppControlBody>() {
        @Override
        public AppControlBody createFromParcel(Parcel source) {
            return new AppControlBody(source);
        }

        @Override
        public AppControlBody[] newArray(int size) {
            return new AppControlBody[size];
        }
    };
}
