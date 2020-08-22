package com.example.helloworld.denglu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.denglu.db.domain.User;

import java.util.List;

/**
 * com.example.helloworld.denglu.adapter
 * HelloWorld
 *
 * @author:Tom 2020/8/20
 * 描述:
 **/

public class UserListAdapter extends BaseAdapter {
    private final List<User> userList;
    private final Context context;

    public UserListAdapter(Context context, List<User> userList) {
        this.context=context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_user,null);
        }
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvPsw = convertView.findViewById(R.id.tv_psw);


        tvName.setText(userList.get(position).getName());
        tvPsw.setText(userList.get(position).getPsw());
        return convertView;
    }
}
