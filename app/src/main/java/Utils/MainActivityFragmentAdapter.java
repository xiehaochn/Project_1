package Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import Data.Information;
import Fragments.FriendFragment;
import Fragments.UserFragment;

/**
 * Created by Administrator on 2016/1/13.
 */
public class MainActivityFragmentAdapter extends FragmentPagerAdapter {
    private List<Information> list;
    private Information userInformation;
    private FriendFragment friendFragment;
    private final int PageCount=2;
    private final String[] tabTitle=new String[]{"Friends","User"};
    private Context context;
    private UserFragment userFragment;

    public MainActivityFragmentAdapter(FragmentManager fm, Context context, List<Information> list, Information userInformation) {
        super(fm);
        this.context=context;
        this.list=list;
        this.userInformation=userInformation;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
                friendFragment=FriendFragment.newInstance();
                friendFragment.setList(list);
                return friendFragment;
            }
        if(position==1){
                userFragment=UserFragment.newInstance();
                userFragment.setUserInformation(userInformation);
                return userFragment;
            }
        return null;
    }

    @Override
    public int getCount() {
        return PageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }

    public FriendFragment getFriendFragment() {
        if(friendFragment==null){
            friendFragment=FriendFragment.newInstance();

        }
        return friendFragment;
    }

    public UserFragment getUserFragment() {
        if(userFragment==null){
            userFragment=UserFragment.newInstance();
        }
        return userFragment;
    }
}
