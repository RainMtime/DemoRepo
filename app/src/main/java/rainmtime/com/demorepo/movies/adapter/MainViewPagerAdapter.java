package rainmtime.com.demorepo.movies.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by chunyu on 2018/2/9 下午3:00.
 * Email: 746431278@qq.com
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ArrayList<String> mFragmentsTabsTitle = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm, @NonNull ArrayList<Fragment> fragments, @NonNull ArrayList<String> fragmentsTitles) {
        super(fm);

        if (mFragments.size() == mFragmentsTabsTitle.size()) {
            mFragments = fragments;
            mFragmentsTabsTitle = fragmentsTitles;
        } else {
            throw new IllegalStateException("tilte size not equal fragment size ");
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTabsTitle.get(position);
    }
}
