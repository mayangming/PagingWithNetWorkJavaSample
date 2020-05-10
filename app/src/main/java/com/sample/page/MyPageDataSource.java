package com.sample.page;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义数据源
 */
public class MyPageDataSource extends PageKeyedDataSource<Integer,ContactBean> {

    private MutableLiveData<NetWorkStatus> netWorkStatus;

    public MyPageDataSource(MutableLiveData<NetWorkStatus> netWorkStatus) {
        this.netWorkStatus = netWorkStatus;
    }

    /**
     * 初始化加载
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ContactBean> callback) {
        Log.e("YM","初始化");
        callback.onResult(initData(1),1,2);
    }

    /**
     * 加载之前
     * @param params
     * @param callback
     */
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ContactBean> callback) {
        Log.e("YM","加载前");
    }

    /**
     * 加载之后
     * @param params
     * @param callback
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ContactBean> callback) {
        Log.e("YM","加载后-----key:"+params.key);
        netWorkStatus.postValue(NetWorkStatus.LOADING);
        callback.onResult(initData(params.key),params.key+1);
        netWorkStatus.postValue(NetWorkStatus.SUCCESS);
    }

    /**
     * 加载数据
     */
    private List<ContactBean> initData(int key) {
        Log.e("YM","key值为:"+key);
        List<ContactBean> contactBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ContactBean contactBean = new ContactBean();
            contactBean.setContent("第" + i + "个数据---->key为:"+key);
            contactBeans.add(contactBean);
        }//asn
//        contactViewModel.insert(contactBeans);
        return contactBeans;
    }

}