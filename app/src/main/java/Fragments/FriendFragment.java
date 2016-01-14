package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hawx.project_1.R;

import java.util.List;

import Data.Information;
import Utils.FriendRecyclerViewAdapter;

/**
 * Created by Administrator on 2016/1/13.
 */
public class FriendFragment extends Fragment {
    private static FriendFragment friendFragment;
    private List<Information> list;
    private FriendRecyclerViewAdapter adapter;
    public static FriendFragment newInstance() {
        if(friendFragment!=null){
            return friendFragment;
        }else {
            friendFragment = new FriendFragment();
            return friendFragment;
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void setList(List<Information> list){
        this.list=list;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.friend_fragment,container,false);
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        if(adapter==null) {
            adapter = new FriendRecyclerViewAdapter(getContext(), list);
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
    public FriendRecyclerViewAdapter getAdapter(Context context,List<Information> list) {
        if(adapter==null){
            adapter=new FriendRecyclerViewAdapter(context,list);
        }
            return adapter;
    }
}
