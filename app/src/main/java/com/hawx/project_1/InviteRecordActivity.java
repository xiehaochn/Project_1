package com.hawx.project_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import Data.DataList;
import Data.InviteRecord;
import Utils.InviteRVAdapter;
import Utils.PayRVAdapter;

/**
 * Created by Administrator on 2016/1/14.
 */
public class InviteRecordActivity extends BaseActivity {
    public  final String SER_KEY = "friendprofilelist";
    private List<InviteRecord> inviteRecordList;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private InviteRVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_pay_invite);
        BaseActivity.addActivity(this);
        DataList dataList= (DataList) getIntent().getSerializableExtra(SER_KEY);
        inviteRecordList=dataList.getInviteRecord();
        toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Invite Record");
        setSupportActionBar(toolbar);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        adapter=new InviteRVAdapter(this,inviteRecordList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
