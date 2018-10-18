package masterzht.top.appranking.ui.fragment.first.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import masterzht.top.appranking.App;
import masterzht.top.appranking.R;
import masterzht.top.appranking.model.bean.douban.TopMovieBean;

/**
 * Created by masterzht on 2018/9/26.
 */

public class TopMovieAdapter extends RecyclerView.Adapter<TopMovieAdapter.ViewHolder> {

    public void setClickCallBack(ItemClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public interface ItemClickCallBack{
        void onItemClick(int pos);
    }

    public ArrayList<TopMovieBean.SubjectsGsonBean> datas=null ;
    private ItemClickCallBack clickCallBack;

    public TopMovieAdapter(ArrayList<TopMovieBean.SubjectsGsonBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_topmovie,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        //viewHolder.mNovelName.setText(datas.get(position));
        viewHolder.mTextView.setText(datas.get(position).getTitle().toString());


        Glide.with(App.getContext()).load(datas.get(position).getImages().getSmall()).into(viewHolder.mIvImage);
        viewHolder.mIvImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        viewHolder.mRatingBar.setRating((float) datas.get(position).getRating().getAverage()/2);
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

        ImageView mIvImage;
        TextView mTextView;
        RatingBar mRatingBar;
        public ViewHolder(View view){
            super(view);

            mIvImage=(ImageView)view.findViewById(R.id.item_imageview_topmovie);
            mTextView = (TextView) view.findViewById(R.id.item_name_textview_movie);
            mRatingBar=(RatingBar)view.findViewById(R.id.ratingbar_topmovie);
        }
    }
}
