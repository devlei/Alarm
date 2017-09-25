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
public class RentCollectActivity extends Activity implements OnClickListener {

    private EditText mHouseAddressEt;
    private EditText mHouseholderNameEt;
    private EditText mHouseholderIdNumberEt;
    private EditText mHouseholderPhoneEt;
    private EditText mHouseNumberEt;
    private TextView mRentStartTimeTv;
    private TextView mRentEndTimeTv;
    private EditText mTenantNameEt;
    private EditText mTenantIdNumberEt;
    private EditText mTenantPhoneEt;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, RentCollectActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_collect);
        init();
    }

    private void init() {
        mHouseAddressEt = (EditText) findViewById(R.id.rc_house_address);
        mHouseholderNameEt = (EditText) findViewById(R.id.rc_householder_name);
        mHouseholderIdNumberEt = (EditText) findViewById(R.id.rc_householder_id_number);
        mHouseholderPhoneEt = (EditText) findViewById(R.id.rc_householder_phone);
        mHouseNumberEt = (EditText) findViewById(R.id.rc_house_number);
        mRentStartTimeTv = (TextView) findViewById(R.id.rc_start_time);
        mRentEndTimeTv = (TextView) findViewById(R.id.rc_end_time);
        mTenantNameEt = (EditText) findViewById(R.id.rc_tenant_name);
        mTenantIdNumberEt = (EditText) findViewById(R.id.rc_tenant_id_number);
        mTenantPhoneEt = (EditText) findViewById(R.id.rc_tenant_phone);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.rent_collect);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.complete);
        findViewById(R.id.rc_rent_start_time).setOnClickListener(this);
        findViewById(R.id.rc_rent_end_time).setOnClickListener(this);
        findViewById(R.id.rc_add).setOnClickListener(this);
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
            case R.id.rc_rent_start_time:
                ToastUtils.showToast(this, "选择开始日期");
                break;
            case R.id.rc_rent_end_time:
                ToastUtils.showToast(this, "选择结束日期");
                break;
        }
    }
}
