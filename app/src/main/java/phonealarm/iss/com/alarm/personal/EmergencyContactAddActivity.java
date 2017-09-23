package phonealarm.iss.com.alarm.personal;

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
 * Created by weizhilei on 2017/9/23.
 */
public class EmergencyContactAddActivity extends Activity implements OnClickListener {

    private EditText mNameEt;
    private EditText mRelationEt;
    private EditText mPhoneEt;
    private EditText mAddressEt;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, EmergencyContactAddActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact_add);
        init();

    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.emergency_contact);
        mNameEt = (EditText) findViewById(R.id.eca_name);
        mRelationEt = (EditText) findViewById(R.id.eca_relation);
        mPhoneEt = (EditText) findViewById(R.id.eca_phone);
        mAddressEt = (EditText) findViewById(R.id.eca_address);

        findViewById(R.id.title_other).setVisibility(View.GONE);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.eca_address_book).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.eca_address_book:
                ToastUtils.showToast(this, "通讯录");
                break;
        }
    }

}
