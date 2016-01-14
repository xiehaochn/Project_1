package com.hawx.project_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import Data.InviteRecord;
import Data.PayData;
import Utils.CheckEditText;
import Data.CommunicationData;
import Data.Information;
import Data.DataList;
import Utils.RequestManager;

/**
 * Created by Administrator on 2016/1/13.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    public  final String SER_KEY = "friendprofilelist";
    private Toolbar toolbar;
    private Button register;
    private Button login;
    private EditText account;
    private EditText password;
    private RequestManager requestManager;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        context=this;
        BaseActivity.addActivity(this);
        toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        register= (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        account= (EditText) findViewById(R.id.account);
        password= (EditText) findViewById(R.id.password);
        requestManager=RequestManager.getRequestManager(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:{
                //跳转注册界面
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.login:{
                if(CheckEditText.isEmpty(account)||CheckEditText.isEmpty(password)){
                    Toast.makeText(this,"Input Error",Toast.LENGTH_SHORT).show();
                }else {
                    String account_string=account.getText().toString();
                    String password_string=password.getText().toString();
                    requestManager.sendLoginRequest(account_string, password_string, new RequestManager.LoginListener() {
                        @Override
                        //设置监听
                        public void loginFinished(boolean successed, List<Information> informationList, Information userInformation, HashMap<String,List<CommunicationData>> hashmap, List<PayData> payData, List<InviteRecord> inviteRecord) {
                            if(successed){
                                Intent intent =new Intent(context,MainActivity.class);
                                DataList list=new DataList(informationList,userInformation,hashmap,payData,inviteRecord);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable(SER_KEY,list);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }else {
                                Toast.makeText(context,"Account or Password mistake",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            }
            default:break;
        }
    }
}
