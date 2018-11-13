package edu.temple.mybrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private static final String TAG = "ViewPagerAdapter";
    ArrayList<ViewPagerFragment> viewPagerArrayList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        createFragment();
    }

    public void createFragment(){
        ViewPagerFragment vpf = ViewPagerFragment.newInstance(MainActivity.homepage);
        viewPagerArrayList.add(vpf);
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int i) {
        return viewPagerArrayList.get(i);
    }

    @Override
    public int getCount() {
        return viewPagerArrayList.size();
    }

}
