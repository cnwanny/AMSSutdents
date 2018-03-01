package com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 15:41
 */

public class TimeEntity implements Parcelable {
  private String start_time;
  private String  end_time;


    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.start_time);
        dest.writeString(this.end_time);
    }

    public TimeEntity() {
    }

    protected TimeEntity(Parcel in) {
        this.start_time = in.readString();
        this.end_time = in.readString();
    }

    public static final Parcelable.Creator<TimeEntity> CREATOR = new Parcelable.Creator<TimeEntity>() {
        @Override
        public TimeEntity createFromParcel(Parcel source) {
            return new TimeEntity(source);
        }

        @Override
        public TimeEntity[] newArray(int size) {
            return new TimeEntity[size];
        }
    };
}
