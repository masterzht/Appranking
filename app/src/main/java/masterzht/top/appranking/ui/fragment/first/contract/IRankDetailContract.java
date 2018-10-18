package masterzht.top.appranking.ui.fragment.first.contract;

import java.util.List;

import masterzht.top.appranking.base.contract.IBaseContract;
import masterzht.top.appranking.model.bean.novel.RankDetailBean;

public interface IRankDetailContract {

    interface View extends IBaseContract.IBaseView {
        void showRankDetail(List<RankDetailBean.RankingBean.BooksBean> booksBeans,int loadmoretimes );
    }

    interface Presenter<V> extends IBaseContract.IBasePresenter<V> {
        void getRankDetail(String _id,int loadmoretimes);
    }
}
