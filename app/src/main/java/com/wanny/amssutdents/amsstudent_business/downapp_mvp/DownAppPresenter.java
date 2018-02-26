package com.wanny.amssutdents.amsstudent_business.downapp_mvp;

import android.text.TextUtils;

import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.ApplicationResult;
import com.wanny.amssutdents.framework_mvpbasic.BasePresenter;
import com.wanny.amssutdents.framework_net.rxjava.ApiCallback;
import com.wanny.amssutdents.framework_net.rxjava.SubscribCallBack;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/24 17:27
 */

public class DownAppPresenter extends BasePresenter<DownAppImpl> {

    public DownAppPresenter(DownAppImpl view){
        attachView(view);
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

}
