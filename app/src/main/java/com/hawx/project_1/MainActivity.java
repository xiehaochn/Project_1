package com.hawx.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import Data.InviteRecord;
import Data.PayData;
import Fragments.FriendFragment;
import Data.CommunicationData;
import Fragments.UserFragment;
import Utils.FriendRecyclerViewAdapter;
import Data.Information;
import Data.DataList;
import Utils.MainActivityFragmentAdapter;

/**
 * Created by Administrator on 2016/1/13.
 */
public class MainActivity extends BaseActivity {
    public  final String SER_KEY = "friendprofilelist";
    private List<Information> list;
    private Information userInformation;
    private HashMap<String,List<CommunicationData>> hashmap;
    private List<PayData> payDataList;
    private List<InviteRecord> inviteRecordList;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private MainActivityFragmentAdapter adapter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        BaseActivity.finishAll();
        BaseActivity.addActivity(this);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        fragmentManager=getSupportFragmentManager();
        final DataList dataList = (DataList) getIntent().getSerializableExtra(SER_KEY);
        list= dataList.getList();
        userInformation=dataList.getUserInformation();
        hashmap=dataList.getHashMap();
        payDataList=dataList.getPayData();
        inviteRecordList=dataList.getInviteRecord();
        adapter=new MainActivityFragmentAdapter(fragmentManager,this,list,userInformation);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        FriendFragment friendFragment= adapter.getFriendFragment();
        FriendRecyclerViewAdapter friendRecyclerViewAdapter=friendFragment.getAdapter(this,list);
        friendRecyclerViewAdapter.setListener(new FriendRecyclerViewAdapter.CommunicationOnClickListener() {
            @Override
            public void onCommunicateClicked(View v,String username) {
                Intent intent=new Intent(MainActivity.this,CommunicationRecordActivity.class);
                List<CommunicationData> list1=hashmap.get(username);
                DataList dataList1=new DataList(list1);
                Bundle bundle=new Bundle();
                bundle.putSerializable(SER_KEY,dataList1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        friendRecyclerViewAdapter.setSendOnClickListener(new FriendRecyclerViewAdapter.SendOnClickListener() {
            @Override
            public void onSendClicked(View v, String username) {
                Intent intent=new Intent(MainActivity.this,SendPictureActivity.class);
                intent.putExtra(SER_KEY,username);
                startActivity(intent);
            }
        });
        UserFragment userFragment=adapter.getUserFragment();
        userFragment.setPayRecordListener(new UserFragment.PayRecordListener() {
            @Override
            public void onPayRecordClicked() {
                Intent intent=new Intent(MainActivity.this,PayRecordActivity.class);
                DataList dataList1=new DataList();
                dataList1.setPayData(payDataList);
                Bundle bundle=new Bundle();
                bundle.putSerializable(SER_KEY,dataList1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        userFragment.setInviteRecordListener(new UserFragment.InviteRecordListener() {
            @Override
            public void onInviteRecordClicked() {
                Intent intent=new Intent(MainActivity.this,InviteRecordActivity.class);
                DataList dataList1=new DataList();
                dataList1.setInviteRecord(inviteRecordList);
                Bundle bundle=new Bundle();
                bundle.putSerializable(SER_KEY,dataList1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
