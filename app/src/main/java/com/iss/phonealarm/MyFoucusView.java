package com.iss.phonealarm;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zhaocuilong on 2017/10/14.
 */

public class MyFoucusView extends LinearLayout {
    public MyFoucusView(Context context) {
        super(context);
    }

    public MyFoucusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFoucusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
