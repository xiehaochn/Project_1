package com.hawx.project_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Utils.CheckEditText;
import Utils.RequestManager;

/**
 * Created by Administrator on 2016/1/13.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button register_confirm;
    private Button back;
    private Context context;
    private RequestManager requestManager;
    private EditText account;
    private EditText password;
    private EditText confirm;
    private EditText inviter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        context=this;
        BaseActivity.addActivity(this);
        toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        register_confirm= (Button) findViewById(R.id.register_confirm);
        register_confirm.setOnClickListener(this);
        back= (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
        requestManager=RequestManager.getRequestManager(context);
        account= (EditText) findViewById(R.id.account);
        password= (EditText) findViewById(R.id.password);
        confirm= (EditText) findViewById(R.id.confirm);
        inviter= (EditText) findViewById(R.id.inviter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_confirm:{
                if(CheckEditText.isEmpty(account)||CheckEditText.isEmpty(password)||CheckEditText.isEmpty(confirm)){
                    Toast.makeText(this,"Input Error",Toast.LENGTH_SHORT).show();
                }else if(!CheckEditText.isEqual(password,confirm)){
                    Toast.makeText(this,"Please confirm your password",Toast.LENGTH_SHORT).show();
                }else {
                    String account_string=account.getText().toString();
                    String password_string=password.getText().toString();
                    String inviter_string=inviter.getText().toString();
                    requestManager.sendRegisterRequest(account_string, password_string, inviter_string, new RequestManager.RegisterListener() {
                        @Override
                        public void registerFinished(boolean successed) {
                            if(successed){
                                Intent intent = new Intent(context,MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(context,"Register failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            }
            case R.id.back:{
                finish();
                BaseActivity.removeActivity(this);
                break;
            }
            default:break;
        }
    }
}
