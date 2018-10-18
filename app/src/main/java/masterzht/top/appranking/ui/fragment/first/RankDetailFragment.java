package masterzht.top.appranking.ui.fragment.first;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import masterzht.top.appranking.R;
import masterzht.top.appranking.base.fragmentation.BaseMainFragment;
import masterzht.top.appranking.model.bean.novel.RankDetailBean;
import masterzht.top.appranking.ui.fragment.first.adapter.RankCategoryAdapter;
import masterzht.top.appranking.ui.fragment.first.adapter.RankDetailAdapter;
import masterzht.top.appranking.ui.fragment.first.contract.IRankDetailContract;
import masterzht.top.appranking.ui.fragment.first.presenter.RankDetailPresenter;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankDetailFragment extends SupportFragment implements IRankDetailContract.View {


    private XRecyclerView mRecyclerView;
    private RankDetailAdapter rankDetailAdapter;
    private List<RankDetailBean.RankingBean.BooksBean> listData;
    private RankDetailPresenter rankDetailPresenter;
    private String _id;
    private int loadmoretimes=0;




    public static RankDetailFragment newInstance(String _id) {
        Bundle args = new Bundle();

        args.putString("_id", _id);

        RankDetailFragment fragment = new RankDetailFragment();
        RankDetailPresenter rankDetailPresenter=new RankDetailPresenter(fragment);
        fragment.setRankDetailPresenter(rankDetailPresenter);
        fragment.setArguments(args);
        return fragment;

    }

    public void setRankDetailPresenter(RankDetailPresenter rankDetailPresenter) {
        this.rankDetailPresenter = rankDetailPresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_rank_detail, container, false);
        listData=new ArrayList<RankDetailBean.RankingBean.BooksBean>();


        initData();



        //Recyclerview
        mRecyclerView = (XRecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        rankDetailAdapter=new RankDetailAdapter(listData);
        mRecyclerView.setAdapter(rankDetailAdapter);

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
                loadmoretimes=0;

                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        listData.clear();
                        rankDetailPresenter.getRankDetail(_id,loadmoretimes);
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {

                loadmoretimes++;

                if (loadmoretimes<9) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            rankDetailPresenter.getRankDetail(_id, loadmoretimes);
                            mRecyclerView.loadMoreComplete();

                        }
                    }, 1000);
                }else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            mRecyclerView.setNoMore(true);
                        }
                    }, 1000);
                }



            }


        });
    }

    private void initData() {
        /*listData = new ArrayList<String>();
        for(int i = 0; i < 15 ;i++){
            listData.add("item" + i);
        }*/
        if (getArguments() != null) {
            _id= getArguments().getString("_id");
            rankDetailPresenter.getRankDetail(_id,loadmoretimes);
        }


    }

    @Override
    public void showRankDetail(List<RankDetailBean.RankingBean.BooksBean> rankDetailBean,int loadmoretimes) {

        for(int i=0;i<10;i++){
            listData.add(rankDetailBean.get(10*loadmoretimes+i));
        }

        rankDetailAdapter.notifyDataSetChanged();

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
