package com.wanny.amssutdents.amsstudent_business.allapplic_mvp;

import java.util.ArrayList;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/24 9:40
 */

public class EquipmentInfo {

    /// <summary>
    /// 设备ID
    /// </summary>
    public String equipID ;
    /// <summary>
    /// 班级ID
    /// </summary>
    public String classID ;
    /// <summary>
    /// 设备编码
    /// </summary>
    public String equipCode;


    /// <summary>
    /// Mac编码
    /// </summary>
    public String macNo;

    /// <summary>
    /// 学号
    /// </summary>
    public String stuNo ;

    /// <summary>
    /// 学生姓名
    /// </summary>
    public String stuName ;

    /// <summary>
    /// 性别
    /// </summary>
    public int sex ;

    /// <summary>
    /// 状态值（1为在线，0为离线）
    /// </summary>
    public int stateValue;

    /// <summary>
    /// 状态名称(在线/离线)
    /// </summary>
    public  String stateName ;

    public String getEquipID() {
        return equipID;
    }

    public void setEquipID(String equipID) {
        this.equipID = equipID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getEquipCode() {
        return equipCode;
    }

    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }

    public String getMacNo() {
        return macNo;
    }

    public void setMacNo(String macNo) {
        this.macNo = macNo;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStateValue() {
        return stateValue;
    }

    public void setStateValue(int stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getEquipDesc() {
        return equipDesc;
    }

    public void setEquipDesc(String equipDesc) {
        this.equipDesc = equipDesc;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    /// <summary>
    /// 管理员名称
    /// </summary>
    public String leaderName;
    /// <summary>
    /// 设备描述
    /// </summary>
    public String equipDesc ;

    /// <summary>
    /// 管控模式（学校/老师/家长）
    /// </summary>
    public String Model ;
    /// <summary>
    /// 所属班级
    /// </summary>
    public  ClassInfo classInfo ;
//
//    /// <summary>
//    /// 设备上的配置列表
//    /// </summary>
//    public ArrayList<ConfigItem> configList;
}
