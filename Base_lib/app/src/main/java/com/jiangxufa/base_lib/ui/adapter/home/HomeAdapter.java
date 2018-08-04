package com.jiangxufa.base_lib.ui.adapter.home;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jiangxufa.base_lib.R;
import com.jiangxufa.base_lib.RefreshFragment;
import com.jiangxufa.base_lib.ui.home.ChaseBandumiFragment;
import com.jiangxufa.base_lib.ui.home.DisconverFragment;
import com.jiangxufa.base_lib.ui.home.DynamicFragment;
import com.jiangxufa.base_lib.ui.home.RecommendFragment;
import com.jiangxufa.base_lib.ui.home.RegionFragment;
import com.jiangxufa.fa_lib.utils.AppUtils;

/**
 * 创建时间：2018/7/3
 * 编写人：lenovo
 * 功能描述：
 */

public class HomeAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private Fragment[] mFragment;

    public HomeAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    private void init() {
        mTitles = AppUtils.getStringArray(R.array.main_title);
        mFragment = new Fragment[mTitles.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragment[position] == null) {
            switch (position) {
                case 0:
//                    mFragment[position] = LiveFragment.newInstance();
                    mFragment[position] = RefreshFragment.newInstance();
                    break;
                case 1:
                    mFragment[position] = RecommendFragment.newInstance();
                    break;
                case 2:
                    mFragment[position]= ChaseBandumiFragment.newInstance();
                    break;
                case 3:
                    mFragment[position]= RegionFragment.newInstance();
                    break;
                case 4:
                    mFragment[position]= DynamicFragment.newInstance();
                    break;
                case 5:
                    mFragment[position]= DisconverFragment.newInstance();
                    break;
                default:
                    break;
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
