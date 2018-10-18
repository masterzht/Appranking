package masterzht.top.appranking.ui.fragment.first;


import android.app.Activity;
import android.arch.lifecycle.ReportFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import masterzht.top.appranking.App;
import masterzht.top.appranking.R;
import masterzht.top.appranking.base.fragmentation.BaseMainFragment;
import masterzht.top.appranking.model.bean.novel.RankCategoryBean;
import masterzht.top.appranking.ui.activity.ExtendsActivity;
import masterzht.top.appranking.ui.fragment.MainFragment;
import masterzht.top.appranking.ui.fragment.first.adapter.RankCategoryAdapter;
import masterzht.top.appranking.ui.fragment.first.adapter.TopMovieAdapter;
import masterzht.top.appranking.ui.fragment.first.contract.IRankCategoryContract;
import masterzht.top.appranking.ui.fragment.first.presenter.RankCategoryPresenter;
import masterzht.top.appranking.ui.fragment.fourth.FourthTabFragment;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankCategoryFragment extends SupportFragment implements IRankCategoryContract.View {

    private XRecyclerView mRecyclerView;
    private ArrayList<RankCategoryBean.RankingsBean> listData;
    private RankCategoryAdapter rankCategoryAdapter;
    private RankCategoryPresenter rankCategoryPresenter;


    public static RankCategoryFragment newInstance() {
        Bundle args = new Bundle();
        RankCategoryFragment fragment = new RankCategoryFragment();
        RankCategoryPresenter rankCategoryPresenter = new RankCategoryPresenter(fragment);
        fragment.setRankCategoryPresenter(rankCategoryPresenter);
        fragment.setArguments(args);
        return fragment;
    }

    public void setRankCategoryPresenter(RankCategoryPresenter rankCategoryPresenter) {
        this.rankCategoryPresenter = rankCategoryPresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank_category, container, false);


        initData();

        //Recyclerview
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        rankCategoryAdapter = new RankCategoryAdapter(getContext(), listData);
        /*rankCategoryAdapter.setOnItemClickListener(
                new RankCategoryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(App.getContext(), "啦啦啦啦"+position, Toast.LENGTH_SHORT).show();
                    }
                }
        );*/
        //rankCategoryAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(rankCategoryAdapter);

        rankCategoryAdapter.setOnItemClickListener(new RankCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(App.getContext(), "啦啦啦啦" + position, Toast.LENGTH_SHORT).show();
                //((MainFragment) getParentFragment().getParentFragment()).startBrotherFragment(FourthTabFragment.newInstance());
                //((FirstTabFragment)getParentFragment()).start(RankDetailFragment.newInstance(listData.get(position).get_id()));

                //start(RankDetailFragment.newInstance(listData.get(position).get_id()),SupportFragment.SINGLETASK);

                Intent intent=new Intent(getContext(), ExtendsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",listData.get(position).get_id());
                bundle.putString("toolbar_title",listData.get(position).getTitle());
                intent.putExtras(bundle);
                startActivity(intent);




            }
        });


        initRefresh();


        return view;
    }

    private void initRefresh() {
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listData.clear();
                        rankCategoryPresenter.getRankCategory();
                        mRecyclerView.refreshComplete();


                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                mRecyclerView.setNoMore(true);

            }


        });
    }

    private void initData() {
        /*listData = new ArrayList<String>();
        for(int i = 0; i < 15 ;i++){
            listData.add("item" + i);
        }*/
        listData = new ArrayList<RankCategoryBean.RankingsBean>();
        rankCategoryPresenter.getRankCategory();
    }

    @Override
    public void showRankCategory(RankCategoryBean rankCategoryBeans) {

        /*for (int i = 0; i < rankCategoryBeans.getRankings().size(); i++) {
            listData.add(rankCategoryBeans.getRankings().get(i));
        }*/
        rankCategoryBeans.getRankings().stream().forEach(s ->listData.add(s));

        rankCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void complete() {

    }



}
