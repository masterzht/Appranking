package masterzht.top.appranking.model.api;

import masterzht.top.appranking.model.bean.novel.RankCategoryBean;
import masterzht.top.appranking.model.bean.novel.RankDetailBean;
import retrofit2.http.GET;
import io.reactivex.Flowable;
import retrofit2.http.Path;

public interface INovelApi {


    /**
     * 获取排名分类
     * @return
     */
    @GET("/ranking")
    Flowable<RankCategoryBean> getRankCategory();

    @GET("/ranking/{id}")
    Flowable<RankDetailBean> getRankDetail(@Path("id") String id);

}
