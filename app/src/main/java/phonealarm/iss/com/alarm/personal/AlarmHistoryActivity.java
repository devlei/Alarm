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

/**
 * Created by weizhilei on 2017/9/26.
 */
public class AlarmHistoryActivity extends Activity implements OnClickListener {

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
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, AlarmHistoryActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_history);
        init();
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
        ((TextView) findViewById(R.id.title_other)).setText(R.string.evaluate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                EvaluateDialog.show(this);
                break;
        }
    }

}
