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
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.AllAppAdapter;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.ApplicationResult;
import com.wanny.amssutdents.framework_mvpbasic.MvpActivity;
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
    private ArrayList<PackageInfo> installList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allapp_view);
        ButterKnife.bind(this);
        BodyReq bodyReq = new BodyReq();
        bodyReq.setMac("04:e6:76:c3:74:32");
        bodyReq.setStudentNumber("20171212001");
        mvpPresenter.getEquipInfo(bodyReq, "正在加载");
        mvpPresenter.getAppList(bodyReq, "正在加载");
        initView();
    }

    private void initView() {
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

    private AllAppAdapter.OperateListener operateListener = new AllAppAdapter.OperateListener() {
        @Override
        public void startOperate(int position) {
            startActivityByPackageName(installList.get(position).packageName);
        }
    };


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
            Toast.makeText(mContext, s.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0 & !pak.applicationInfo.enabled) {
                // 第三方应用
                dataList.add(pak);
            }
        }
        //获取包名： mPackageInfo.packageName
//        获取icon： mPackageInfo.getApplicationIcon(applicationInfo);
    }


    private void startActivityByPackageName(String packagename) {
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        if (resolveinfoList.iterator().next() != null) {
            ResolveInfo resolveinfo = resolveinfoList.iterator().next();
            if (resolveinfo != null) {
                // packagename = 参数packname
                String packageName = resolveinfo.activityInfo.packageName;
                // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
                String className = resolveinfo.activityInfo.name;
                // LAUNCHER Intent
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                // 设置ComponentName参数1:packagename参数2:MainActivity路径
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                startActivity(intent);
            }
        }
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
        popWindow.showAsDropDown(more, 10, 10);
        TextView about = (TextView) view.findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }

            }
        });
        TextView appLoad = (TextView) view.findViewById(R.id.appdownload);
        appLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                Intent intent = new Intent(AllApplicationActivity.this , DownLoadActivity.class);
                startActivity(intent);
            }
        });
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
                    installList.add(install.get(i));
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
