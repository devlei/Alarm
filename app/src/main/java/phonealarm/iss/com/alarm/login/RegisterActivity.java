package phonealarm.iss.com.alarm.login;

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
 * Created by weizhilei on 2017/9/24.
 */
public class RegisterActivity extends Activity implements OnClickListener {

    private EditText mPhoneEt;
    private EditText mPasswordEt;
    private EditText mConfirmPasswordEt;
    private TextView mVersionTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, RegisterActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init views
        mPhoneEt = (EditText) findViewById(R.id.register_phone);
        mPasswordEt = (EditText) findViewById(R.id.register_password);
        mConfirmPasswordEt = (EditText) findViewById(R.id.register_password_confirm);
        mVersionTv = (TextView) findViewById(R.id.register_version);

        //set listener
        findViewById(R.id.register_back).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register:
                ToastUtils.showToast(this, R.string.register);
                break;
        }
    }
}
