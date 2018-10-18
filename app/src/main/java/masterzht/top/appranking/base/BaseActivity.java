package masterzht.top.appranking.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import masterzht.top.appranking.App;
import masterzht.top.appranking.base.fragmentation.MySupportActivity;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by masterzht on 2018/9/23.
 */

public abstract class BaseActivity extends SupportActivity {



    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        activity = this;
        App.getInstance().addActivity(this);
        init();
    }



    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    private void init() {
        initData();
        initEvents();
    }

    /***
     * 初始化事件（监听事件等事件绑定）
     */
    protected void initEvents() {
    }

    /**
     * 绑定数据
     */
    protected void initData() {
    }

    /**
     * activity退出时将activity移出栈
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        App.getInstance().removeActivity(this);
    }
}
