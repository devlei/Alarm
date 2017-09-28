package phonealarm.iss.com.alarm.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by weizhilei on 2017/1/7.
 */
public class ImageUtils {

    /**
     * 默认加载方式
     *
     * @param context
     * @param url
     * @param placeHolderResId
     * @param errorResId
     * @param imageView
     */
    public static void defaultLoadImage(Context context,
                                        String url,
                                        int placeHolderResId,
                                        int errorResId,
                                        ImageView imageView) {
        if (context != null && !TextUtils.isEmpty(url) && imageView != null) {
            Glide.with(context.getApplicationContext())
                    .load(url)
                    .placeholder(placeHolderResId)
                    .error(errorResId)
                    .into(imageView);
        }
    }

    /**
     * 默认target加载方式
     *
     * @param context
     * @param url
     * @param placeHolderResId
     * @param errorResId
     * @param target
     */
    public static void defaultTargetLoadImage(Context context,
                                              String url,
                                              int placeHolderResId,
                                              int errorResId,
                                              SimpleTarget target) {
        if (context != null && !TextUtils.isEmpty(url) && target != null) {
            if (isGifImage(url)) {
                Glide.with(context.getApplicationContext())
                        .load(url)
                        .asGif()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(placeHolderResId)
                        .error(errorResId)
                        .into(target);
            } else {
                Glide.with(context.getApplicationContext())
                        .load(url)
                        .placeholder(placeHolderResId)
                        .error(errorResId)
                        .into(target);
            }
        }
    }


    /**
     * 默认Gif模式加载方式
     *
     * @param context
     * @param url
     * @param placeHolderResId
     * @param errorResId
     * @param imageView
     */
    public static void defaultGifLoadImage(Context context,
                                           String url,
                                           int placeHolderResId,
                                           int errorResId,
                                           ImageView imageView) {
        if (context != null && !TextUtils.isEmpty(url) && imageView != null) {
            Glide.with(context.getApplicationContext())
                    .load(url)
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(placeHolderResId)
                    .error(errorResId)
                    .into(imageView);
        }
    }

    /**
     * 默认transformation模式加载方式
     *
     * @param context
     * @param url
     * @param placeHolderResId
     * @param errorResId
     * @param transformation
     * @param imageView
     */
    public static void defaultTransformationLoadImage(Context context,
                                                      String url,
                                                      int placeHolderResId,
                                                      int errorResId,
                                                      Transformation transformation,
                                                      ImageView imageView) {
        if (context != null && !TextUtils.isEmpty(url) && transformation != null && imageView != null) {
            Glide.with(context.getApplicationContext())
                    .load(url)
                    .placeholder(placeHolderResId)
                    .error(errorResId)
                    .bitmapTransform(transformation)
                    .into(imageView);
        }
    }

    /**
     * 判断是否是gif图
     *
     * @param url
     * @return
     */
    public static boolean isGifImage(String url) {
        if (!TextUtils.isEmpty(url)) {
            String suffix = url.substring(url.lastIndexOf(".") + 1, url.length());
            if ("gif".equals(suffix.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取相册图片地址
     *
     * @param context
     * @param uri
     * @return
     */
    public String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver()
                    .query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
