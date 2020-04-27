package com.example.helloworld.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;
    private OnLongItemClickListener mLongListener;

    public LinearAdapter(Context mContext,OnItemClickListener listener,OnLongItemClickListener LongListener) {
        this.mContext = mContext;
        this.mListener=listener;
        this.mLongListener = LongListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item,parent,false));
        } else {
            return new LinearViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item_2,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == 0) {
            ((LinearViewHolder) holder).textView.setText("以梦为马,不负韶华");

        } else {
            ((LinearViewHolder2) holder).imageView.setImageResource(R.drawable.beauty_girl);
            ((LinearViewHolder2) holder).textView.setText("面朝大海,春暖花开");

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mLongListener.OnLongClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2 == 0){
            return 0;
        }else {
            return 1;
        }
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

    class LinearViewHolder2 extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;
        public LinearViewHolder2(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
    //新建一个接口,返回点击位置参数
    public interface OnItemClickListener{
        void onClick(int pos);
    }
    //新建一个接口,返回长按位置参数
    public interface OnLongItemClickListener{
        void OnLongClick(int pos);
    }
}
