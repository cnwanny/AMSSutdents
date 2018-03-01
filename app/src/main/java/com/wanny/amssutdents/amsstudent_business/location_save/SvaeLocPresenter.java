package com.wanny.amssutdents.amsstudent_business.location_save;

import com.wanny.amssutdents.framework_care.OrdinalResultEntity;
import com.wanny.amssutdents.framework_mvpbasic.BasePresenter;
import com.wanny.amssutdents.framework_net.rxjava.ApiCallback;
import com.wanny.amssutdents.framework_net.rxjava.SubscribCallBack;

import java.util.ArrayList;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/3/1 10:59
 */


public class SvaeLocPresenter extends BasePresenter<SaveLocaImpl> {
    private SaveLocaImpl saveLocaImpl;
    public SvaeLocPresenter(SaveLocaImpl saveLocaImpl) {
        this.saveLocaImpl = saveLocaImpl;
    }
    public void submitFileInfo(LocationBody entity) {
        addSubscription(apiStores.saveLocation(entity), new SubscribCallBack<>(new ApiCallback<OrdinalResultEntity>() {
            @Override
            public void onSuccess(OrdinalResultEntity model) {
                if(saveLocaImpl != null){
                    saveLocaImpl.success(model);
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

