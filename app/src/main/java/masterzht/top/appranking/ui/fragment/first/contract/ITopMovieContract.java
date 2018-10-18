package masterzht.top.appranking.ui.fragment.first.contract;

import java.util.List;

import masterzht.top.appranking.base.contract.IBaseContract;
import masterzht.top.appranking.model.bean.douban.TopMovieBean;

/**
 * Created by masterzht on 2018/9/23.
 */

public interface ITopMovieContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IBaseContract.IBaseView {
        void showTopMovie(List<TopMovieBean.SubjectsGsonBean> subjectsGsonBean , int start, int count);
    }

    interface Presenter<V> extends IBaseContract.IBasePresenter<V> {
        void getTopMovie(int start, int count);
    }
}
