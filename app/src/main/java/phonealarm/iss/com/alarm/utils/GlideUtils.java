package phonealarm.iss.com.alarm.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;

/**
 * Created by zhaocuilong on 2016/12/6.
 */
public class GlideUtils {

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param errorResId
     * @param imageView
     */
    public static void loadImage(Context context, String url, int errorResId, ImageView imageView) {
        loadTransformationImage(context, url, errorResId, errorResId, null, imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param placeHolder
     * @param errorResId
     * @param imageView
     */
    public static void loadImage(Context context, String url, int placeHolder, int errorResId, ImageView imageView) {
        loadTransformationImage(context, url, placeHolder, errorResId, null, imageView);
    }


    /**
     * 加载背景图片
     *
     * @param context
     * @param url
     * @param errorResId
     * @param view
     */
    public static void loadBackgroundImage(Context context, String url, int errorResId, View view) {
        if (context != null && !TextUtils.isEmpty(url) && view != null) {
            ImageUtils.defaultTargetLoadImage(context, url, errorResId, errorResId, new GlideBackgroundTarget(view));
        }
    }

    /**
     * 加载背景图片
     *
     * @param context
     * @param url
     * @param placeHolderResId
     * @param errorResId
     * @param view
     */
    public static void loadBackgroundImage(Context context,
                                           String url,
                                           int placeHolderResId,
                                           int errorResId,
                                           View view) {
        if (context != null && !TextUtils.isEmpty(url) && view != null) {
            ImageUtils.defaultTargetLoadImage(context, url, placeHolderResId, errorResId,
                    new GlideBackgroundTarget(view));
        }
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param resId
     * @param errorResId
     * @param imageView
     */
    public static void loadLocalImage(Context context, int resId, int errorResId, ImageView imageView) {
        if (context != null && imageView != null) {
            Glide.with(context.getApplicationContext())
                    .load(resId)
                    .placeholder(errorResId)
                    .error(errorResId)
                    .into(imageView);
        }
    }

    /**
     * 加载transformation图片
     *
     * @param context
     * @param url
     * @param placeHolderResId
     * @param errorResId
     * @param transformation
     * @param imageView
     */
    public static void loadTransformationImage(Context context,
                                               String url,
                                               int placeHolderResId,
                                               int errorResId,
                                               Transformation transformation,
                                               ImageView imageView) {
        if (context != null && !TextUtils.isEmpty(url) && imageView != null) {
            if (ImageUtils.isGifImage(url)) {
                ImageUtils.defaultGifLoadImage(context, url, placeHolderResId, errorResId, imageView);
            } else {
                if (transformation == null) {
                    ImageUtils.defaultLoadImage(context, url, placeHolderResId, errorResId, imageView);
                } else {
                    ImageUtils.defaultTransformationLoadImage(context, url, placeHolderResId, errorResId,
                            transformation, imageView);
                }
            }
        }
    }

}
