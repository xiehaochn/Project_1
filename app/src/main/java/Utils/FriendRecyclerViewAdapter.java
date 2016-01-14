package Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawx.project_1.R;

import java.util.List;

import Data.Information;

/**
 * Created by Administrator on 2016/1/13.
 */
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Information> list;
    private CommunicationOnClickListener listener;
    private SendOnClickListener sendOnClickListener;
    public FriendRecyclerViewAdapter(Context context,List<Information> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.friend_item,parent,false);
        return new FriendVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendVH friendVH= (FriendVH) holder;
        final Information information =list.get(position);
        friendVH.name.setText(information.getName());
        friendVH.nickname1.setText(information.getNickname1());
        friendVH.nickname2.setText(information.getNickname2());
        friendVH.phone.setText(information.getPhone());
        friendVH.intro.setText(information.getIntro());
        friendVH.avatar.setImageResource(R.mipmap.ic_launcher);
        friendVH.communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onCommunicateClicked(v,information.getUsername());
                }
            }
        });
        friendVH.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendOnClickListener!=null){
                    sendOnClickListener.onSendClicked(v,information.getUsername());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FriendVH extends RecyclerView.ViewHolder {
        public ImageView avatar;
        public TextView name;
        public TextView phone;
        public TextView nickname1;
        public TextView nickname2;
        public TextView intro;
        public Button communication;
        public Button send;
        public FriendVH(View itemView) {
            super(itemView);
            avatar= (ImageView) itemView.findViewById(R.id.avatar);
            name= (TextView) itemView.findViewById(R.id.name);
            phone= (TextView) itemView.findViewById(R.id.phone);
            nickname1= (TextView) itemView.findViewById(R.id.nickname1);
            nickname2= (TextView) itemView.findViewById(R.id.nickname2);
            intro= (TextView) itemView.findViewById(R.id.intro);
            communication= (Button) itemView.findViewById(R.id.communication);
            send= (Button) itemView.findViewById(R.id.sendpicture);
        }
    }
    public interface CommunicationOnClickListener{
        void onCommunicateClicked(View v,String username);
    }

    public void setListener(CommunicationOnClickListener listener) {
        this.listener = listener;
    }
    public interface SendOnClickListener{
        void onSendClicked(View v,String username);
    }

    public void setSendOnClickListener(SendOnClickListener sendOnClickListener) {
        this.sendOnClickListener = sendOnClickListener;
    }
}
