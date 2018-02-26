package com.wanny.amssutdents.amsstudent_business.login_mvp;

import android.text.TextUtils;

import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.framework_mvpbasic.BasePresenter;
import com.wanny.amssutdents.framework_net.rxjava.ApiCallback;
import com.wanny.amssutdents.framework_net.rxjava.SubscribCallBack;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/23 10:19
 */

public class LoginPresenter extends BasePresenter<LoginImpl> {


    public LoginPresenter(LoginImpl view){
        attachView(view);
    }



    //抢单调用
    public void login(BodyReq body , final String loading) {
        //执行网络请求的回调
        if(!TextUtils.isEmpty(loading)){
            if(mvpView != null){
                mvpView.loadIng(loading);
            }
        }
        addSubscription(apiStores.login(body),new SubscribCallBack<>(new ApiCallback<LoginEntity>() {
            @Override
            public void onSuccess(LoginEntity model) {
                if(!TextUtils.isEmpty(loading)){
                    mvpView.hide();
                }
                mvpView.success(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.fail(code,msg);
                mvpView.hide();
            }
            @Override
            public void onCompleted() {

            }
        }));
    }

}
