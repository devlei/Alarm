package com.iss.phonealarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.carinfo.CarInfo;
import com.iss.phonealarm.utils.GlideUtils;
import com.iss.phonealarm.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class VehicleTrackActivity extends Activity implements OnClickListener {

    private static final String CAR_INFO = "car_info";

    private TextView mCarNumberTv;
    private TextView mCarTimeTv;
    private TextView mCarTypeTv;
    private TextView mCarBrandTv;
    private TextView mCarColorTv;
    private TextView mCarDescTv;
    private ImageView mCarIv;

    /**
     * open
     *
     * @param context
     * @param carInfo
     */
    public static void open(Context context, CarInfo carInfo) {
        if (context != null) {
            Intent intent = new Intent(context, VehicleTrackActivity.class);
            intent.putExtra(CAR_INFO, carInfo);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_track);
        init();
        setData();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.vehicle_track);
        TextView reportTv = (TextView) findViewById(R.id.title_other);
        reportTv.setText(R.string.report);
        mCarNumberTv = (TextView) findViewById(R.id.vt_car_number);
        mCarTimeTv = (TextView) findViewById(R.id.vt_cat_time);
        mCarTypeTv = (TextView) findViewById(R.id.vt_car_type);
        mCarBrandTv = (TextView) findViewById(R.id.vt_car_brand);
        mCarColorTv = (TextView) findViewById(R.id.vt_car_color);
        mCarDescTv = (TextView) findViewById(R.id.vt_car_desc);
        mCarIv = (ImageView) findViewById(R.id.vt_car_img);

        //set listener
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
    }

    private void setData() {
        final CarInfo carInfo = (CarInfo) getIntent().getSerializableExtra(CAR_INFO);
        if (carInfo != null) {
            mCarNumberTv.setText(carInfo.getCar_num());
            mCarTimeTv.setText(carInfo.getPursuit_time());
            mCarTypeTv.setText(carInfo.getCar_type());
            mCarBrandTv.setText(carInfo.getCar_brand());
            mCarColorTv.setText(carInfo.getCar_color());
            mCarDescTv.setText(carInfo.getCar_info());
            GlideUtils.loadImage(this, carInfo.getCar_purl(), R.drawable.icon_header_default,
                    mCarIv);

            final ImageWatcher vImageWatcher = ImageWatcher.Helper.with(this) // 一般来讲， ImageWatcher 需要占据全屏的位置
                    .setErrorImageRes(R.mipmap.error_picture) // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
                    .setLoader(new ImageWatcher.Loader() {
                        @Override
                        public void load(Context context, String url, final ImageWatcher.LoadCallback lc) {
                            Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    lc.onResourceReady(resource);
                                }

                                @Override
                                public void onLoadStarted(Drawable placeholder) {
                                    lc.onLoadStarted(placeholder);
                                }

                                @Override
                                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    lc.onLoadFailed(errorDrawable);
                                }
                            });
                        }
                    }).create();
            final List<ImageView> mList = new ArrayList<>();
            final List<String> urlList = new ArrayList<>();
            mList.add(mCarIv);
            urlList.add(carInfo.getCar_purl());
            mCarIv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    vImageWatcher.show(mCarIv, mList, urlList);
                }
            });
        }
    }

    private void imageClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                IntentUtils.openFastAlarmActivity(this, R.integer.type_vehicle_track);
                break;
        }
    }
}
