package com.wanny.amssutdents.framework_net.retrofit;


import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.EquipmentResult;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.OpenBody;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContralTimeResult;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppControlBody;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.ApplicationResult;
import com.wanny.amssutdents.amsstudent_business.location_save.LocationBody;
import com.wanny.amssutdents.amsstudent_business.login_mvp.LoginEntity;
import com.wanny.amssutdents.framework_care.OrdinalBooleanEntity;
import com.wanny.amssutdents.framework_care.OrdinalResultEntity;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * 文件名： ApiStores
 * 功能：
 * 作者： wanny
 * 时间： 15:58 2016/8/5
 */
public interface ApiStores {
    //测试内网地址
//    String API_SERVER_URL = "http://183.230.7.249:9022/api/";
    //测试外网
    String API_SERVER_URL = "http://118.190.201.93:81/";

    //
    //汪琪诗
//    String API_SERVER_URL = "http://192.168.5.247:8088/api/" ;
    //陈良勇
//    String API_SERVER_URL = "http://192.168.5.244:9022/api/" ;
    //
//    String API_SERVER_URL = "http://yp.17gp.com:9001/api/";
    //登录
    @POST("UserService.svc/UserLogin")
    Observable<LoginEntity> login(@Body BodyReq loginBody);


    @POST("UserService.svc/GetEquipmentInfoBymacNo")
    Observable<EquipmentResult> getEquipInfo(@Body BodyReq loginBody);


    @POST("AppFile.svc/GetUserAppList")
    Observable<ApplicationResult> getAppList(@Body BodyReq loginBody);


    @POST("MapLocus.svc/PutMapLocus")
    Observable<OrdinalResultEntity> saveLocation(@Body LocationBody loginBody);


    @POST("UserService.svc/UserTrailTime")
    Observable<OrdinalResultEntity> saveOpenTime(@Body OpenBody body);


    //获取app的控制时间
    @POST("AppFile.svc/AppControl")
    Observable<OrdinalBooleanEntity> getAppControl(@Body AppControlBody body);


    @POST("AppFile.svc/GetAppControlData")
    Observable<AppContralTimeResult> getTime(@Body BodyReq body);


//
//    //获取列表
//    @GET("Project/GetBackPriceList")
//    Observable<PriceListResult> getBackPriceList(@Query("page") int page, @Query("pageSize") int pageSize, @Query("type") int type, @Query("key") String key);
//
//    //通过项目ID获取回价列表
//    @GET("Project/GetObjectDetails")
//    Observable<CallObjectResult> getPriceProject(@Query("projectId") String projectId, @Query("objectId") String objectId);
//
//    //临时保存回价信息
//    @POST("Project/PostBackTempPriceItem")
//    Observable<OrdinalResultEntity> saveObjectTemp(@Body SaveObjectTemp loginBody);
//
//    //通过项目ID获取回价列表
//    @GET("Project/GetNextAction")
//    Observable<OperateResult> getOpearte(@Query("projectId") String projectId, @Query("type") int type);
//
//    //获取操作人名字
//    @GET("Project/GetNextUser")
//    Observable<OperatNameResult> getOperatename(@Query("projectId") String projectId, @Query("nextAction") String type);
//
//    //保存回价信息
//    @POST("Project/PostBackPriceItem")
//    Observable<OrdinalResultEntity> savePrice(@Body SaveObjectTemp loginBody);
//
//    //获取我的工作列表
//    @GET("Project/GetMyWorkList")
//    //isCommon
//    Observable<MyWorkResult> getMyWork(@Query("isCommon") boolean isCommon, @Query("type") int type);
//
//    //项目管理
//    @GET("Project/GetProjectList")
//    //isCommon
//    Observable<ProjectResult> getProject(@Query("page") int page, @Query("pageSize") int pageSize, @Query("keyword") String keyword, @Query("status") int status);
//
//
//    //提交审核
//    @POST("Project/PostCheckPriceItem")
//    Observable<OrdinalResultEntity> submitShenhe(@Body SaveObjectTemp loginBody);
//
//    //提交价格给客户
//    @POST("Project/PostPriceToCustomer")
//    Observable<OrdinalResultEntity> submitCustmer(@Body MyWorkSubmitBody myworBody);
//
//
//    //获取项目详情
//    @GET("Project/GetProjectDetails")
//    //isCommon
//    Observable<ProjectDetailResult> getProjectDetail(@Query("projectId") String projectId);
//
//
//    //获取项目详情
//       @GET("Project/GetDispatchUser")
//        //isCommon
//     Observable<AttruteResult> getDistrubeData(@Query("projectId") String projectId);
//
//
//    //分配操作人
//    @POST("Project/PostDispatchHandler")
//    Observable<OrdinalResultEntity> setAttribute(@Body SetAttributeBody body);
//
//
//    //获取项目日志
//    @GET("Project/GetProjectLogs")
//    //isCommon
//    Observable<LogResult> getLogData(@Query("projectId") String projectId);
//    //获取受理详情
//    @GET("Accept/GetAcceptDetail")
//    Observable<HandlerResult> getAcceptDetail(@Query("id") String id);
//    //退出登录
//    @GET("person/LoginOut")
//    Observable<OrdinalResultEntity> loginOut(@Query("token") String projectId);
//
//    @GET("Accept/GetAcceptList")
//    Observable<CrubTaskReslut> crubTask(@Query("page") int page, @Query("pageSize") int pageSize, @Query("status") int status);
//
//    @POST("Accept/PostSaveCliemd")
//    Observable<OrdinalResultEntity> saveCrub(@Body CrubBody body);
//    //获取案例数据
//    @GET("Accept/GetAcceptFiles")
//    //isCommon
//    Observable<FileResult> getFileResult(@Query("id") String id);
//    //保存受理
//    @POST("Accept/PostSaveAccept")
//    Observable<OrdinalResultEntity> saveAccept(@Body AcceptBody body);
//    //获取案例数据
//    @GET("Record/GetCaseSection")
//    //isCommon
//    Observable<CaseResult> getCaseData(@Query("communityId") String communityId, @Query("caseType") String caseType, @Query("time") String time);
//
//    //登录
//    @POST("Record/AddGpsRecord")
//    Observable<OrdinalResultEntity> saveGps(@Body SaveGpsBody body);
//
//    //获取处理列表
//    @GET("Accept/GetAcceptList")
//    Observable<AcceptResult> getAccpetList(@Query("page") int page, @Query("pageSize") int pageSize, @Query("status") int status);

}
