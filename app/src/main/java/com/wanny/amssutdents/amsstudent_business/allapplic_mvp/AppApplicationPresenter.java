package com.wanny.amssutdents.amsstudent_business.allapplic_mvp;

import android.text.TextUtils;

import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContralTimeResult;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContrilTime;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppControlBody;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.ApplicationResult;
import com.wanny.amssutdents.amsstudent_business.login_mvp.LoginEntity;
import com.wanny.amssutdents.framework_care.OrdinalBooleanEntity;
import com.wanny.amssutdents.framework_care.OrdinalResultEntity;
import com.wanny.amssutdents.framework_mvpbasic.BasePresenter;
import com.wanny.amssutdents.framework_net.rxjava.ApiCallback;
import com.wanny.amssutdents.framework_net.rxjava.SubscribCallBack;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/23 13:43
 */

public class AppApplicationPresenter extends BasePresenter<AllAppImpl> {

    public AppApplicationPresenter(AllAppImpl view) {
        attachView(view);
    }

   //获取抢单详情
    public void getEquipInfo(BodyReq body, final String loading) {
        //执行网络请求的回调
        if (!TextUtils.isEmpty(loading)) {
            if (mvpView != null) {
                mvpView.loadIng(loading);
            }
        }
        addSubscription(apiStores.getEquipInfo(body), new SubscribCallBack<>(new ApiCallback<EquipmentResult>() {
            @Override
            public void onSuccess(EquipmentResult model) {
                if (!TextUtils.isEmpty(loading)) {
                    mvpView.hide();
                }
                mvpView.success(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.fail(code, msg);
                mvpView.hide();
            }

            @Override
            public void onCompleted() {

            }
        }));
    }


    //获取抢单详情
    public void getAppList(BodyReq body, final String loading) {
        //执行网络请求的回调
        if (!TextUtils.isEmpty(loading)) {
            if (mvpView != null) {
                mvpView.loadIng(loading);
            }
        }
        addSubscription(apiStores.getAppList(body), new SubscribCallBack<>(new ApiCallback<ApplicationResult>() {
            @Override
            public void onSuccess(ApplicationResult model) {
                if (!TextUtils.isEmpty(loading)) {
                    mvpView.hide();
                }
                mvpView.getAppList(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.fail(code, msg);
                mvpView.hide();
            }

            @Override
            public void onCompleted() {

            }
        }));
    }

    //获取抢单详情
    public void setStartTime(OpenBody mac, final String loading) {
        //执行网络请求的回调
        if (!TextUtils.isEmpty(loading)) {
            if (mvpView != null) {
                mvpView.loadIng(loading);
            }
        }
        addSubscription(apiStores.saveOpenTime(mac), new SubscribCallBack<>(new ApiCallback<OrdinalResultEntity>() {
            @Override
            public void onSuccess(OrdinalResultEntity model) {
                if (!TextUtils.isEmpty(loading)) {
                    mvpView.hide();
                }
                mvpView.saveTime(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.fail(code, msg);
                mvpView.hide();
            }

            @Override
            public void onCompleted() {

            }
        }));
    }




    //获取抢单详情
    public void getAppControl(AppControlBody mac, final String loading) {
        //执行网络请求的回调
        if (!TextUtils.isEmpty(loading)) {
            if (mvpView != null) {
                mvpView.loadIng(loading);
            }
        }
        addSubscription(apiStores.getAppControl(mac), new SubscribCallBack<>(new ApiCallback<OrdinalBooleanEntity>() {
            @Override
            public void onSuccess(OrdinalBooleanEntity model) {
                if (!TextUtils.isEmpty(loading)) {
                    mvpView.hide();
                }
                mvpView.getControlTime(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.fail(code, msg);
                mvpView.hide();
            }

            @Override
            public void onCompleted() {

            }
        }));
    }

}
