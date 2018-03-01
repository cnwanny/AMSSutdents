package com.wanny.amssutdents.amsstudent_business.netcontrol_mvp;

import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContralTimeResult;
import com.wanny.amssutdents.framework_care.OrdinalResultEntity;
import com.wanny.amssutdents.framework_mvpbasic.BasePresenter;
import com.wanny.amssutdents.framework_net.rxjava.ApiCallback;
import com.wanny.amssutdents.framework_net.rxjava.SubscribCallBack;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 17:12
 */

public class NetContralPresenter extends BasePresenter<NetControlImpl> {


    private NetControlImpl netControl;
    public NetContralPresenter(NetControlImpl netControl) {
        this.netControl = netControl;
    }

    //设置控制时间
    public void appControlTime(BodyReq entity) {
        addSubscription(apiStores.getTime(entity), new SubscribCallBack<>(new ApiCallback<AppContralTimeResult>() {
            @Override
            public void onSuccess(AppContralTimeResult model) {
                if(netControl != null){
                    netControl.success(model);
                }
            }
            @Override
            public void onFailure(int code, String msg) {
//                if(filePostPresenterView != null){
//                    filePostPresenterView.getDataFail(msg);
//                }
            }
            @Override
            public void onCompleted() {

            }
        }));
    }
}
