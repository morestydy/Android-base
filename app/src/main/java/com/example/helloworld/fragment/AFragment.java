package com.example.helloworld.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworld.R;

/**
 * com.example.helloworld.fragment
 * HelloWorld
 *
 * @author:Tom 2020/7/28
 * 描述:
 **/

public class AFragment extends Fragment {
    private TextView mTvTitle;
//    private Activity mActivity;
    private String title;
    private Button mBtnReset,mBtnChange,mBtnMessage;
    private BFragment bFragment;
    private IOnMessageClick listener;

//    private String title1;

    public static AFragment newInstance(String title) {
        AFragment fragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }

    //      1.可以使用构造方法传递参数
//    public AFragment(String title) {
//        this.title=title;
//    }
//    public AFragment() {
//
//    }

    public interface IOnMessageClick{
        void onClick(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (IOnMessageClick) context;
        }catch (ClassCastException e){
            throw new ClassCastException("Activity须实现接口");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        Log.d("AFragment","----onCreateView-----");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        mTvTitle = view.findViewById(R.id.tv_title);

        mBtnChange = view.findViewById(R.id.btn_change);
        mBtnReset = view.findViewById(R.id.btn_reset);
        mBtnMessage = view.findViewById(R.id.btn_message);

        mBtnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((ContainerActivity)getActivity()).setData("nihao");
                listener.onClick("nihao");
            }
        });

        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bFragment == null){
                    bFragment = new BFragment();
                }
                Fragment fragment=getFragmentManager().findFragmentByTag("a");
                if (fragment != null){//有就移除再添加,无则添加,replace是清空容器再添加当前，无则直接添加当前
                    getFragmentManager().beginTransaction().hide(fragment).add(R.id.fl_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
                }else {
                    getFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
                }
            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTitle.setText("new BFragment");
            }
        });
//        if (getActivity() != null){
//            //  TODO:
//        }else {
//
//        }
        if (getArguments() != null){
            mTvTitle.setText(getArguments().getString("title"));
        }

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
////        mActivity= (Activity) context;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //取消异步
//    }
}
