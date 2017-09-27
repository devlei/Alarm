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
import phonealarm.iss.com.alarm.bean.interactquery.InterQueryInfo;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class PoliceInteractDetailActivity extends Activity implements OnClickListener {

    private static final String POLICE_INTERACT_INFO = "police_interact_info";

    private TextView mDescTv;
    private ImageView mCoverIv;
    private TextView mReplyDescTv;
    private TextView mReplyTimeTv;


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
        InterQueryInfo interQueryInfo = (InterQueryInfo) getIntent().getSerializableExtra(POLICE_INTERACT_INFO);
        if (interQueryInfo != null) {
            mDescTv.setText(interQueryInfo.getFk_content());
            mReplyDescTv.setText(interQueryInfo.getReply_content());
            mReplyTimeTv.setText(interQueryInfo.getReply_date());
        }
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
