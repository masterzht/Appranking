package masterzht.top.appranking.base;

/**
 * Created by masterzht on 2018/9/22.
 */

public interface IBasePresenter<V>  {


    void attachView(V view);

    void detachView();

}
