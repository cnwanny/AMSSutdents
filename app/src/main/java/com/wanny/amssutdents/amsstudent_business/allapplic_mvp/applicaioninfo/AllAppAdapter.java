package com.wanny.amssutdents.amsstudent_business.allapplic_mvp.applicaioninfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
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
 * Time: 2018/2/24 13:42
 */

public class AllAppAdapter extends RecyclerView.Adapter<AllAppAdapter.AllAppHolder> {



    private ArrayList<ApplicationInfo> dataList;
    private Context context;

    public AllAppAdapter(ArrayList<ApplicationInfo> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public AllAppAdapter(ArrayList<ApplicationInfo> dataList, Context context, OperateListener operateListener) {
        this.dataList = dataList;
        this.context = context;
        this.operateListener = operateListener;
    }

    public ArrayList<ApplicationInfo> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<ApplicationInfo> dataList) {
        this.dataList = dataList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public AllAppHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allapp_item_view, parent, false);
        return new AllAppHolder(view);
    }

    @Override
    public void onBindViewHolder(AllAppHolder holder, final int position) {
       if(dataList != null){
           ApplicationInfo info = dataList.get(position);
           if(info != null){
               Glide.with(context).load(info.getAppIco()).into(holder.appicon);
               holder.appname.setText(info.getAppName());
           }
       }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(operateListener != null){
                   operateListener.startOperate(position);
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class AllAppHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.appicon)
        ImageView appicon;
        @BindView(R.id.appname)
        TextView appname;
        public AllAppHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private OperateListener operateListener;



    public void setOperateListener(OperateListener operateListener) {
        this.operateListener = operateListener;
    }

    public interface OperateListener{
        void startOperate(int position);
    }
}
