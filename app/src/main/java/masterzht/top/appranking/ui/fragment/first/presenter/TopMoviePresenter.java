package masterzht.top.appranking.ui.fragment.first.presenter;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import masterzht.top.appranking.base.RxPresenter;
import masterzht.top.appranking.model.api.ApiManager;
import masterzht.top.appranking.model.api.IDoubanApi;
import masterzht.top.appranking.model.bean.douban.TopMovieBean;
import masterzht.top.appranking.ui.fragment.first.TopMovieFragment;
import masterzht.top.appranking.ui.fragment.first.contract.ITopMovieContract;

/**
 * Created by masterzht on 2018/9/26.
 */

public class TopMoviePresenter extends RxPresenter<ITopMovieContract.View> implements ITopMovieContract.Presenter<ITopMovieContract.View> {

    private ITopMovieContract.View mView;

    public TopMoviePresenter(ITopMovieContract.View mView) {
        this.mView = mView;

    }

    @Override
    public void getTopMovie(final int start, final int count) {
        IDoubanApi iDoubanApi = ApiManager.getInstance().getIDoubanApi();
        Flowable<TopMovieBean> flowable = iDoubanApi.getTopMovie(start, count);
        addSubscrebe(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<TopMovieBean>() {
                            @Override
                            public void accept(TopMovieBean topMovieBean) throws Exception {
                                mView.showTopMovie(topMovieBean.getSubjects(), start, count);
                                if (start==0){
                                ((TopMovieFragment)mView).getmRecyclerView().refreshComplete();
                                }
                                else {
                                    ((TopMovieFragment)mView).getmRecyclerView().loadMoreComplete();
                                }
                            }
                        }

                )
        );


    }

}
