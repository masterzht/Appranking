package masterzht.top.appranking.ui.fragment.first.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import masterzht.top.appranking.App;
import masterzht.top.appranking.R;
import masterzht.top.appranking.model.bean.novel.RankCategoryBean;
import masterzht.top.appranking.utils.Constant;

public class RankCategoryAdapter extends RecyclerView.Adapter<RankCategoryAdapter.ViewHolder>  implements View.OnClickListener{

    private OnItemClickListener mClickListener;
    private Context context;

    @Override
    public void onClick(View v) {
        if (mClickListener != null) {
            mClickListener.onItemClick((Integer) v.getTag());
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }


    public ArrayList<RankCategoryBean.RankingsBean> datas = null;


    public RankCategoryAdapter(Context context, ArrayList<RankCategoryBean.RankingsBean> datas) {

        this.context=context;
        this.datas = datas;
    }



    //创建新View，被LayoutManager所调用
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rank_category,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);
        Glide.with(App.getContext()).load(Constant.NOVELSTATICS_URL+datas.get(position).getCover()).into(viewHolder.mIvImage);
        viewHolder.mIvImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        viewHolder.mTextView.setText(datas.get(position).getTitle());

        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(viewHolder.getAdapterPosition());
                }
            }
        });*/

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

    }

    @Override
    public int getItemCount() {
        //return datas.size();
        return datas.size();
    }



    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        ImageView mIvImage;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            mIvImage=(ImageView)view.findViewById(R.id.item_imageview_rankcategory);
        }
    }
}
