package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.searchalarm.AlarmInfoBean;

/**
 * Created by weizhilei on 2017/9/26.
 */
public class AlarmHistoryActivity extends Activity implements OnClickListener {

    private static final String ALARM_INFO = "alarm_info";

    private TextView mTimeTv;
    private TextView mSiteTv;
    private TextView mPhoneTv;
    private TextView mContentTv;
    private ImageView mImg1Iv;
    private ImageView mImg2Iv;
    private ImageButton mPlayBtn;
    private TextView mPlayTimeTv;
    private ProgressBar mProgressPb;

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
        mImg1Iv = (ImageView) findViewById(R.id.ah_img1);
        mImg2Iv = (ImageView) findViewById(R.id.ah_img2);
        mPlayBtn = (ImageButton) findViewById(R.id.play);
        mPlayTimeTv = (TextView) findViewById(R.id.time);
        mProgressPb = (ProgressBar) findViewById(R.id.progressBar);

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
            // TODO: 2017/9/26 weizhilei 附件
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
                    EvaluateDialog.show(this, alarmInfo.getAlarm_id());
                }
                break;
        }
    }

}
