package com.example.helloworld.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.LinearViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;
//    private OnLongItemClickListener mLongListener;

//    public HorAdapter(Context mContext, OnItemClickListener listener, OnLongItemClickListener LongListener) {
//        this.mContext = mContext;
//        this.mListener=listener;
//        this.mLongListener = LongListener;
//    }
    public StaggeredAdapter(Context mContext, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mListener=listener;
    }
    @NonNull
    @Override
    public StaggeredAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_staggered_grid_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredAdapter.LinearViewHolder holder, final int position) {
        if ( position % 2 != 0){
            holder.imageView.setImageResource(R.drawable.image1);
        }else {
            holder.imageView.setImageResource(R.drawable.image2);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                mLongListener.OnLongClick(position);
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 80;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
    //新建一个接口,返回点击位置参数
    public interface OnItemClickListener{
        void onClick(int pos);
    }
//    //新建一个接口,返回长按位置参数
//    public interface OnLongItemClickListener{
//        void OnLongClick(int pos);
//    }
}
