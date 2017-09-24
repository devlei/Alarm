package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class LostFoundActivity extends Activity implements OnClickListener {

    private TextView mGoodsNameTv;
    private TextView mPickAddressTv;
    private TextView mPickTimeTv;
    private TextView mGoodsDetailTv;
    private TextView mAlarmUnitTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, LostFoundActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);
        init();

        mGoodsDetailTv.setText("银行卡，会员卡\n医保卡：姓名A");
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.lost_found);
        mGoodsNameTv = (TextView) findViewById(R.id.lf_goods_name);
        mPickAddressTv = (TextView) findViewById(R.id.lf_pick_address);
        mPickTimeTv = (TextView) findViewById(R.id.lf_pick_time);
        mGoodsDetailTv = (TextView) findViewById(R.id.lf_goods_detail);
        mAlarmUnitTv = (TextView) findViewById(R.id.lf_alarm_unit);

        //set listener
        findViewById(R.id.title_other).setVisibility(View.GONE);
        findViewById(R.id.title_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
