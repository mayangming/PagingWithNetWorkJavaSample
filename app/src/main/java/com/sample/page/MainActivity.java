package com.sample.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactViewModel contactViewModel;
    private ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewModel();
    }

    private void initView(){
        findViewById(R.id.load_more).setOnClickListener(this::onClick);
        recyclerView = findViewById(R.id.rv);
        initRecycleView();
    }

    private void initRecycleView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter();
        recyclerView.setAdapter(contactAdapter);
    }

    private void initViewModel(){
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.init();
        contactViewModel.netWorkChange().observe(this, new Observer<NetWorkStatus>() {
            @Override
            public void onChanged(NetWorkStatus netWorkStatus) {
                switch (netWorkStatus){
                    case LOADING:
                        Log.e("YM","加载中");
                        break;
                    case SUCCESS:
                        Log.e("YM","加载成功");
                        break;
                    case FAIL:
                        Log.e("YM","加载失败");
                        break;
                }
            }
        });
    }

    private void onClick(View view){
        switch (view.getId()){
            case R.id.load_more:
                contactViewModel.getConcertList().observe(this,contactAdapter::submitList);
                break;
        }
    }
}
