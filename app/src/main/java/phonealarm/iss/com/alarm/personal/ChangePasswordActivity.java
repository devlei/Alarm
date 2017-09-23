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
public class ChangePasswordActivity extends Activity implements OnClickListener {

    private TextView mTitleTv;
    private TextView mConfirmTv;
    private EditText mOldPasswordTv;
    private EditText mNewPasswordTv;
    private EditText mConfirmPasswordTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, ChangePasswordActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
    }

    private void init() {
        mTitleTv = (TextView) findViewById(R.id.title_name);
        mConfirmTv = (TextView) findViewById(R.id.title_other);
        mOldPasswordTv = (EditText) findViewById(R.id.password_old);
        mNewPasswordTv = (EditText) findViewById(R.id.password_new);
        mConfirmPasswordTv = (EditText) findViewById(R.id.password_confirm);

        mTitleTv.setText(R.string.change_password);
        mConfirmTv.setText(R.string.confirm);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                ToastUtils.showToast(this, R.string.confirm);
                break;
        }
    }
}
