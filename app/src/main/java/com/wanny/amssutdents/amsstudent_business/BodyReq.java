package com.wanny.amssutdents.amsstudent_business;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/23 11:44
 */

public class BodyReq implements Parcelable {

    private String Mac;
    private String StudentNumber;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Mac);
        dest.writeString(this.StudentNumber);
    }

    public BodyReq() {
    }

    protected BodyReq(Parcel in) {
        this.Mac = in.readString();
        this.StudentNumber = in.readString();
    }

    public static final Parcelable.Creator<BodyReq> CREATOR = new Parcelable.Creator<BodyReq>() {
        @Override
        public BodyReq createFromParcel(Parcel source) {
            return new BodyReq(source);
        }

        @Override
        public BodyReq[] newArray(int size) {
            return new BodyReq[size];
        }
    };
}
