package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.searchalarm.AlarmFilesList;
import phonealarm.iss.com.alarm.bean.searchalarm.AlarmInfoBean;
import phonealarm.iss.com.alarm.bean.searchalarm.MultimediaAttrBean;

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

    //播放音频
    private MediaPlayer mPlayer;
    private String mediaPath = "http://218.241.189.52:8089/alarmFolder/2017-09-28/f06f6ff0-871c-42df-88ae-4786fc41169c.amr";

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
            for (int i = 0; i < attach_list.size(); i++) {
                MultimediaAttrBean multimediaAttrBean = attach_list.get(i);
                if (null != multimediaAttrBean) {
                    if (multimediaAttrBean.getType().equals("jpg")
                            || multimediaAttrBean.getType().equals("png")) {
                        final ImageView img = new ImageView(this);
                        img.setScaleType(ImageView.ScaleType.FIT_XY);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                getResources().getDimensionPixelSize(R.dimen.s_50),
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        lp.rightMargin = getResources().getDimensionPixelSize(R.dimen.s_21);
                        imgarray.addView(img, lp);
                        Glide.with(this).load(multimediaAttrBean.getValue()).into(img);
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

    private void play(String path) {
        if (null == mPlayer) {
            try {
                mPlayer = new MediaPlayer();
                mPlayer.setDataSource(path);
                mPlayer.prepare();
            } catch (Exception e) {
            }
        }
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            start();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    public void start() {
        //播放
        if (null != mPlayer) {
            mPlayer.start();
        }
    }

    public void stop() {
        if (null != mPlayer) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

}
