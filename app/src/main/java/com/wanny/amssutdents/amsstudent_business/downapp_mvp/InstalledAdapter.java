package com.wanny.amssutdents.amsstudent_business.downapp_mvp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanny.amssutdents.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/26 15:15
 */

public class InstalledAdapter extends RecyclerView.Adapter<InstalledAdapter.InstalledHolder> {


    private ArrayList<PackageInfo> dataList;
    private Context context;


    public InstalledAdapter(ArrayList<PackageInfo> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public InstalledHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.installed_item_view, parent, false);
        return new InstalledHolder(view);
    }

    @Override
    public void onBindViewHolder(InstalledHolder holder, final int position) {
        PackageInfo data = dataList.get(position);
        PackageManager packageManager = context.getPackageManager();
        if (data != null) {
            holder.iconItemImage.setImageDrawable(data.applicationInfo.loadIcon(packageManager));
            holder.appItemName.setText(data.applicationInfo.loadLabel(packageManager));
            if(data.applicationInfo.enabled){
                holder.appItemEnable.setText("禁用");
            }else{
                holder.appItemEnable.setText("启用");
            }
        }
        holder.appItemEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(installedListener != null){
                    installedListener.setEnable(position);
                }
            }
        });


        holder.appItemUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(installedListener != null){
                    installedListener.uninstall(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class InstalledHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_item_image)
        ImageView iconItemImage;
        @BindView(R.id.app_item_name)
        TextView appItemName;
        @BindView(R.id.app_item_detail)
        TextView appItemDetail;
        @BindView(R.id.app_item_uninstall)
        TextView appItemUninstall;
        @BindView(R.id.app_item_enable)
        TextView appItemEnable;

        public InstalledHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setInstalledListener(InstalledListener installedListener) {
        this.installedListener = installedListener;
    }

    private InstalledListener installedListener;

    public interface InstalledListener{
        //设置禁用还是启用
        void setEnable(int position);
        //卸载应用
        void uninstall(int position);
    }
}
