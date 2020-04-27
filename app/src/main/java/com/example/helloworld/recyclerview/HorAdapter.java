package com.example.helloworld.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

public class HorAdapter extends RecyclerView.Adapter<HorAdapter.LinearViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;
//    private OnLongItemClickListener mLongListener;

//    public HorAdapter(Context mContext, OnItemClickListener listener, OnLongItemClickListener LongListener) {
//        this.mContext = mContext;
//        this.mListener=listener;
//        this.mLongListener = LongListener;
//    }
    public HorAdapter(Context mContext, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mListener=listener;
    }
    @NonNull
    @Override
    public HorAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_hor_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorAdapter.LinearViewHolder holder, final int position) {
        holder.textView.setText("Hello");
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
        return 30;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
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
