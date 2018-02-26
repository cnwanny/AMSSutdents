package com.wanny.amssutdents.framework_mvpbasic;

/**
 * 文件名： BaseViewImp
 * 功能：
 * 作者： wanny
 * 时间： 9:29 2017/3/9
 */
public interface BaseViewImp<T> {

    void showDialog(T t);
    void dismissDialog();
}
