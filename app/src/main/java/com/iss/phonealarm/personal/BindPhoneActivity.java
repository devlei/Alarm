package com.iss.phonealarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.iss.phonealarm.AlarmApplication;
import com.iss.phonealarm.R;
import com.iss.phonealarm.personal.observer.UserAdapterObserver;
import com.iss.phonealarm.personal.observer.UserObserverHelper;
import com.iss.phonealarm.utils.IntentUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class BindPhoneActivity extends Activity implements OnClickListener {

    private TextView mPhoneTv;

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
        setContentView(R.layout.activity_bind_phone);
        init();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.bind_phone);
        mPhoneTv = (TextView) findViewById(R.id.bp_phone);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setVisibility(View.GONE);
        findViewById(R.id.bp_change).setOnClickListener(this);

        mPhoneTv.setText(AlarmApplication.mAlarmApplication.getUserId());

        UserObserverHelper.getInstance().addUserObserver(mUserAdapterObserver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.bp_change:
                IntentUtils.openChangePhone(this);
                break;
        }
    }

    private UserAdapterObserver mUserAdapterObserver = new UserAdapterObserver() {
        @Override
        public void onUserInfoChange() {
            if (AlarmApplication.mUserInfo != null) {
                mPhoneTv.setText(AlarmApplication.mUserInfo.getUser_userid());
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserObserverHelper.getInstance().removeUserObserver(mUserAdapterObserver);
    }

}
