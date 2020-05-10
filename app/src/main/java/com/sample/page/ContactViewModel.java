package com.sample.page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * 数据处理中心
 */
public class ContactViewModel extends ViewModel {
    private LiveData<PagedList<ContactBean>> concertList;
    private DataSource<Integer, ContactBean> mostRecentDataSource;
    private MutableLiveData<NetWorkStatus> netWorkStatus = new MutableLiveData<>();
    PagedList.Config config = new PagedList.Config.Builder()
            .setPageSize(10)    //每页显示的词条数
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10) //首次加载的数据量
            .setPrefetchDistance(1)     //距离底部还有多少条数据时开始预加载
            .build();
    public ContactViewModel() {
        MyPageDataSourceFactory myPageDataSourceFactory = new MyPageDataSourceFactory(netWorkStatus);
        mostRecentDataSource = myPageDataSourceFactory.create();
        concertList = new LivePagedListBuilder<Integer,ContactBean>(myPageDataSourceFactory,config).build();
    }

    public void init(){
        mostRecentDataSource.invalidate();
    }
    public LiveData<PagedList<ContactBean>> getConcertList(){
        return concertList;
    }

    public LiveData<NetWorkStatus> netWorkChange(){
        return netWorkStatus;
    }

}