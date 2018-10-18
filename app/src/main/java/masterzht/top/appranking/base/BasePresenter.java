package masterzht.top.appranking.base;

import android.util.Log;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by masterzht on 2018/9/22.
 */

public  class BasePresenter<V> implements IBasePresenter<V> {

    /**
     * 当内存不足释放内存
     */
    protected WeakReference<V> mViewRef; // view 的弱引用



    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);

    }

    @Override
    public void detachView() {
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
            Log.i("BasePresenter","已经GC...");
        }
    }

    /**
     * 获取view的方法
     *
     * @return 当前关联的view
     */
    public V getView() {
        return mViewRef.get();
    }





    protected CompositeDisposable mCompositeDisposable;

    //RxJava取消注册，以避免内存泄露
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }
}
