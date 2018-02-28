package com.wanny.amssutdents.amsstudent_business.downapp_mvp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanny.amssutdents.R;
import com.wanny.amssutdents.amsstudent_business.ApplicationInfo;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/26 15:15
 */

public class NotInstallAdapter extends RecyclerView.Adapter<NotInstallAdapter.InstalledHolder> {


    private ArrayList<ApplicationInfo> dataList;
    private Context context;


    public NotInstallAdapter(ArrayList<ApplicationInfo> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public InstalledHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notinstall_item_view, parent, false);
        return new InstalledHolder(view);
    }

    @Override
    public void onBindViewHolder(final InstalledHolder holder, final int position) {
        ApplicationInfo data = dataList.get(position);
        PackageManager packageManager = context.getPackageManager();
        if (data != null) {
            if(!TextUtils.isEmpty(data.getAppIco())){
                Glide.with(context).load(data.getAppIco()).into(holder.iconNotitemImage);
            }
            holder.appNotitemName.setText(data.getAppName());
            holder.appNotitemDetail.setText(data.getAppName());
        }
        holder.appItemDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDownLoadListener != null) {
                    startDownLoadListener.startDown(holder.appItemDownload ,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class InstalledHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon_notitem_image)
        ImageView iconNotitemImage;
        @BindView(R.id.app_notitem_name)
        TextView appNotitemName;
        @BindView(R.id.app_notitem_detail)
        TextView appNotitemDetail;
        @BindView(R.id.app_item_download)
        TextView appItemDownload;
        public InstalledHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setStartDownLoadListener(StartDownLoadListener startDownLoadListener) {
        this.startDownLoadListener = startDownLoadListener;
    }

    private StartDownLoadListener startDownLoadListener;

    public interface StartDownLoadListener {
        //设置禁用还是启用
        void startDown(TextView view ,int position);
    }
}
