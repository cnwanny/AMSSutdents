package com.wanny.amssutdents.amsstudent_business.allapplic_mvp;

import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContralTimeResult;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContrilTime;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.ApplicationResult;
import com.wanny.amssutdents.framework_care.OrdinalBooleanEntity;
import com.wanny.amssutdents.framework_care.OrdinalResultEntity;
import com.wanny.amssutdents.framework_mvpbasic.BaseOperateImp;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/23 13:43
 */

public interface AllAppImpl extends BaseOperateImp<EquipmentResult> {


    void getAppList(ApplicationResult result);
    void saveTime(OrdinalResultEntity entity);

    void getControlTime(OrdinalBooleanEntity entity);


}
