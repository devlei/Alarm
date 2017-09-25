package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.utils.DateUtils;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class PoliceInteractDetailActivity extends Activity implements OnClickListener {

    private TextView mDescTv;
    private ImageView mCoverIv;
    private TextView mReplyDescTv;
    private TextView mReplyTimeTv;


    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, PoliceInteractDetailActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_interact_detail);
        init();

        //test data
        mDescTv.setText("省政府周边北京第九中学旁的兴华长街长期有车辆停放，该地区有九中和风华小学，一到放学时间就造成道路拥堵，周围小区居民进出也不方便，建议将兴华街划上道牙儿，禁止道边停车。");
        mReplyDescTv.setText("您好，我们会转交交警部门实地查看，进行整改请您耐心等待我们为您反馈信息。");
        mReplyTimeTv.setText(DateUtils.formatDate(DateUtils.Y_M_D_H_M, System.currentTimeMillis()));
    }

    private void init() {
        mDescTv = (TextView) findViewById(R.id.pid_desc);
        mCoverIv = (ImageView) findViewById(R.id.pid_img);
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
