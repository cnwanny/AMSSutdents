package com.wanny.amssutdents.framework_ui;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wanny.amssutdents.R;
import com.wanny.amssutdents.amsstudent_business.ApplicationInfo;
import com.wanny.amssutdents.amsstudent_business.BodyReq;
import com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo.ApplicationResult;
import com.wanny.amssutdents.amsstudent_business.downapp_mvp.DownAppImpl;
import com.wanny.amssutdents.amsstudent_business.downapp_mvp.DownAppPresenter;
import com.wanny.amssutdents.amsstudent_business.downapp_mvp.InstalledAdapter;
import com.wanny.amssutdents.amsstudent_business.downapp_mvp.NotInstallAdapter;
import com.wanny.amssutdents.amsstudent_business.downapp_mvp.UpdateEntity;
import com.wanny.amssutdents.framework_mvpbasic.MvpActivity;
import com.wanny.amssutdents.framework_utils.AppUtils;
import com.wanny.amssutdents.framework_utils.StoragePath;
import com.wanny.amssutdents.framework_view.ListViewItemDecotion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/24 17:21
 */

public class DownLoadActivity extends MvpActivity<DownAppPresenter> implements DownAppImpl {

    //返回操作
    @BindView(R.id.down_close)
    ImageView downClose;
    //已经安装的
    @BindView(R.id.down_install)
    TextView downInstall;
    //未安装的
    @BindView(R.id.down_notinstall)
    TextView downNotinstall;
    //
    @BindView(R.id.install_header)
    RelativeLayout installHeader;
    //已经安装的
    @BindView(R.id.installed_recycle)
    RecyclerView installedRecycle;
    //未安装的
    @BindView(R.id.notinstall_recycle)
    RecyclerView notinstallRecycle;


    private ArrayList<PackageInfo> installList;
    private ArrayList<ApplicationInfo> notInstallList;
    private InstalledAdapter installAdapter;
    private NotInstallAdapter notInstallAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_view);
        ButterKnife.bind(this);
        initView();
        getThridAppList();
        startReginster();
    }
    //加载的进度设置显示
    private Queue<UpdateEntity> progressQue = new LinkedList<>();
    private boolean isLoading = false;

    private void initView() {
        installList = new ArrayList<>();
        notInstallList = new ArrayList<>();
        dataList = new ArrayList<>();
        installAdapter = new InstalledAdapter(installList, mContext);
        notInstallAdapter = new NotInstallAdapter(notInstallList, mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        installedRecycle.setLayoutManager(layoutManager);
        if (installAdapter != null) {
            installedRecycle.setAdapter(installAdapter);
        }
        installedRecycle.addItemDecoration(new ListViewItemDecotion(mContext, ListViewItemDecotion.ORIVATION_VERCAL, R.drawable.listview_1dp_drawabel));
        installAdapter.setInstalledListener(installedListener);


        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mContext);
        notinstallRecycle.setLayoutManager(layoutManager2);
        if (notInstallAdapter != null) {
            notinstallRecycle.setAdapter(notInstallAdapter);
        }
        notinstallRecycle.addItemDecoration(new ListViewItemDecotion(mContext, ListViewItemDecotion.ORIVATION_VERCAL, R.drawable.listview_1dp_drawabel));
        notInstallAdapter.setStartDownLoadListener(startDownLoadListener);


        BodyReq bodyReq = new BodyReq();
        bodyReq.setMac("04:e6:76:c3:74:32");
        bodyReq.setStudentNumber("cc20171212001");
        mvpPresenter.getAppList(bodyReq, "正在加载");
    }


    private InstalledAdapter.InstalledListener installedListener = new InstalledAdapter.InstalledListener() {
        @Override
        public void setEnable(int position) {
            //
            PackageInfo info = installList.get(position);
            PackageManager pm = getPackageManager();
            if (info.applicationInfo.enabled) {
                pm.setApplicationEnabledSetting(info.packageName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER, 0);
            } else {
                pm.setApplicationEnabledSetting(info.packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, 0);
            }
            installAdapter.notifyItemChanged(position);
        }
        //卸载应用
        @Override
        public void uninstall(int position) {
            //卸载应用
            PackageInfo info = installList.get(position);
            Uri uri = Uri.fromParts("package", info.applicationInfo.packageName, null);
            Intent intent = new Intent(Intent.ACTION_DELETE, uri);
            startActivity(intent);
        }
    };


    //广播
    public  class AppInstallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            updataList();
        }
    }

    private AppInstallReceiver receiver;
    private void startReginster(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.setPriority(0);//设置动态优先级
        receiver  = new AppInstallReceiver();
        registerReceiver(receiver,filter);
    }


    private NotInstallAdapter.StartDownLoadListener startDownLoadListener = new NotInstallAdapter.StartDownLoadListener() {
        @Override
        public void startDown(TextView textView, int position) {
            //启动下载
            UpdateEntity updateEntity = new UpdateEntity();
            updateEntity.setPosition(position);
            updateEntity.setTextView(textView);
            progressQue.offer(updateEntity);
            if(isLoading){
                 textView.setText("队列中");
            }else{
                isLoading = true;
                String appUrl = "http://118.190.201.93:81/DownLoadFile.ashx?appId=" + notInstallList.get(position).getAppID();
                startDownLoad(appUrl, notInstallList.get(position).getAppName());
            }
        }
    };

    private void startDownLoad(String appUpdateUrl, String appName) {

        String localpath;
        if (!TextUtils.isEmpty(StoragePath.apkDir)) {
            File file = new File(StoragePath.apkDir, appName + ".apk");
            if (file.exists()) {
                file.delete();
            }
            localpath = StoragePath.apkDir + "/" + appName + ".apk";
        } else {
            StoragePath.createDirs();
            localpath = StoragePath.apkDir + "/" + appName + ".apk";
        }
        File locaFile = new File(localpath);
        if (locaFile.exists()) {
            installApk();
            return;
        }
        startLoading(appUpdateUrl, locaFile);
    }


    //启动下载
    private void startLoading(final String httpurl, final File localpath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int downnum = 0;//已下载的大小
                    int downcount = 0;//下载百分比
                    URL url = new URL(httpurl);
                    URLConnection connection = url.openConnection();
                    //输入流
                    InputStream inputStream = connection.getInputStream();
                    byte[] bs = new byte[1024];
                    // 读取到的数据长度
                    int length = connection.getContentLength();
                    // 输出的文件流
                    OutputStream os = new FileOutputStream(localpath);
                    // 开始读取
                    int readsize = 0;
                    while ((readsize = inputStream.read(bs)) != -1) {
                        os.write(bs, 0, readsize);
                        downnum += readsize;
                        if ((downcount == 0) || (int) (downnum * 100 / length) - 1 > downcount) {
                            downcount += 1;
                            Message msg = new Message();
                            msg.what = 0x0001;
                            msg.obj = (int) downnum * 100 / length;
                            mHandler.sendMessage(msg);
                        }
                        if (downnum == length) {
                            Message msg = new Message();
                            msg.what = 0x0002;
                            mHandler.sendMessage(msg);
                        }
                    }
                    // 完毕，关闭所有链接
                    os.close();
                    inputStream.close();
                    //执行完毕后。
                    installApk();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //自动安装apk
    @TargetApi(Build.VERSION_CODES.O)
    private void installApk() {
        //删除数据库 ，每次更新app的时候。
//        FileOperateData fileOperateData = FileOperateData.getInstance(getApplicationContext());
//        fileOperateData.deleteAllData();
        //但是加字段后会出现问题。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                startActivity(intent);
            }
        }
        String appName = notInstallList.get(progressQue.poll().getPosition()).getAppName();
        File apkFile = new File(StoragePath.apkDir + "/" + appName + ".apk");
        if (!apkFile.exists()) {
            return;
        }
        openFile(apkFile);
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        PreferenceUtil.getInstance(getApplicationContext()).saveBoolean("isFirst", false);
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
//
//        }
//        i.setDataAndType(Uri.parse("file://" + apkFile.toString()),
//                "application/vnd.android.package-archive");
//        startActivity(i);
    }

    /**
     * 重点在这里
     */
    public void openFile(File var0) {
        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(this.getBaseContext(), "com.wanny.amssutdents.fileProvider", var0);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, mContext.getContentResolver().getType(uriForFile));
        } else {
            var2.setDataAndType(Uri.fromFile(var0), getMIMEType(var0));
        }
        try {
            startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }


    //更新api
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //当前经度
            if (msg.what == 0x0001) {
//                View view = notinstallRecycle.getLayoutManager().getChildAt(0);
//                TextView progress = (TextView) view.findViewById(R.id.down_notinstall);
                if(progressQue.size() > 0){
                    UpdateEntity entity = progressQue.poll();
                    if(entity != null && entity.getTextView() != null){
                        entity.getTextView().setText((String) (msg.obj).toString() + "%");
                    }
                }
            }else if(msg.what == 0x0002){
                if(progressQue.size() > 0){
                    progressQue.remove();
                }else{
                    isLoading = false;
                    return;
                }
                if(progressQue.size() > 0){
                    String appUrl = "http://118.190.201.93:81/DownLoadFile.ashx?appId=" + notInstallList.get(progressQue.poll().getPosition()).getAppID();
                    startDownLoad(appUrl, notInstallList.get(progressQue.poll().getPosition()).getAppName());
                }
            }
        }
    };


    private ArrayList<PackageInfo> dataList;

    private void getThridAppList() {
        PackageManager packageManager = this.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (int i = 0; i < packageInfoList.size(); i++) {
            PackageInfo pak = (PackageInfo) packageInfoList.get(i);
            //判断是否为系统预装的应用
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0 ) {
                // 第三方应用
                dataList.add(pak);
            }
        }
        //获取包名： mPackageInfo.packageName
//        获取icon： mPackageInfo.getApplicationIcon(applicationInfo);
    }


    @OnClick(R.id.down_install)
    void setInstall(View view) {
        downInstall.setBackgroundResource(R.drawable.install_selected_drawable);
        downNotinstall.setBackgroundResource(R.drawable.notinstall_drawable);
        AppUtils.showView(installedRecycle);
        AppUtils.notShowView(notinstallRecycle);
    }


    @OnClick(R.id.down_notinstall)
    void setNoInstall(View view) {
        downInstall.setBackgroundResource(R.drawable.install_drawable);
        downNotinstall.setBackgroundResource(R.drawable.notinstall_selected_drawable);
        AppUtils.notShowView(installedRecycle);
        AppUtils.showView(notinstallRecycle);
    }

    @Override
    public void success(ApplicationResult data) {
        //这里做过滤
        if (data.isStatus()) {
            if (data.getDataList() != null) {
                allAppList.clear();
                allAppList.addAll(data.getDataList());
                //遍历二分算法
                updataList();
            }
        }
    }

   //更新list
    private void updataList(){
        rexApp(dataList, allAppList);
        setNotInstall(allAppList);
        installAdapter.notifyDataSetChanged();
        notInstallAdapter.notifyDataSetChanged();
    }
     //网络获取到的全部的应用列表
    private ArrayList<ApplicationInfo> allAppList = new ArrayList<>();


    private void rexApp(ArrayList<PackageInfo> install, ArrayList<ApplicationInfo> allapp) {
        for (int i = 0; i < install.size(); i++) {
            for (int j = 0; j < allapp.size(); j++) {
                if (allapp.get(j).appPackageName.equals(install.get(i).packageName)) {
                    installList.add(install.get(i));
                }
            }
        }
    }

    private void setNotInstall(ArrayList<ApplicationInfo> allapp) {
        for (int j = 0; j < allapp.size(); j++) {
            boolean isNot = true;
            for (int i = 0; i < installList.size(); i++) {
                if (allapp.get(j).appPackageName.equals(installList.get(i).packageName)) {
                    isNot = false;
                    break;
                }
            }
            if (isNot) {
                notInstallList.add(allapp.get(j));
            }
        }
    }

    @Override
    public void fail(int code, String errorMessage) {

    }

    @Override
    public void loadIng(String title) {

    }

    @Override
    public void hide() {

    }
    //
    @Override
    protected DownAppPresenter createPresenter() {
        return new DownAppPresenter(this);
    }

    //
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver != null){
            unregisterReceiver(receiver);
        }
    }
}
