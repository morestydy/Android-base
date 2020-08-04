package com.example.helloworld;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * com.example.helloworld
 * HelloWorld
 *
 * @author:Tom 2020/8/4
 * 描述:属性动画
 **/

public class ObjectAnimActivity extends AppCompatActivity {
    private TextView mTvTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_anim);
        mTvTest = findViewById(R.id.tv);
//                          平移500               执行事件2000ms  启动
//        mTvTest.animate().translationYBy(500).setDuration(2000).start();
//         2秒透明度变0
//        mTvTest.animate().alpha(0).setDuration(2000).start();
//          从0到100变化
//        ValueAnimator animator = ValueAnimator.ofInt(0,100);
//        animator.setDuration(2000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int an= (int) animation.getAnimatedValue();
//                mTvTest.animate().translationYBy(an);
//                Log.d("aadas",animation.getAnimatedValue()+"");//animator实际的值
//                Log.d("aaaaa",animation.getAnimatedFraction()+"");//动画的进度0-1.0
//            }
//        });
//            animator.start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTvTest,"translationY",0,500,200,300);
        objectAnimator.setDuration(2000).start();
    }
}
