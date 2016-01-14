package Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawx.project_1.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2016/1/14.
 */
public class PictureRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Bitmap> list;
    private List<String> list_time;
    public PictureRVAdapter(Context context,List<Bitmap> list,List<String> list_time){
        this.list=list;
        this.context=context;
        this.list_time=list_time;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.picture_item,parent,false);
        return new PictureVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PictureVH pictureVH= (PictureVH) holder;
        pictureVH.imageView.setImageBitmap(list.get(position));
        pictureVH.time.setText(list_time.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class PictureVH extends RecyclerView.ViewHolder {
        private TextView time;
        private ImageView imageView;
        public PictureVH(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.image);
            time= (TextView) itemView.findViewById(R.id.time);
        }
    }

    public void setList(List<Bitmap> list) {
        this.list = list;
    }

    public void setList_time(List<String> list_time) {
        this.list_time = list_time;
    }
}
