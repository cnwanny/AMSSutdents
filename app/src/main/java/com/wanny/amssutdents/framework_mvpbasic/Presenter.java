package com.wanny.amssutdents.framework_mvpbasic;

/**
 * 文件名： Presenter
 * 功能：
 * 作者： wanny
 * 时间： 15:47 2016/8/5
 */
public interface Presenter<T> {
    //关联View
    void attachView(T view);
   //解除关联
    void detachView();
}
