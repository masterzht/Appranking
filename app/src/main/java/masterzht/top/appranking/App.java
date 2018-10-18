package masterzht.top.appranking;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.iflytek.cloud.SpeechUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import masterzht.top.appranking.base.BaseActivity;

/**
 * 简单设置一些全局变量,管理activity,获取手机数据等等
 * Created by masterzht on 2018/9/23.
 */

public class App extends Application {
    //创建一个App对象
    private static App instance ;
    public static final App getInstance(){
        return instance;
    }
    // 获取ApplicationContext
    public static Context getContext() {
        return instance;
    }

    private static int mainTid;

    //管理所有的Activities
    private List<BaseActivity> activities;

    @Override
    public void onCreate() {

        SpeechUtility.createUtility(App.this, "appid=" + getString(R.string.app_id));
        // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        // 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
        // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
        // 参数间使用半角“,”分隔。
        // 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符

        // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误
        super.onCreate();
        instance = this;
        //初始化装载所有Activities的集合
        activities = new ArrayList<>();
        //初始化手机的屏幕数据
        getScreenSize();
        //设置本app的线程id
        mainTid = android.os.Process.myTid();
    }

    /**
     * 添加Activity
     */
    public void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    /**
     * 移除Activity
     */
    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }


    //获取手机的基础信息，屏幕宽度，高度。。。
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    /*
    * 获取屏幕数据
    * */
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }






    //初始化数据库的功能

    //夜间模式的基础设置
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    //退出App

    /**
     * 结束当前所有Activity
     */
    public void clearActivities() {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应运程序,关闭所有activity
     * 例如:a-b-c,在actcityC中,想要退出,只能点三次返回键,回桌面只是挂起操作,所以可以通过这个方法直接关闭app
     */
    public void quiteApplication() {
        clearActivities();
        System.exit(0);
    }
}
