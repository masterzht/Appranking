package masterzht.top.appranking.model.bean.novel;

import java.util.List;

public class RankCategoryBean {


    /**
     * rankings : [{"_id":"548e97e29fb698a01dc6ee6f","title":"追书最热榜 Top100","cover":"/ranking-cover/141863113855141.png"},{"_id":"548e40f2c58cff632353e730","title":"读者留存率 Top100","cover":"/ranking-cover/141860888275386.png"},{"_id":"548e984e5beb6f0458d652aa","title":"追书完结榜 Top100","cover":"/ranking-cover/141863124550369.png"},{"_id":"548e9883c58cff632353e731","title":"起点月票榜","cover":"/ranking-cover/141863129848835.png"},{"_id":"548e98c55beb6f0458d652ab","title":"纵横月票榜","cover":"/ranking-cover/141863136489758.png"},{"_id":"548e9915fb8190536fb097d0","title":"17K 鲜花榜","cover":"/ranking-cover/141863144475846.png"},{"_id":"548e99423b5a77135a798b58","title":"创世月票榜","cover":"/ranking-cover/141863148975041.png"},{"_id":"549d1ab5796b680a50686f47","title":"晋江月票榜","cover":"/ranking-cover/141958213270242"},{"_id":"549d1ad11cd9d5d26ee1b6c2","title":"潇湘月票榜","cover":"/ranking-cover/141958216066360"}]
     * ok : true
     */

    private boolean ok;
    private List<RankingsBean> rankings;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<RankingsBean> getRankings() {
        return rankings;
    }

    public void setRankings(List<RankingsBean> rankings) {
        this.rankings = rankings;
    }

    public static class RankingsBean {
        /**
         * _id : 548e97e29fb698a01dc6ee6f
         * title : 追书最热榜 Top100
         * cover : /ranking-cover/141863113855141.png
         */

        private String _id;
        private String title;
        private String cover;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
