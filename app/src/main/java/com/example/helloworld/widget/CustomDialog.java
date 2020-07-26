package com.example.helloworld.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.helloworld.R;

/**
 * com.example.helloworld.widget
 * HelloWorld
 *
 * @author:Tom 2020/7/26
 * 描述:
 **/

public class CustomDialog extends Dialog implements View.OnClickListener {
    private TextView mTvTitle,mtvMessage,mTvCancel,mTvConfirm;
    private String title,message,confirm,cancel;
    private IOnConfirmListener confirmListener;
    private IOnCancelListener cancelListener;

    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomDialog setConfirm(String confirm,IOnConfirmListener confirmListener) {
        this.confirm = confirm;
        this.confirmListener = confirmListener;
        return this;
    }

    public CustomDialog setCancel(String cancel,IOnCancelListener cancelListener) {
        this.cancel = cancel;
        this.cancelListener = cancelListener;
        return this;
    }

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);

        //设置宽度
        WindowManager manager = getWindow().getWindowManager();
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        Point size = new Point();
        display.getSize(size);
        // 宽度为当前屏幕的80%
        params.width = (int) (size.x * 0.8);
        getWindow().setAttributes(params);


        mTvTitle = findViewById(R.id.tv_title);
        mtvMessage = findViewById(R.id.tv_message);
        mTvCancel = findViewById(R.id.tv_cancel);
        mTvConfirm = findViewById(R.id.tv_confirm);
        if(!TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(message)){
            mtvMessage.setText(message);
        }
        if(!TextUtils.isEmpty(confirm)){
            mTvConfirm.setText(confirm);
        }
        if(!TextUtils.isEmpty(cancel)){
            mTvCancel.setText(cancel);
        }
        mTvCancel.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm:
                if(confirmListener != null)
                    confirmListener.OnConfirm(this);
                dismiss();
                break;
            case R.id.tv_cancel:
                if (cancelListener != null)
                    cancelListener.OnCancel(this);
                dismiss();
                break;
        }
    }


    public interface IOnConfirmListener {
        public void OnConfirm(CustomDialog dialog);
    }

    public interface IOnCancelListener {
        public void OnCancel(CustomDialog dialog);
    }
}
