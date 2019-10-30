package masterzht.top.appranking.ui.fragment.first;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import masterzht.top.appranking.R;
import masterzht.top.appranking.model.bean.douban.TopMovieBean;
import masterzht.top.appranking.ui.fragment.first.adapter.TopMovieAdapter;
import masterzht.top.appranking.ui.fragment.first.contract.ITopMovieContract;
import masterzht.top.appranking.ui.fragment.first.presenter.TopMoviePresenter;
import me.yokeyword.fragmentation.SupportFragment;

/**ay
 * Top250界面暂时考虑用Xrecyclerview实现
 * A simple {@link Fragment} subclass.
 */
public class TopMovieFragment extends SupportFragment implements ITopMovieContract.View {


    public XRecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    private XRecyclerView mRecyclerView;
    private TopMovieAdapter mAdapter;
    private ArrayList<TopMovieBean.SubjectsGsonBean> listData;
    private int refreshTime = 0;
    private int times = 0;
    private TopMoviePresenter topMoviePresenter;


    public static TopMovieFragment newInstance() {
        Bundle args = new Bundle();

        TopMovieFragment fragment = new TopMovieFragment();
        TopMoviePresenter topMoviePresenter=new TopMoviePresenter(fragment);
        fragment.setTopMoviePresenter(topMoviePresenter);
        fragment.setArguments(args);
        return fragment;

    }

    public void setTopMoviePresenter(TopMoviePresenter topMoviePresenter) {
        this.topMoviePresenter = topMoviePresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_top_movie, container, false);

        listData=new ArrayList<TopMovieBean.SubjectsGsonBean>();

        // Toolbar
        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        //Recyclerview
        mRecyclerView = (XRecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new TopMovieAdapter(listData);
        mRecyclerView.setAdapter(mAdapter);

        //initData();

        //initRefresh();

        return view;
    }

    private void initRefresh() {
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                       //refresh data here
                refreshTime ++;
                times = 0;
                listData.clear();
                topMoviePresenter.getTopMovie(0,10);

            }

            @Override
            public void onLoadMore() {
                            topMoviePresenter.getTopMovie(listData.size(),10);
            }

        });
    }


    /**
     * 初始化加载数据
     */
    private void initData() {
        topMoviePresenter.getTopMovie(0,10);
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


    @Override
    public void showTopMovie(List<TopMovieBean.SubjectsGsonBean> subjectsGsonBean, int start, int count) {
        for(int i=0;i<subjectsGsonBean.size();i++){
            listData.add(subjectsGsonBean.get(i));
        }

        mAdapter.notifyDataSetChanged();
    }
}
