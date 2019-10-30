package masterzht.top.appranking.model.api;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import masterzht.top.appranking.App;
import masterzht.top.appranking.utils.Constant;
import masterzht.top.appranking.utils.network.NetUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by masterzht on 2018/9/23.
 */

public class ApiManager {

    //设置 数据的缓存时间，有效期为两天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control 配置，为 only-if-cache 时，只查询缓存而不会请求服务器， max-stale可以配合设置缓存失效时间
    protected static final String CACHE_CONTROL_CACHE = "only-if-cache, max-stale=" + CACHE_STALE_SEC;
    //查询缓存的Cache-Control配置，为 Cache-Control设置为max-age=0时则不会使用缓存，而是请求服务器
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    /**
     * 单例模式的 OKhttpClient,以及Retrifit引擎类
     */
    private static OkHttpClient mOkHttpClient;

    /**
     * 初始化构造 okhttp客户端
     */
    private static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            //缓存目录名httpcache
            File cacheFile = new File(App.getContext().getCacheDir(), "HttpCache");
            //缓存大小100Mb
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
            //云端相应头拦截器，用来动态配置缓存策略
            Interceptor reWriteCacheControlInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    //如果请求的时候没网
                    //无网络下强制使用缓存，无论缓存是否过期,
                    //有网络时则根据缓存时长来决定是否发出请求
                    if (!NetUtil.isConnected(App.getContext())) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();//强制读取缓存
                    }
                    //响应的时候
                    Response originalResponse = chain.proceed(request);
                    if (NetUtil.isConnected(App.getContext())) {

                        return originalResponse.newBuilder()
                                // 有网络时 设置缓存超时时间1个小时，先请求网络（在线时即使有网也一小时请求一次）
                                //没有超出maxAge,不管怎么样都是返回缓存数据，超过了maxAge,发起新的请求获取数据更新，请求失败返回缓存数据。
                                // //当然如果你想在有网络的情况下都直接走网络，那么只需要
                                //             //将其超时时间maxAge设为0即可
                                .header("Cache-Control", "public, max-age=" + 60 * 60)
                                .removeHeader("Pragma").build();
                    } else {
                        return originalResponse
                                .newBuilder()
                                //无网络时使用缓存，超时时间为两天
                                //没有超过maxStale，不管怎么样都返回缓存数据，超过了maxStale,发起请求获取更新数据，请求失败返回失败
                                .header("Cache-Control", CACHE_CONTROL_CACHE)
                                .removeHeader("Pragma").build();
                    }
                }
            };
            /**
             * okhttp 2.x
             */
//            mOkHttpClient = new OkHttpClient();
//            mOkHttpClient.setCache(cache);
//            mOkHttpClient.networkInterceptors().add(rewriteCacheControlInterceptor);
//            mOkHttpClient.interceptors().add(rewriteCacheControlInterceptor);
//            mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
            /**
             * okhttp 3.x
             */



            mOkHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .retryOnConnectionFailure(true)


                    //有网络时的拦截器
                    .addNetworkInterceptor(reWriteCacheControlInterceptor)
                    //没网络时的拦截器
                    .addInterceptor(reWriteCacheControlInterceptor)
                    //网络正常情况下建立连接时长
                    .connectTimeout(30, TimeUnit.SECONDS)
                    //连接成功后读取远程数据所花的时间
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
    }


    //使用单态设计模式去管理ApiManager
    private ApiManager() {
    }

    private static ApiManager instance = null;

    static {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
    }

    public static final ApiManager getInstance() {
        return instance;
    }

    private Object lock = new Object();


    private IDoubanApi iDoubanApi;

    public IDoubanApi getIDoubanApi() {
        if (iDoubanApi == null) {
            synchronized (lock) {
                if (iDoubanApi == null) {
                    //设置缓存
                    initOkHttpClient();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.DOUBAN_URL)
                            .client(mOkHttpClient)
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    iDoubanApi = retrofit.create(IDoubanApi.class);
                }
            }
        }
        return iDoubanApi;
    }

    private INovelApi iNovelApi;

    public INovelApi getiNovelApi() {
        if (iNovelApi==null){
            //设置缓存
            initOkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.ZHUISHUSHENQI_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            iNovelApi= retrofit.create(INovelApi.class);
        }
        return iNovelApi;
    }
}
