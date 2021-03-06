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
import com.iss.phonealarm.bean.beLost.BelostInfo;
import com.iss.phonealarm.utils.GlideUtils;
import com.iss.phonealarm.utils.IntentUtils;
import com.iss.phonealarm.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class PeopleLostActivity extends Activity implements OnClickListener {

    private static final String PEOPLE_LOST_INFO = "people_lost_info";

    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mGenderTv;
    private TextView mAgeTv;
    private TextView mMissLocationTv;
    private TextView mMissTimeTv;
    private TextView mBirthDateTv;
    private TextView mHeightWeightTv;
    private TextView mHairColorTv;
    private TextView mDetailTv;
    private ImageView mHeaderIv;
    private TextView mFamilyNameTv;
    private TextView mFamilyPhoneTv;

    /**
     * open
     *
     * @param context
     * @param belostInfo
     */
    public static void open(Context context, BelostInfo belostInfo) {
        if (context != null) {
            Intent intent = new Intent(context, PeopleLostActivity.class);
            intent.putExtra(PEOPLE_LOST_INFO, belostInfo);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_lost);
        init();
        setData();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.people_lost);
        TextView reportTv = (TextView) findViewById(R.id.title_other);
        reportTv.setText(R.string.report);
        mNameTv = (TextView) findViewById(R.id.pl_name);
        mTimeTv = (TextView) findViewById(R.id.pl_time);
        mGenderTv = (TextView) findViewById(R.id.pl_gender);
        mAgeTv = (TextView) findViewById(R.id.pl_age);
        mMissLocationTv = (TextView) findViewById(R.id.pl_miss_location);
        mMissTimeTv = (TextView) findViewById(R.id.pl_miss_time);
        mBirthDateTv = (TextView) findViewById(R.id.pl_birth_date);
        mHeightWeightTv = (TextView) findViewById(R.id.pl_height_weight);
        mHairColorTv = (TextView) findViewById(R.id.pl_hair);
        mDetailTv = (TextView) findViewById(R.id.pl_detail);
        mHeaderIv = (ImageView) findViewById(R.id.pl_header);
        mFamilyNameTv = (TextView) findViewById(R.id.pl_family_name);
        mFamilyPhoneTv = (TextView) findViewById(R.id.pl_family_phone);

        //set listener
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        findViewById(R.id.pl_phone_icon).setOnClickListener(this);
    }

    private void setData() {
        BelostInfo belostInfo = (BelostInfo) getIntent().getSerializableExtra(PEOPLE_LOST_INFO);
        if (belostInfo != null) {
            mNameTv.setText(belostInfo.getBeLost_name());
            mTimeTv.setText(belostInfo.getPursuit_time());
            mGenderTv.setText(belostInfo.getBeLost_sex());
            mAgeTv.setText(belostInfo.getBeLost_age());
            mMissLocationTv.setText(belostInfo.getBeLost_site());
            mMissTimeTv.setText(belostInfo.getBeLost_date());
            mBirthDateTv.setText(belostInfo.getBeLost_bithdate());
            mHeightWeightTv.setText(belostInfo.getBeLost_weigth());
            mHairColorTv.setText(belostInfo.getBeLost_hairstyle());
            mDetailTv.setText(belostInfo.getBeLost_information());
            GlideUtils.loadImage(this, belostInfo.getBeLost_pUrl(), R.drawable.icon_header_default,
                    mHeaderIv);
            mFamilyNameTv.setText(belostInfo.getBeLost_telename());
            mFamilyPhoneTv.setText(belostInfo.getBeLost_telenum());
            //
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
            mList.add(mHeaderIv);
            urlList.add(belostInfo.getBeLost_pUrl());
            mHeaderIv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    vImageWatcher.show(mHeaderIv, mList, urlList);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                IntentUtils.openFastAlarmActivity(this, R.integer.type_people_lost);
                break;
            case R.id.pl_phone_icon:
                if (null != mFamilyPhoneTv.getText()) {
                    Utils.call(this, mFamilyPhoneTv.getText().toString());
                }
                break;
        }
    }
}
