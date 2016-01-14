package Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hawx.project_1.R;

import java.util.List;

import Data.InviteRecord;

/**
 * Created by Administrator on 2016/1/14.
 */
public class InviteRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<InviteRecord> inviteRecordList;
    public InviteRVAdapter(Context context,List<InviteRecord> inviteRecordList){
        this.context=context;
        this.inviteRecordList=inviteRecordList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.invite_item,parent,false);
        return new InviteVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InviteVH inviteVH= (InviteVH) holder;
        InviteRecord inviteRecord=inviteRecordList.get(position);
        inviteVH.userid.setText(inviteRecord.getUserid());;
        inviteVH.time.setText(inviteRecord.getTime());
        inviteVH.state.setText(inviteRecord.getIfSuccessed());
    }

    @Override
    public int getItemCount() {
        if(inviteRecordList!=null){
            return inviteRecordList.size();
        }
        return 0;
    }
    class InviteVH extends RecyclerView.ViewHolder {
        private TextView userid;
        private TextView time;
        private TextView state;
        public InviteVH(View itemView) {
            super(itemView);
            userid= (TextView) itemView.findViewById(R.id.userid);
            time= (TextView) itemView.findViewById(R.id.time);
            state= (TextView) itemView.findViewById(R.id.state);
        }
    }
}
