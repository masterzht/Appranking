package masterzht.top.appranking.ui.fragment.first;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import masterzht.top.appranking.R;
import masterzht.top.appranking.base.fragmentation.BaseMainFragment;
import masterzht.top.appranking.ui.adapter.FIrstTabFragmentAdapter;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstTabFragment extends SupportFragment {

    private TabLayout mTab;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private String[] firstTabTitle = new String[]{"小说", "音乐", "应用"};


    public static FirstTabFragment newInstance() {
        Bundle args = new Bundle();

        FirstTabFragment fragment = new FirstTabFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);




        //mToolbar.setTitle(R.string.discover);
        //TabLayout标题设置
        for (int i = 0; i < firstTabTitle.length; i++) {
            mTab.addTab(mTab.newTab().setText(firstTabTitle[i]));
        }

        mViewPager.setAdapter(new FIrstTabFragmentAdapter(getChildFragmentManager(), firstTabTitle[0], firstTabTitle[1], firstTabTitle[2]));

        mTab.setupWithViewPager(mViewPager);

    }





}
