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
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class LoginActivity extends Activity implements OnClickListener {

    private EditText mUserEt;
    private EditText mPasswordEt;
    private TextView mVersionTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init views
        mUserEt = (EditText) findViewById(R.id.login_user);
        mPasswordEt = (EditText) findViewById(R.id.login_user);
        mVersionTv = (TextView) findViewById(R.id.login_version);

        //set listener
        findViewById(R.id.login_confirm).setOnClickListener(this);
        findViewById(R.id.login_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_confirm:
                ToastUtils.showToast(this, R.string.login);
                break;
            case R.id.login_register:
                IntentUtils.openRegister(this);
                break;
        }
    }
}