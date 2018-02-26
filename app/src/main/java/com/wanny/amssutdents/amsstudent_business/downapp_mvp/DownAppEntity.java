package com.wanny.amssutdents.amsstudent_business.downapp_mvp;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/24 17:27
 */

public class DownAppEntity {

    /// <summary>
    /// 应用ID 此主键用于当前用于上传App的日志进行匹配，下载时需使用appId进行下载
    /// </summary>
    public String  appID ;
    /// <summary>
    /// 应用编码
    /// </summary>
    public String appCode ;

    /// <summary>
    /// 应用名称
    /// </summary>
    public  String appName ;

    /// <summary>
    /// 应用包名
    /// </summary>
    public String appPackageName ;

    /// <summary>
    /// SDK名称
    /// </summary>
    public String SDKName ;

    /// <summary>
    /// 是否系统软件
    /// </summary>
    public boolean isSystemSoft ;
    /// <summary>
    /// 版本号 此方法用于对App进行更新时使用
    /// </summary>
    public String appVersionNo ;

    /// <summary>
    /// 版本名称
    /// </summary>
    public String appVersionName;

    /// <summary>
    /// 应用文件路径
    /// </summary>
    public String appPath ;

    /// <summary>
    /// 应用文件名称
    /// </summary>
    public String appFileName ;

    /// <summary>
    /// 上传人
    /// </summary>
    public  String uploador ;

    /// <summary>
    /// 上传时间
    /// </summary>
    public  String uploadTime;

    /// <summary>
    /// 是否强制更新
    /// </summary>
    public boolean isAutoUpdate ;
}
