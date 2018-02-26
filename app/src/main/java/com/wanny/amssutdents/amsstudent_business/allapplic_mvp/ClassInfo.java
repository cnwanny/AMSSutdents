package com.wanny.amssutdents.amsstudent_business.allapplic_mvp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/24 10:06
 */

public class ClassInfo implements Parcelable {
     /// <summary>
        /// 班级ID
        /// </summary>
        public String  classID ;

        /// <summary>
        /// 班级编码
        /// </summary>
        public String  classCode;

        /// <summary>
        /// 班级名称
        /// </summary>
        public  String className ;

        /// <summary>
        /// 所属年级名称：小一、小二、小三、小四、小五、小六；初一、初二、初三；高一、高二、高三
        /// </summary>
        public  String gradeName ;

        /// <summary>
        /// 所属年级区间：小学、初中、高中
        /// </summary>
        public  String gradeLevleName ;
//        /// <summary>
//        /// 班级描述
//        /// </summary>
//        public  String classDesc;

        /// <summary>
        /// 所属学校
        /// </summary>
        public  SchoolInfo schoolInfo ;

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeLevleName() {
        return gradeLevleName;
    }

    public void setGradeLevleName(String gradeLevleName) {
        this.gradeLevleName = gradeLevleName;
    }

    public SchoolInfo getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(SchoolInfo schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

//        /// <summary>
//        /// 设备列表
//        /// </summary>
//        public ArrayList<EquipmentInfo> EquipList ;

//        /// <summary>
//        /// 班级的用户列表
//        /// </summary>
//        public  List<AccountInfo> accountList ;
//
//        /// <summary>
//        /// 班级列表
//        /// </summary>
//        public ArrayList<ClassInfo> classList ;

//        /// <summary>
//        /// 学习平台班级ID
//        /// </summary>
//        public  int classIdentity ;
//
//        /// <summary>
//        /// 学习平台年级ID
//        /// </summary>
//        public  int gradeIdentity ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.classID);
        dest.writeString(this.classCode);
        dest.writeString(this.className);
        dest.writeString(this.gradeName);
        dest.writeString(this.gradeLevleName);
        dest.writeParcelable(this.schoolInfo, flags);
    }

    public ClassInfo() {
    }

    protected ClassInfo(Parcel in) {
        this.classID = in.readString();
        this.classCode = in.readString();
        this.className = in.readString();
        this.gradeName = in.readString();
        this.gradeLevleName = in.readString();
        this.schoolInfo = in.readParcelable(SchoolInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClassInfo> CREATOR = new Parcelable.Creator<ClassInfo>() {
        @Override
        public ClassInfo createFromParcel(Parcel source) {
            return new ClassInfo(source);
        }

        @Override
        public ClassInfo[] newArray(int size) {
            return new ClassInfo[size];
        }
    };
}
