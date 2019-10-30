package masterzht.top.appranking.ui.fragment.first.presenter;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import masterzht.top.appranking.base.RxPresenter;
import masterzht.top.appranking.model.api.ApiManager;
import masterzht.top.appranking.model.api.INovelApi;
import masterzht.top.appranking.model.bean.novel.RankDetailBean;
import masterzht.top.appranking.ui.fragment.first.contract.IRankDetailContract;

public class RankDetailPresenter extends RxPresenter<IRankDetailContract.View> implements IRankDetailContract.Presenter<IRankDetailContract.View> {

    private IRankDetailContract.View mView;

    public RankDetailPresenter(IRankDetailContract.View mView) {
        this.mView = mView;
    }


     /*new Subscriber<RankDetailBean>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(RankDetailBean rankDetailBean) {
            mView.showRankDetail(rankDetailBean.getRanking().getBooks(),loadmoretimes);
        }
    }*/
    @Override
    public void getRankDetail(String id,int loadmoretimes) {
        INovelApi iNovelApi = ApiManager.getInstance().getiNovelApi();
        Flowable<RankDetailBean> flowable = iNovelApi.getRankDetail(id);
        addSubscrebe(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                            new Consumer<RankDetailBean>() {
                                @Override
                                public void accept(RankDetailBean rankDetailBean) throws Exception {
                                    mView.showRankDetail(rankDetailBean.getRanking().getBooks(),loadmoretimes);
                                }


                            }
                            ,new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                android.util.Log.i("hhh", "错误是还好还好哈还好还好哈" + throwable.toString());
                            }
                        }

                )
        );

    }
}
