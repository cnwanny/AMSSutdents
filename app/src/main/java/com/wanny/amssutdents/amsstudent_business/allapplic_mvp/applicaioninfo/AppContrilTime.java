package com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 15:39
 */

public class AppContrilTime implements Parcelable {
    private String macString;
    private TimeData monday;
    private TimeData tuesday;

    private TimeData wednesday;

    private TimeData thursday;

    private TimeData friday;

    private TimeData saturday;
    private TimeData sunday;

    public String getMacString() {
        return macString;
    }

    public void setMacString(String macString) {
        this.macString = macString;
    }

    public TimeData getMonday() {
        return monday;
    }

    public void setMonday(TimeData monday) {
        this.monday = monday;
    }

    public TimeData getTuesday() {
        return tuesday;
    }

    public void setTuesday(TimeData tuesday) {
        this.tuesday = tuesday;
    }

    public TimeData getWednesday() {
        return wednesday;
    }

    public void setWednesday(TimeData wednesday) {
        this.wednesday = wednesday;
    }

    public TimeData getThursday() {
        return thursday;
    }

    public void setThursday(TimeData thursday) {
        this.thursday = thursday;
    }

    public TimeData getFriday() {
        return friday;
    }

    public void setFriday(TimeData friday) {
        this.friday = friday;
    }

    public TimeData getSaturday() {
        return saturday;
    }

    public void setSaturday(TimeData saturday) {
        this.saturday = saturday;
    }

    public TimeData getSunday() {
        return sunday;
    }

    public void setSunday(TimeData sunday) {
        this.sunday = sunday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.macString);
        dest.writeParcelable(this.monday, flags);
        dest.writeParcelable(this.tuesday, flags);
        dest.writeParcelable(this.wednesday, flags);
        dest.writeParcelable(this.thursday, flags);
        dest.writeParcelable(this.friday, flags);
        dest.writeParcelable(this.saturday, flags);
        dest.writeParcelable(this.sunday, flags);
    }

    public AppContrilTime() {
    }

    protected AppContrilTime(Parcel in) {
        this.macString = in.readString();
        this.monday = in.readParcelable(TimeData.class.getClassLoader());
        this.tuesday = in.readParcelable(TimeData.class.getClassLoader());
        this.wednesday = in.readParcelable(TimeData.class.getClassLoader());
        this.thursday = in.readParcelable(TimeData.class.getClassLoader());
        this.friday = in.readParcelable(TimeData.class.getClassLoader());
        this.saturday = in.readParcelable(TimeData.class.getClassLoader());
        this.sunday = in.readParcelable(TimeData.class.getClassLoader());
    }

    public static final Parcelable.Creator<AppContrilTime> CREATOR = new Parcelable.Creator<AppContrilTime>() {
        @Override
        public AppContrilTime createFromParcel(Parcel source) {
            return new AppContrilTime(source);
        }

        @Override
        public AppContrilTime[] newArray(int size) {
            return new AppContrilTime[size];
        }
    };
}
