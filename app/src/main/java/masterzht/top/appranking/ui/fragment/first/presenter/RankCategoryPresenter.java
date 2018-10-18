package masterzht.top.appranking.ui.fragment.first.presenter;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import masterzht.top.appranking.base.RxPresenter;
import masterzht.top.appranking.model.api.ApiManager;
import masterzht.top.appranking.model.api.INovelApi;
import masterzht.top.appranking.model.bean.novel.RankCategoryBean;
import masterzht.top.appranking.ui.fragment.first.RankCategoryFragment;
import masterzht.top.appranking.ui.fragment.first.contract.IRankCategoryContract;

public class RankCategoryPresenter extends RxPresenter<IRankCategoryContract.View> implements IRankCategoryContract.Presenter<IRankCategoryContract.View>  {

    private IRankCategoryContract.View mView;

    public RankCategoryPresenter(IRankCategoryContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getRankCategory() {

        INovelApi iNovelApi = ApiManager.getInstance().getiNovelApi();
        Flowable<RankCategoryBean> flowable = iNovelApi.getRankCategory();
        addSubscrebe(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<RankCategoryBean>() {
                            @Override
                            public void accept(RankCategoryBean rankCategoryBean) throws Exception {
                                mView.showRankCategory(rankCategoryBean);
                                ((RankCategoryFragment)mView).getmRecyclerView().refreshComplete();
                            }
                        }

                )
        );

    }


}
