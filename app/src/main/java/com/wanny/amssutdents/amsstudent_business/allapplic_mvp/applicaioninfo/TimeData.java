package com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 15:40
 */

public class TimeData implements Parcelable {
    private ArrayList<TimeEntity> timelist;

    public ArrayList<TimeEntity> getTimelist() {
        return timelist;
    }

    public void setTimelist(ArrayList<TimeEntity> timelist) {
        this.timelist = timelist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.timelist);
    }

    public TimeData() {
    }

    protected TimeData(Parcel in) {
        this.timelist = in.createTypedArrayList(TimeEntity.CREATOR);
    }

    public static final Parcelable.Creator<TimeData> CREATOR = new Parcelable.Creator<TimeData>() {
        @Override
        public TimeData createFromParcel(Parcel source) {
            return new TimeData(source);
        }

        @Override
        public TimeData[] newArray(int size) {
            return new TimeData[size];
        }
    };
}
