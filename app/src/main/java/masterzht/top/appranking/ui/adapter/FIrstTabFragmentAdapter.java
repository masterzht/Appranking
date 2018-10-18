package masterzht.top.appranking.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import masterzht.top.appranking.ui.fragment.first.RankCategoryFragment;
import masterzht.top.appranking.ui.fragment.first.RankDetailFragment;
import masterzht.top.appranking.ui.fragment.fourth.FourthTabFragment;
import masterzht.top.appranking.ui.fragment.second.SecondTabFragment;
import masterzht.top.appranking.ui.fragment.third.ThirdTabFragment;

/**
 * Created by masterzht on 2018/9/25.
 */

public class FIrstTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public FIrstTabFragmentAdapter(FragmentManager fm, String...titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return RankCategoryFragment.newInstance();

        } else if (position==1){
            return SecondTabFragment.newInstance();
        }else {
            return ThirdTabFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
