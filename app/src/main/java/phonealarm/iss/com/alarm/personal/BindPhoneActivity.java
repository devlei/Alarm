package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class BindPhoneActivity extends Activity implements OnClickListener {

    //绑定手机号
    private static final int TYPE_BIND = 1;
    //更换手机号
    private static final int TYPE_CHANGE = 2;

    private EditText mPhoneEt;
    private Button mBindBtn;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, BindPhoneActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_change_phone);
        init();

        // TODO: 2017/9/23 weizhilei test data
        //mBindBtn.setTag(TYPE_BIND);
        //mBindBtn.setText(R.string.bind_phone);

        mBindBtn.setTag(TYPE_CHANGE);
        mBindBtn.setText(R.string.change_phone);

    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.bind_phone);
        mPhoneEt = (EditText) findViewById(R.id.bcp_phone);
        mBindBtn = (Button) findViewById(R.id.bcp_submit);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setVisibility(View.GONE);
        findViewById(R.id.bcp_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.bcp_submit:
                handleBind(v);
                break;
        }
    }

    /**
     * 处理绑定按钮逻辑
     *
     * @param view
     */
    private void handleBind(View view) {
        if (view != null && view.getTag() != null) {
            int tag = (int) view.getTag();
            switch (tag) {
                case TYPE_BIND:
                    ToastUtils.showToast(this, R.string.bind_phone);
                    break;
                case TYPE_CHANGE:
                    IntentUtils.openChangePhone(this);
                    break;
            }
        }
    }

}
