package masterzht.top.appranking.ui.fragment.first.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import masterzht.top.appranking.App;
import masterzht.top.appranking.R;
import masterzht.top.appranking.model.bean.novel.RankDetailBean;
import masterzht.top.appranking.utils.Constant;

public class RankDetailAdapter extends RecyclerView.Adapter<RankDetailAdapter.ViewHolder> {
    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public interface ItemClickCallBack{
        void onItemClick(int pos);
    }

    public List<RankDetailBean.RankingBean.BooksBean> datas = null;
    private ItemClickCallBack clickCallBack;

    public RankDetailAdapter(List<RankDetailBean.RankingBean.BooksBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        viewHolder.mNovelName.setText(datas.get(position).getTitle());
        viewHolder.mNovelIntro.setText(datas.get(position).getShortIntro());
        Glide.with(App.getContext()).load(Constant.NOVELSTATICS_URL+datas.get(position).getCover()).into(viewHolder.mIvImage);
        viewHolder.mIvImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
/*
        viewHolder.mNovelName.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(clickCallBack != null){
                            clickCallBack.onItemClick(position);
                        }
                    }
                }
        );
*/
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNovelName;
        private TextView mNovelIntro;
        private ImageView mIvImage;

        public ViewHolder(View view){
            super(view);
            mNovelName = (TextView) view.findViewById(R.id.item_textview_novelname);
            mNovelIntro=(TextView)view.findViewById(R.id.item_textview_introduction);
            mIvImage=(ImageView)view.findViewById(R.id.item_imageview_rankdetail);
        }
    }
}
