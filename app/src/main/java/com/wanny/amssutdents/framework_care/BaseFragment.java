package com.wanny.amssutdents.framework_care;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wanny.amssutdents.framework_utils.LogUtil;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 文件名： BaseFragment
 * 功能：
 * 作者： wanny
 * 时间： 17:05 2016/8/5
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    public Context mContext;
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LogUtil.log(this.getClass().getName()+"=onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mActivity = getActivity();
        mContext = mActivity.getBaseContext();
        isViewCreated = true;
        lazyLoad();
    }




    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {
        LogUtil.log(this.getClass().getName()+"=onAttach()");
        super.onAttach(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtil.log(this.getClass().getName()+"=onCreate()");
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.log(this.getClass().getName()+"=onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        LogUtil.log(this.getClass().getName()+"=onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        LogUtil.log(this.getClass().getName()+"=onResume()");
        super.onResume();
    }

    @Override
    public void onStart() {
        LogUtil.log(this.getClass().getName()+"=onStart()");
        super.onStart();
    }




    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        LogUtil.log(this.getClass().getName()+"=onViewStateRestored()");

        super.onViewStateRestored(savedInstanceState);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.log(this.getClass().getName()+"=onActivityResult()");
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onPause() {
        LogUtil.log(this.getClass().getName()+"=onPause()");
        super.onPause();
    }
    @Override
    public void onStop() {
        LogUtil.log(this.getClass().getName()+"=onStop()");
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }


    protected abstract void loadData();

    @Override
    public void onDestroyView() {
        LogUtil.log(this.getClass().getName()+"=onDestroyView()");
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
    }

    @Override
    public void onDestroy() {
        LogUtil.log(this.getClass().getName()+"=onDestroy()");
        super.onDestroy();
        onUnsubscribe();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
//        }
        mCompositeSubscription.add(subscription);
    }
}
