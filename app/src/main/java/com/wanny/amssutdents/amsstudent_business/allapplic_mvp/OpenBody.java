package com.wanny.amssutdents.amsstudent_business.allapplic_mvp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 11:48
 */

public class OpenBody implements Parcelable {

    private String mac;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mac);
    }

    public OpenBody() {
    }

    protected OpenBody(Parcel in) {
        this.mac = in.readString();
    }

    public static final Parcelable.Creator<OpenBody> CREATOR = new Parcelable.Creator<OpenBody>() {
        @Override
        public OpenBody createFromParcel(Parcel source) {
            return new OpenBody(source);
        }

        @Override
        public OpenBody[] newArray(int size) {
            return new OpenBody[size];
        }
    };
}
