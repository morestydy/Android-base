package com.example.helloworld.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloworld.R;

public class MyListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;
    public MyListAdapter(Context context){
        this.context=context;
        mLayoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvTitle,tvContent,tvTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.layout_list_item,null);
            holder=new ViewHolder();
            holder.imageView=convertView.findViewById(R.id.iv);
            holder.tvTime=convertView.findViewById(R.id.tv_time);
            holder.tvTitle=convertView.findViewById(R.id.tv_title);
            holder.tvContent=convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }//给控件赋值
        holder.tvTitle.setText("这是标题");
        holder.tvTime.setText("2088.08.08");
        holder.tvContent.setText("这是内容");
        Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554742176602&di=46cebc51f7c64b446c38793329e9b8d2&imgtype=0&src=http%3A%2F%2Fwww.ooqiu.com%2Fuploads%2Fallimg%2F170726%2F262-1FH6142G20-L.jpg").into(holder.imageView);
        return convertView;
    }
}
