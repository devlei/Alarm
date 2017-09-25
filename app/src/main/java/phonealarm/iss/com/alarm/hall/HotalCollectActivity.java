package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class HotalCollectActivity extends Activity implements OnClickListener {

    private EditText mHotalAddressEt;
    private EditText mHotalNameEt;
    private EditText mRoomNameEt;
    private EditText mHotalPhoneEt;
    private EditText mPassengerNameEt;
    private EditText mPassengerIdNumberEt;
    private EditText mPassengerPhone;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, HotalCollectActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotal_collect);
        init();
    }

    private void init() {
        mHotalAddressEt = (EditText) findViewById(R.id.hc_hotal_address);
        mHotalNameEt = (EditText) findViewById(R.id.hc_hotal_name);
        mRoomNameEt = (EditText) findViewById(R.id.hc_room_name);
        mHotalPhoneEt = (EditText) findViewById(R.id.hc_hotal_phone);
        mPassengerNameEt = (EditText) findViewById(R.id.hc_passenger_name);
        mPassengerIdNumberEt = (EditText) findViewById(R.id.hc_passenger_id_number);
        mPassengerPhone = (EditText) findViewById(R.id.hc_passenger_phone);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.hotel_collect);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.complete);
        findViewById(R.id.hc_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                finish();
                ToastUtils.showToast(this, R.string.complete);
                break;
        }
    }
}
