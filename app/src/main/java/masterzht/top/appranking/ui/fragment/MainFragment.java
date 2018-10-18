package masterzht.top.appranking.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import masterzht.top.appranking.R;
import masterzht.top.appranking.base.fragmentation.MySupportFragment;
import masterzht.top.appranking.ui.fragment.first.FirstTabFragment;
import masterzht.top.appranking.ui.fragment.first.RankDetailFragment;
import masterzht.top.appranking.ui.fragment.first.TopMovieFragment;
import masterzht.top.appranking.ui.fragment.fourth.FourthTabFragment;
import masterzht.top.appranking.ui.fragment.second.SecondTabFragment;
import masterzht.top.appranking.ui.fragment.third.ThirdTabFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends SupportFragment{


    // collections
    private List<Fragment> fragments;// used for ViewPager adapter
    BottomNavigationViewEx bnve ;
    ViewPager vp;
    FloatingActionButton fab;
    private SupportFragment[] mFragments = new SupportFragment[4];
    private VpAdapter adapter;
    public static MainFragment newInstance(){
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_main, container, false);

        bnve = (BottomNavigationViewEx) view.findViewById(R.id.bnve);
        vp=(ViewPager)view.findViewById(R.id.vp);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        initData();
        initView();
        initEvent();
        return view;
    }

    /**
     * create fragments
     */
    private void initData() {
        fragments = new ArrayList<>(4);

        // create music fragment and add it
        FirstTabFragment musicFragment = new FirstTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", "FirstTabFragment");
        musicFragment.setArguments(bundle);

        // create backup fragment and add it
       /* SecondTabFragment backupFragment = new SecondTabFragment();
        bundle = new Bundle();
        bundle.putString("title", "SecondTabFragment");
        backupFragment.setArguments(bundle);*/
       TopMovieFragment backupFragment=TopMovieFragment.newInstance();


        // create friends fragment and add it
        ThirdTabFragment favorFragment = new ThirdTabFragment();
        bundle = new Bundle();
        bundle.putString("title", "ThirdTabFragment");
        favorFragment.setArguments(bundle);
        //TopMovieFragment favorFragment = TopMovieFragment.newInstance();


        FourthTabFragment visibilityFragment = new FourthTabFragment();
        bundle = new Bundle();
        bundle.putString("title", "FourthTabFragment");
        visibilityFragment.setArguments(bundle);
        //RankDetailFragment visibilityFragment=RankDetailFragment.newInstance("548e40f2c58cff632353e730");


        // add to fragments for adapter
        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(favorFragment);
        fragments.add(visibilityFragment);
    }

    /**
     * change BottomNavigationViewEx style
     */
    private void initView() {
        bnve.enableItemShiftingMode(false);
        bnve.enableShiftingMode(false);
        bnve.enableAnimation(false);

        // set adapter
        adapter = new VpAdapter(getActivity().getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item_topmovie of view pager when click bottom nav item_topmovie
       bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                switch (item.getItemId()) {
                    case R.id.i_music:
                        position = 0;
                        break;
                    case R.id.i_backup:
                        position = 1;
                        break;
                    case R.id.i_favor:
                        position = 2;
                        break;
                    case R.id.i_visibility:
                        position = 3;
                        break;
                    case R.id.i_empty: {
                        return false;
                    }
                }
                if (previousPosition != position) {
                    vp.setCurrentItem(position, false);
                    previousPosition = position;
                    Log.i("开始", "-----bnve-------- previous item_topmovie:" + bnve.getCurrentItem() + " current item_topmovie:" + position + " ------------------");
                }

                return true;
            }
        });

        // set listener to change the current checked item_topmovie of bottom nav when scroll view pager
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("啦啦啦", "-----ViewPager-------- previous item_topmovie:" + bnve.getCurrentItem() + " current item_topmovie:" + position + " ------------------");
                if (position >= 2)// 2 is center
                    position++;// if page is 2, need set bottom item_topmovie to 3, and the same to 3 -> 4
                bnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // center item_topmovie click listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Center", Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }



    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }

}
