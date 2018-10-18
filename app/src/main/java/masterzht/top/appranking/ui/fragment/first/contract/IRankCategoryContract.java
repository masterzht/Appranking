package masterzht.top.appranking.ui.fragment.first.contract;


import masterzht.top.appranking.base.contract.IBaseContract;
import masterzht.top.appranking.model.bean.novel.RankCategoryBean;

public interface IRankCategoryContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IBaseContract.IBaseView {
        void showRankCategory(RankCategoryBean rankCategoryBeans );
    }

    interface Presenter<V> extends IBaseContract.IBasePresenter<V> {
        void getRankCategory();
    }
}
