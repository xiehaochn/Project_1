package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawx.project_1.R;

import Data.Information;

/**
 * Created by Administrator on 2016/1/13.
 */
public class UserFragment extends Fragment {
    private PayRecordListener payRecordListener;
    private InviteRecordListener inviteRecordListener;
    private static UserFragment userFragment;
    private Information userInformation;
    public static UserFragment newInstance() {
        if(userFragment!=null){
            return userFragment;
        }else {
            userFragment = new UserFragment();
            return userFragment;
        }
    }
    public void setUserInformation(Information userInformation){
        this.userInformation=userInformation;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.user_fragment,container,false);
        TextView name= (TextView) view.findViewById(R.id.name);
        TextView phone= (TextView) view.findViewById(R.id.phone);
        TextView nickname1= (TextView) view.findViewById(R.id.nickname1);
        TextView nickname2= (TextView) view.findViewById(R.id.nickname2);
        TextView integration= (TextView) view.findViewById(R.id.integration);
        ImageView avatar= (ImageView) view.findViewById(R.id.avatar);
        name.setText(userInformation.getName());
        phone.setText(userInformation.getPhone());
        nickname1.setText(userInformation.getNickname1());
        nickname2.setText(userInformation.getNickname2());
        integration.setText(userInformation.getIntegration());
        avatar.setImageResource(R.mipmap.ic_launcher);
        Button payrecord= (Button) view.findViewById(R.id.payrecord);
        Button inviterecord= (Button) view.findViewById(R.id.inviterecord);
        payrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(payRecordListener!=null){
                    payRecordListener.onPayRecordClicked();
                }
            }
        });
        inviterecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inviteRecordListener!=null){
                    inviteRecordListener.onInviteRecordClicked();
                }
            }
        });
        return view;
    }
    public interface PayRecordListener{
        void onPayRecordClicked();
    }
    public interface InviteRecordListener{
        void onInviteRecordClicked();
    }

    public void setPayRecordListener(PayRecordListener payRecordListener) {
        this.payRecordListener = payRecordListener;
    }

    public void setInviteRecordListener(InviteRecordListener inviteRecordListener) {
        this.inviteRecordListener = inviteRecordListener;
    }
}
