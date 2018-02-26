package com.wanny.amssutdents.amsstudent_business.allapplic_mvp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/24 10:14
 */

public class SchoolInfo implements Parcelable {
    /// <summary>
    /// 学校ID
    /// </summary>
    public String  schoolID ;

    /// <summary>
    /// 学校编码
    /// </summary>
    public  String schoolCode ;

    /// <summary>
    /// 学校名称
    /// </summary>
    public  String schoolName ;

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.schoolID);
        dest.writeString(this.schoolCode);
        dest.writeString(this.schoolName);
    }

    public SchoolInfo() {
    }

    protected SchoolInfo(Parcel in) {
        this.schoolID = in.readString();
        this.schoolCode = in.readString();
        this.schoolName = in.readString();
    }

    public static final Parcelable.Creator<SchoolInfo> CREATOR = new Parcelable.Creator<SchoolInfo>() {
        @Override
        public SchoolInfo createFromParcel(Parcel source) {
            return new SchoolInfo(source);
        }

        @Override
        public SchoolInfo[] newArray(int size) {
            return new SchoolInfo[size];
        }
    };
}
