package com.hawx.project_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import java.util.List;
import java.util.PriorityQueue;

import Data.DataList;
import Data.PayData;
import Utils.PayRVAdapter;

/**
 * Created by Administrator on 2016/1/14.
 */
public class PayRecordActivity extends BaseActivity {
    public  final String SER_KEY = "friendprofilelist";
    private List<PayData> payDataList;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private PayRVAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_pay_invite);
        BaseActivity.addActivity(this);
        DataList dataList= (DataList) getIntent().getSerializableExtra(SER_KEY);
        payDataList=dataList.getPayData();
        toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Pay Record");
        setSupportActionBar(toolbar);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        adapter=new PayRVAdapter(this,payDataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
