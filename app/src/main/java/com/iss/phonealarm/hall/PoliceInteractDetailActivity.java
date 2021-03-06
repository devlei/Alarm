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
import com.iss.phonealarm.bean.interactquery.InterQueryInfo;
import com.iss.phonealarm.bean.interactquery.InterQueryType;
import com.iss.phonealarm.utils.CollectionUtils;
import com.iss.phonealarm.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class PoliceInteractDetailActivity extends Activity implements OnClickListener {

    private static final String POLICE_INTERACT_INFO = "police_interact_info";

    private TextView mDescTv;
    private ImageView mCoverIv, mCoverIvTwo, mCoverIvThree, mCoverIvFour;
    private TextView mReplyDescTv;
    private TextView mReplyTimeTv;
    final List<ImageView> mList = new ArrayList<>();
    final List<String> urlList = new ArrayList<>();

    /**
     * open
     *
     * @param context
     * @param interQueryInfo
     */
    public static void open(Context context, InterQueryInfo interQueryInfo) {
        if (context != null) {
            Intent intent = new Intent(context, PoliceInteractDetailActivity.class);
            intent.putExtra(POLICE_INTERACT_INFO, interQueryInfo);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_interact_detail);
        init();
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
        InterQueryInfo interQueryInfo = (InterQueryInfo) getIntent().getSerializableExtra(POLICE_INTERACT_INFO);
        if (interQueryInfo != null) {
            mDescTv.setText(interQueryInfo.getFk_content());
            if (interQueryInfo.getFiles() != null) {
                List<InterQueryType> interQueryTypeList = interQueryInfo.getFiles().getFile();
                if (!CollectionUtils.isEmpty(interQueryTypeList)) {
                    urlList.clear();
                    mList.clear();
                    if (interQueryTypeList.size() == 1) {
                        mList.add(mCoverIv);
                    }
                    if (interQueryTypeList.size() == 2) {
                        mList.add(mCoverIv);
                        mList.add(mCoverIvTwo);
                    }
                    if (interQueryTypeList.size() == 3) {
                        mList.add(mCoverIv);
                        mList.add(mCoverIvTwo);
                        mList.add(mCoverIvThree);
                    }
                    if (interQueryTypeList.size() == 4) {
                        mList.add(mCoverIv);
                        mList.add(mCoverIvTwo);
                        mList.add(mCoverIvThree);
                        mList.add(mCoverIvFour);
                    }
                    for (int i = 0; i < interQueryTypeList.size(); i++) {
                        InterQueryType interQueryType = interQueryTypeList.get(i);
                        urlList.add(interQueryType.getValue());
                        final ImageView imageView = mList.get(i);
                        if (interQueryType != null) {
                            imageView.setVisibility(View.VISIBLE);
                            GlideUtils.loadImage(this, interQueryType.getValue(), R.drawable.icon_header_default,
                                    imageView);
                            imageView.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    vImageWatcher.show(imageView, mList, urlList);
                                }
                            });
                        }
                    }
                }
            }
            mReplyDescTv.setText(interQueryInfo.getReply_content());
            mReplyTimeTv.setText(interQueryInfo.getReply_date());
        }
    }

    private void init() {
        mDescTv = (TextView) findViewById(R.id.pid_desc);
        mCoverIv = (ImageView) findViewById(R.id.pid_img);
        mCoverIvTwo = (ImageView) findViewById(R.id.pid_img_two);
        mCoverIvThree = (ImageView) findViewById(R.id.pid_img_three);
        mCoverIvFour = (ImageView) findViewById(R.id.pid_img_four);
        mReplyDescTv = (TextView) findViewById(R.id.pid_reply_desc);
        mReplyTimeTv = (TextView) findViewById(R.id.pid_reply_time);

        findViewById(R.id.title_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.police_interact);
        findViewById(R.id.title_other).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
