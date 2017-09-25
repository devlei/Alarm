package phonealarm.iss.com.alarm.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * 背景图target
 * Created by weizhilei on 2017/1/6.
 */
public class GlideBackgroundTarget<T extends Drawable> extends SimpleTarget<T> {

    private View mView;

    public GlideBackgroundTarget(View view) {
        mView = view;
    }

    @Override
    public void onResourceReady(T resource, GlideAnimation<? super T> glideAnimation) {
        if (mView != null) {
            mView.setBackgroundDrawable(resource);
        }
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        if (mView != null) {
            mView.setBackgroundDrawable(errorDrawable);
        }
    }
}
