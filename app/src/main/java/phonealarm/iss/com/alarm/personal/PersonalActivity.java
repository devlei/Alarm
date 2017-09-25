package phonealarm.iss.com.alarm.personal;

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
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class PersonalActivity extends Activity implements OnClickListener {

    private ImageView mHeaderIv;
    private TextView mNickNameTv;
    private TextView mPhoneTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, PersonalActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.personal_center);
        mHeaderIv = (ImageView) findViewById(R.id.personal_header);
        mNickNameTv = (TextView) findViewById(R.id.personal_nickname);
        mPhoneTv = (TextView) findViewById(R.id.personal_phone);

        findViewById(R.id.title_other).setVisibility(View.GONE);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.personal_header).setOnClickListener(this);
        findViewById(R.id.personal_nickname).setOnClickListener(this);
        findViewById(R.id.personal_alarm_history).setOnClickListener(this);
        findViewById(R.id.personal_emergency_contact).setOnClickListener(this);
        findViewById(R.id.personal_near_police).setOnClickListener(this);
        findViewById(R.id.personal_bind_phone).setOnClickListener(this);
        findViewById(R.id.personal_change_password).setOnClickListener(this);
        findViewById(R.id.personal_check_update).setOnClickListener(this);
        findViewById(R.id.personal_about).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.personal_header:
                IntentUtils.openPersonalInfo(this);
                break;
            case R.id.personal_nickname:
                IntentUtils.openChangeNickName(this);
                break;
            case R.id.personal_alarm_history:
                IntentUtils.openCommonSearch(this, R.integer.type_alarm_history);
                break;
            case R.id.personal_emergency_contact:
                IntentUtils.openEmergencyContact(this);
                break;
            case R.id.personal_near_police:
                IntentUtils.openNearPolice(this);
                break;
            case R.id.personal_bind_phone:
                IntentUtils.openBindPhone(this);
                break;
            case R.id.personal_change_password:
                IntentUtils.openChangePassword(this);
                break;
            case R.id.personal_check_update:
                ToastUtils.showToast(this, R.string.check_update);
                break;
            case R.id.personal_about:
                ToastUtils.showToast(this, R.string.about);
                break;
        }
    }
}
