package Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hawx.project_1.R;

import java.util.List;

import Data.PayData;

/**
 * Created by Administrator on 2016/1/14.
 */
public class PayRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<PayData> payDataList;
    private Context context;
    public PayRVAdapter(Context context,List<PayData> payDataList){
        this.context=context;
        this.payDataList=payDataList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pay_item,parent,false);
        return new PayVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PayVH payVH= (PayVH) holder;
        PayData payData=payDataList.get(position);
        payVH.count.setText(payData.getCount());
        payVH.time.setText(payData.getTime());
    }

    @Override
    public int getItemCount() {
        if(payDataList!=null){
            return  payDataList.size();
        }
        return 0;
    }
    class PayVH extends RecyclerView.ViewHolder {
        private TextView count;
        private TextView time;
        public PayVH(View itemView) {
            super(itemView);
            count= (TextView) itemView.findViewById(R.id.count);
            time= (TextView) itemView.findViewById(R.id.time);
        }
    }
}
