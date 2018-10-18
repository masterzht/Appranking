package masterzht.top.appranking.model.api;


import io.reactivex.Flowable;
import masterzht.top.appranking.model.bean.douban.TopMovieBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by masterzht on 2018/9/26.
 */

public interface IDoubanApi {
    @GET("/v2/movie/top250")
    Flowable<TopMovieBean> getTopMovie(@Query("start") int start, @Query("count") int count);
}
