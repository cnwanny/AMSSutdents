package com.wanny.amssutdents.framework_ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanny.amssutdents.R;
import com.wanny.amssutdents.amsstudent_business.ApplicationInfo;
import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.AllAppImpl;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.AppApplicationPresenter;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.ClassInfo;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.EquipmentInfo;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.EquipmentResult;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.OpenBody;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AllAppAdapter;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppContralTimeResult;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AppControlBody;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.ApplicationResult;
import com.wanny.amssutdents.framework_care.OrdinalBooleanEntity;
import com.wanny.amssutdents.framework_care.OrdinalResultEntity;
import com.wanny.amssutdents.framework_mvpbasic.MvpActivity;
import com.wanny.amssutdents.framework_ui.service.LocationUploadService;
import com.wanny.amssutdents.framework_utils.AppUtils;
import com.wanny.amssutdents.framework_utils.MacOperate;
import com.wanny.amssutdents.framework_utils.PreferenceUtil;
import com.wanny.amssutdents.framework_view.MorePopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/23 13:42
 */

public class AllApplicationActivity extends MvpActivity<AppApplicationPresenter> implements AllAppImpl {

    //
    @BindView(R.id.my_school)
    TextView mySchool;
    @BindView(R.id.my_rank)
    TextView myRank;
    @BindView(R.id.my_class)
    TextView myClass;
    @BindView(R.id.my_identifyId)
    TextView myIdentifyId;
    @BindView(R.id.my_weather)
    TextView myWeather;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.allapp_recycler)
    RecyclerView allappRecycler;

    private AllAppAdapter adapter;
    private ArrayList<PackageInfo> dataList;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<ApplicationInfo> installList;

    private String studentId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allapp_view);
        ButterKnife.bind(this);
        BodyReq bodyReq = new BodyReq();
        bodyReq.setMac(MacOperate.getMac(mContext));
        studentId =  PreferenceUtil.getInstance(mContext).getString("StudentId","");
        bodyReq.setStudentNumber(studentId);
        mvpPresenter.getEquipInfo(bodyReq, "正在加载");
        mvpPresenter.getAppList(bodyReq, "正在加载");
        initView();

    }

    private void initView() {
        Intent intentNet = new Intent(this, LocationUploadService.class);
        startService(intentNet);
        dataList = new ArrayList<>();
        installList = new ArrayList<>();
        getThridAppList();
        gridLayoutManager = new GridLayoutManager(getBaseContext(), 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setReverseLayout(false);
        allappRecycler.setLayoutManager(gridLayoutManager);
        adapter = new AllAppAdapter(installList, mContext);
        adapter.setOperateListener(operateListener);
        allappRecycler.setAdapter(adapter);
    }


    private int selectPos = -1;

    private AllAppAdapter.OperateListener operateListener = new AllAppAdapter.OperateListener() {
        @Override
        public void startOperate(int position) {
            AppControlBody body = new AppControlBody();
            body.setMac(MacOperate.getMac(mContext));
            body.setStudentNumber(studentId);
            body.setAppId(installList.get(position).getAppID());
            mvpPresenter.getAppControl(body,"正在查询");
            selectPos = position ;
//            startActivityByPackageName(installList.get(position).packageName);
        }
    };


    private boolean need = true;

    @Override
    protected void onResume() {
        super.onResume();
        if(true){
            if(mvpPresenter != null){
                OpenBody body = new OpenBody();
                body.setMac(MacOperate.getMac(mContext));
                mvpPresenter.setStartTime(body,"");
            }
        }
    }

    @Override
    public void success(EquipmentResult s) {
        if (s.isStatus()) {
            EquipmentInfo value = s.getData();
            if (value != null) {
                ClassInfo classInfo = value.getClassInfo();
                if (classInfo != null) {
                    if (!TextUtils.isEmpty(classInfo.getClassName())) {
                        myClass.setText(classInfo.getClassName());
                    }
                    if (!TextUtils.isEmpty(classInfo.getGradeName())) {
                        myRank.setText(classInfo.getGradeName());
                    }
                    if (!TextUtils.isEmpty(classInfo.getSchoolInfo().getSchoolName())) {
                        mySchool.setText(classInfo.getSchoolInfo().getSchoolName());
                    }
                }
                if (!TextUtils.isEmpty(value.getStuNo())) {
                    myIdentifyId.setText(value.getStuNo());
                }
            }
        } else {
            if(s.getCode().equals("999") ){
                Toast.makeText(mContext,s.getMessage(),Toast.LENGTH_SHORT).show();
                need = false;
                Intent intent = new Intent(AllApplicationActivity.this , LoginActivity.class);
                startActivity(intent);

            }
        }
    }


    @Override
    public void saveTime(OrdinalResultEntity entity) {

    }

    @OnClick(R.id.more)
    void showPop(View view) {
        createPopWindows();
    }

    private void getThridAppList() {
        PackageManager packageManager = this.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (int i = 0; i < packageInfoList.size(); i++) {
            PackageInfo pak = (PackageInfo) packageInfoList.get(i);
            //判断是否为系统预装的应用
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // 第三方应用
                dataList.add(pak);
            }
        }
        //获取包名： mPackageInfo.packageName
//        获取icon： mPackageInfo.getApplicationIcon(applicationInfo);
    }


    private void startActivityByPackageName(String packagename) {

        PackageManager packageManager = mContext.getPackageManager();
        Intent it= packageManager.getLaunchIntentForPackage(packagename);
        startActivity(it);

//        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
//        PackageInfo packageinfo = null;
//        try {
//            packageinfo = getPackageManager().getPackageInfo(packagename, 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (packageinfo == null) {
//            return;
//        }
//        need = false;
//        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
//        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
//        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        resolveIntent.setPackage(packageinfo.packageName);
//
//        // 通过getPackageManager()的queryIntentActivities方法遍历
//        List<ResolveInfo> resolveinfoList = getPackageManager()
//                .queryIntentActivities(resolveIntent, 0);
//        if (resolveinfoList.iterator().next() != null) {
//            ResolveInfo resolveinfo = resolveinfoList.iterator().next();
//            if (resolveinfo != null) {
//                // packagename = 参数packname
//                String packageName = resolveinfo.activityInfo.packageName;
//                // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
//                String className = resolveinfo.activityInfo.name;
//                // LAUNCHER Intent
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//
//                // 设置ComponentName参数1:packagename参数2:MainActivity路径
//                ComponentName cn = new ComponentName(packageName, className);
//                intent.setComponent(cn);
//                startActivity(intent);
//            }
//        }
    }
    //创建下来的pop

    private MorePopWindow popWindow;

    private void createPopWindows() {
        if (popWindow == null) {
            popWindow = new MorePopWindow(mActivity);
        }
        if (popWindow.isShowing()) {
            return;
        }
        View view = popWindow.getContentView();
        popWindow.showAsDropDown(more, -AppUtils.getScreenWidth(mContext) / 8, 10,Gravity.CENTER);
        TextView about = (TextView) view.findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                need = false;
                Intent intent = new Intent(AllApplicationActivity.this , LoginActivity.class);
                startActivity(intent);


            }
        });
        TextView appLoad = (TextView) view.findViewById(R.id.appdownload);
        appLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                need = false;
                Intent intent = new Intent(AllApplicationActivity.this , DownLoadActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void getControlTime(OrdinalBooleanEntity entity) {
        //获取到当前返回的时间
        //来判定是否在可用时间段内
        PackageManager pm = getPackageManager();
        if(entity.getData()){
            pm.setApplicationEnabledSetting(installList.get(selectPos).getAppPackageName(), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, 0);
            startActivityByPackageName(installList.get(selectPos).getAppPackageName());
        }else{
            pm.setApplicationEnabledSetting(installList.get(selectPos).getAppPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER, 0);
            Toast.makeText(mContext,"该时间段禁止"+ installList.get(selectPos).getAppName() + "使用",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getAppList(ApplicationResult result) {
        //过滤安装成功的apk
        if (result.isStatus()) {
            if (result.getDataList() != null) {
                ArrayList<ApplicationInfo> data = result.getDataList();
                //遍历二分算法
                rexApp(dataList, data);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void rexApp(ArrayList<PackageInfo> install, ArrayList<ApplicationInfo> allapp) {
        for (int i = 0; i < install.size(); i++) {
            for (int j = 0; j < allapp.size(); j++) {
                if (allapp.get(j).appPackageName.equals(install.get(i).packageName)) {
                    installList.add(allapp.get(j));
                }
            }
        }
    }


    //展示列表
    @Override
    public void fail(int code, String errorMessage) {

    }


    @Override
    public void loadIng(String title) {

    }


    @Override
    public void hide() {

    }


    @Override
    protected AppApplicationPresenter createPresenter() {
        return new AppApplicationPresenter(this);
    }
}
