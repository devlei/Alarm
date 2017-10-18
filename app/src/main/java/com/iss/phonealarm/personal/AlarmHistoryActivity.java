package com.iss.phonealarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.searchalarm.AlarmFilesList;
import com.iss.phonealarm.bean.searchalarm.AlarmInfoBean;
import com.iss.phonealarm.bean.searchalarm.MultimediaAttrBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;

/**
 * Created by weizhilei on 2017/9/26.
 */
public class AlarmHistoryActivity extends Activity implements OnClickListener {

    private static final String ALARM_INFO = "alarm_info";

    private TextView mTimeTv;
    private TextView mSiteTv;
    private TextView mPhoneTv;
    private TextView mContentTv;
    private ImageButton mPlayBtn;
    private TextView mPlayTimeTv;
    private ProgressBar mProgressPb;
    private LinearLayout imgarray;
    final List<ImageView> mList = new ArrayList<>();
    final List<String> urlList = new ArrayList<>();
    ImageWatcher vImageWatcher;
    //播放音频
    private MediaPlayer mediaPlayer;
    private String mediaPath = "http://218.241.189.52:8089/alarmFolder/2017-09-28/f06f6ff0-871c-42df-88ae" +
            "-4786fc41169c.amr";

    /**
     * open
     *
     * @param context
     * @param alarmInfo
     */
    public static void open(Context context, AlarmInfoBean alarmInfo) {
        if (context != null) {
            Intent intent = new Intent(context, AlarmHistoryActivity.class);
            intent.putExtra(ALARM_INFO, alarmInfo);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_history);
        vImageWatcher = ImageWatcher.Helper.with(this) // 一般来讲， ImageWatcher 需要占据全屏的位置
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
        init();
        setData();
    }

    private void init() {
        mTimeTv = (TextView) findViewById(R.id.ah_time);
        mSiteTv = (TextView) findViewById(R.id.ah_site);
        mPhoneTv = (TextView) findViewById(R.id.ah_phone);
        mContentTv = (TextView) findViewById(R.id.ah_content);
        mPlayBtn = (ImageButton) findViewById(R.id.play);
        mPlayTimeTv = (TextView) findViewById(R.id.time);
        mProgressPb = (ProgressBar) findViewById(R.id.progressBar);
        imgarray = (LinearLayout) findViewById(R.id.imgarray);

        mPlayBtn.setOnClickListener(this);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.alarm_history);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.evaluate);
    }

    private void setData() {
        AlarmInfoBean alarmInfo = (AlarmInfoBean) getIntent().getSerializableExtra(ALARM_INFO);
        if (alarmInfo != null) {
            mTimeTv.setText(alarmInfo.getRptalarm_time());
            mSiteTv.setText(alarmInfo.getAlarm_addres());
            mPhoneTv.setText(alarmInfo.getAlarm_phone());
            mContentTv.setText(alarmInfo.getAlarm_content());
            if (alarmInfo.getAlarmFilesList() != null) {
                AlarmFilesList alarmFilesList = alarmInfo.getAlarmFilesList();
                addImage(alarmFilesList.getAttach_list());
            }
        }
    }

    private void addImage(List<MultimediaAttrBean> attach_list) {
        if (null != attach_list && attach_list.size() > 0) {
            urlList.clear();
            mList.clear();
            for (int i = 0; i < attach_list.size(); i++) {
                MultimediaAttrBean multimediaAttrBean = attach_list.get(i);
                if (null != multimediaAttrBean) {
                    if (multimediaAttrBean.getType().equals("jpg") || multimediaAttrBean.getType().equals("png")) {
                        urlList.add(multimediaAttrBean.getValue());
                        final ImageView img = new ImageView(this);
                        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                getResources().getDimensionPixelSize(R.dimen.s_50),
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        lp.rightMargin = getResources().getDimensionPixelSize(R.dimen.s_18);
                        imgarray.addView(img, lp);
                        mList.add(img);
                        Glide.with(this).load(multimediaAttrBean.getValue()).into(img);
                        img.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                vImageWatcher.show(img, mList, urlList);
                            }
                        });
                    } else {
                        mediaPath = multimediaAttrBean.getValue();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                AlarmInfoBean alarmInfo = (AlarmInfoBean) getIntent().getSerializableExtra(ALARM_INFO);
                if (alarmInfo != null) {
                    EvaluateDialog.show(this, alarmInfo.getAlarm_id(), alarmInfo.getAlarm_phone());
                }
                break;
            case R.id.play:
                play(mediaPath);
                break;
        }
    }

    public static final int PROGRESS = 122;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == PROGRESS) {
                if (null != mProgressPb) {
                    if (mediaPlayer.getCurrentPosition() <= mediaPlayer.getDuration()) {
                        mHandler.sendEmptyMessageDelayed(PROGRESS, 200);
                        mProgressPb.setProgress(mediaPlayer.getCurrentPosition());
                    }
                }
            }
        }
    };

    private void play(String path) {
        try {
            if (null == mediaPlayer) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(mediaPath);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mProgressPb.setProgress(mp.getDuration());
                        mPlayBtn.setBackgroundResource(R.drawable.icon_play_small);
                    }
                });
                mediaPlayer.prepare();
                mediaPlayer.start();
                mProgressPb.setMax(mediaPlayer.getDuration());
                mHandler.sendEmptyMessageDelayed(PROGRESS, 0);
                mPlayBtn.setBackgroundResource(R.drawable.video_pause);
            } else {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mHandler.removeMessages(PROGRESS);
                    mPlayBtn.setBackgroundResource(R.drawable.icon_play_small);
                } else {
                    mediaPlayer.start();
                    mHandler.sendEmptyMessageDelayed(PROGRESS, 0);
                    mPlayBtn.setBackgroundResource(R.drawable.video_pause);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mediaPlayer) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
    }

}
