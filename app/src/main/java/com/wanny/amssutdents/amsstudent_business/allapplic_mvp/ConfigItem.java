package com.wanny.amssutdents.amsstudent_business.allapplic_mvp;

import java.util.ArrayList;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/24 10:09
 */

public class ConfigItem {
    /// <summary>
    /// 配置项ID
    /// </summary>
    public String itemID;

    /// <summary>
    /// 配置项名称
    /// </summary>
    public String itemName;

    /// <summary>
    /// 配置项描述
    /// </summary>
    public String itemDesc;

    /// <summary>
    /// 应用类型（0：班级；1：设备）
    /// </summary>
    public int objType;

    /// <summary>
    /// 应用班级IDs（,号隔开）
    /// </summary>
    public String classIds;
    /// <summary>
    /// 应用设备IDs（,号隔开）
    /// </summary>
    public String equipIds;
    /// <summary>
    /// 配置班级
    /// </summary>
    //public virtual ClassInfo classInfo { get; set; }

//    /// <summary>
//    /// 所属配置类别
//    /// </summary>
//    public  ConfigType configType ;
//
//    /// <summary>
//    /// 配置清单值列表
//    /// </summary>
//    public ArrayList<ConfigValue> valueList;

    /// <summary>
    /// 分页列表
    /// </summary>
    public ArrayList<ConfigItem> itemList;
}
