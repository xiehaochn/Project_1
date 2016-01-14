package Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hawx.project_1.R;

import java.util.List;

import Data.CommunicationData;

/**
 * Created by Administrator on 2016/1/14.
 */
public class CommunicationRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CommunicationData> communicationDataList;
    public CommunicationRVAdapter(Context context,List<CommunicationData> communicationDataList) {
        this.context=context;
        this.communicationDataList=communicationDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.communication_item,parent,false);
        return new CommunicationDataVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommunicationDataVH communicationDataVH= (CommunicationDataVH) holder;
        CommunicationData communicationData=communicationDataList.get(position);
        communicationDataVH.date.setText(communicationData.getYear() + "." + communicationData.getMonth() + "." + communicationData.getDay());
        communicationDataVH.time.setText(communicationData.getHour() + "hours" + communicationData.getMinite() + "minutes");
    }

    @Override
    public int getItemCount() {
        if(communicationDataList!=null) {
            return communicationDataList.size();
        }else{
            return 0;
        }
    }
    class CommunicationDataVH extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView time;
        public CommunicationDataVH(View itemView) {
            super(itemView);
            date= (TextView) itemView.findViewById(R.id.date);
            time= (TextView) itemView.findViewById(R.id.time);
        }
    }
}
