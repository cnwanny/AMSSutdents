package com.wanny.amssutdents.amsstudent_business.location_save;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 11:09
 */

public class LocationBody implements Parcelable {
    private String Mac;
    private String StudentNumber;
    private double Longitude;
    private double Latitude;
    private String datetime;

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

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Mac);
        dest.writeString(this.StudentNumber);
        dest.writeDouble(this.Longitude);
        dest.writeDouble(this.Latitude);
        dest.writeString(this.datetime);
    }

    public LocationBody() {
    }

    protected LocationBody(Parcel in) {
        this.Mac = in.readString();
        this.StudentNumber = in.readString();
        this.Longitude = in.readDouble();
        this.Latitude = in.readDouble();
        this.datetime = in.readString();
    }

    public static final Parcelable.Creator<LocationBody> CREATOR = new Parcelable.Creator<LocationBody>() {
        @Override
        public LocationBody createFromParcel(Parcel source) {
            return new LocationBody(source);
        }

        @Override
        public LocationBody[] newArray(int size) {
            return new LocationBody[size];
        }
    };
}


