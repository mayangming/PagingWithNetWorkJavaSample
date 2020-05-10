package com.sample.page;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * 工厂模式
 * */
public class MyPageDataSourceFactory extends DataSource.Factory<Integer, ContactBean> {
    private MutableLiveData<MyPageDataSource> sourceLiveData =
            new MutableLiveData<>();
    private MyPageDataSource lastDataSource;
    private MutableLiveData<NetWorkStatus> netWorkStatus;

    public MyPageDataSourceFactory(MutableLiveData<NetWorkStatus> netWorkStatus) {
        this.netWorkStatus = netWorkStatus;
    }

    @NonNull
    @Override
    public DataSource<Integer, ContactBean> create() {
        lastDataSource = new MyPageDataSource(netWorkStatus);
        sourceLiveData.postValue(lastDataSource);
        return lastDataSource;
    }
}