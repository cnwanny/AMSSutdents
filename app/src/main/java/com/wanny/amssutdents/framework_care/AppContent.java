package com.wanny.amssutdents.framework_care;

/**
 * 文件名： AppContent
 * 功能：
 * 作者： wanny
 * 时间： 16:18 2017/1/18
 */
public interface AppContent {

    //权限请求 照相机
    int CAMREA_REQUESTCODE = 0x0001;
    //电话
    int PHONE_REQUESTCODE = 0x0002;
    //读写权限
    int STORAGE_REQUESTCODE = 0x0003;
    //位置信息
    int LOCATION_REQUESTCODE = 0x0004;
    //录音requestcode;
    int RECODEAUDIO_REQUESTCODE = 0x0005;
    //图片类型匹配
    String[] IMAGE_TYPE = new String[]{".jpg", ".jpeg", ".png",};

    //停止上传附件广播标示
    String StopReceiverAction = "com.wanny.stop.receiver";  //com.wanny.stop.receiver
    //附件上传成功广播标示
    String UploadSuccess = "com.android.success.upload";
    //微信分享
    String APP_ID = "wx98346eab7fb353fa";
    //QQ分享
    String QQ_ID = "1106226484";
    //微信支付成功后回调
    String WXPAY = "com.android.wanny.wxpay";


    //当前城市
    /**
     * 二维码请求的type
     */
    String REQUEST_SCAN_TYPE = "type";
    /**
     * 普通类型，扫完即关闭
     */
    int REQUEST_SCAN_TYPE_COMMON = 0;
    /**
     * 服务商登记类型，扫描
     */
    int REQUEST_SCAN_TYPE_REGIST = 1;


    /**
     * 扫描类型
     * 条形码或者二维码：REQUEST_SCAN_MODE_ALL_MODE
     * 条形码： REQUEST_SCAN_MODE_BARCODE_MODE
     * 二维码：REQUEST_SCAN_MODE_QRCODE_MODE
     */
    String REQUEST_SCAN_MODE = "ScanMode";
    /**
     * 条形码： REQUEST_SCAN_MODE_BARCODE_MODE
     */
    int REQUEST_SCAN_MODE_BARCODE_MODE = 0X100;
    /**
     * 二维码：REQUEST_SCAN_MODE_ALL_MODE
     */
    int REQUEST_SCAN_MODE_QRCODE_MODE = 0X200;
    /**
     * 条形码或者二维码：REQUEST_SCAN_MODE_ALL_MODE
     */
    int REQUEST_SCAN_MODE_ALL_MODE = 0X300;

    String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
    String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";

    String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
    String MESSAGE_ATTR_EXPRESSION_ID = "em_expression_id";

    String MESSAGE_ATTR_AT_MSG = "em_at_list";
    String MESSAGE_ATTR_VALUE_AT_MSG_ALL = "ALL";


    int CHATTYPE_SINGLE = 1;
    int CHATTYPE_GROUP = 2;
    int CHATTYPE_CHATROOM = 3;

    String EXTRA_CHAT_TYPE = "chatType";
    String EXTRA_USER_ID = "userId";


    //下来刷新
    String MODE_UPLOAD = "upload";
    //加载更多自动
    String MODE_LOADMORE = "loadmore";

    //上传经纬度
    String LOCATIONREVEIVER = "com.android.hifo.evaulate.loaction";


}
