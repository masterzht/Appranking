package masterzht.top.appranking.base.contract;

/**
 * Created by masterzht on 2018/9/25.
 */

public interface IBaseContract {

    interface IBasePresenter<V> {

        void attachView(V view);

        void detachView();
    }

    interface IBaseView {

        /**
         * 请求前显示进度条
         */
        void showProgressDialog();

        /**
         * 请求后隐藏进度条
         */
        void hideProgressDialog();

        /**
         * 请求失败显示错误信息
         * @param error
         */
        void showErrorMsg(String error);

        void complete();

    }
}