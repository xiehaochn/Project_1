package com.hawx.project_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import Data.CommunicationData;
import Utils.CommunicationRVAdapter;
import Data.DataList;

/**
 * Created by Administrator on 2016/1/14.
 */
public class CommunicationRecordActivity extends BaseActivity {
    public  final String SER_KEY = "friendprofilelist";
    private Toolbar toolbar;
    private List<CommunicationData> communicationDataList;
    private CommunicationRVAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_pay_invite);
        BaseActivity.addActivity(this);
        toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Communication Record");
        setSupportActionBar(toolbar);
        DataList dataList = (DataList) getIntent().getSerializableExtra(SER_KEY);
        communicationDataList=dataList.getCommunicationDataList();
        adapter=new CommunicationRVAdapter(this,communicationDataList);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
