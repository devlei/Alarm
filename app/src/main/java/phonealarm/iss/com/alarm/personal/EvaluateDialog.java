package phonealarm.iss.com.alarm.personal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/26.
 */
public class EvaluateDialog extends Dialog implements OnCheckedChangeListener, OnClickListener {

    private RadioButton mGoodBtn;
    private RadioButton mMiddleBtn;
    private RadioButton mBadBtn;
    private EditText mContentEt;

    public static Dialog show(Context context) {
        EvaluateDialog dialog = null;
        if (context != null) {
            dialog = new EvaluateDialog(context);
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dialog;
    }

    public EvaluateDialog(@NonNull Context context) {
        super(context, R.style.ThemeTransparent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_evaluate);

        mGoodBtn = (RadioButton) findViewById(R.id.evaluate_good);
        mMiddleBtn = (RadioButton) findViewById(R.id.evaluate_middle);
        mBadBtn = (RadioButton) findViewById(R.id.evaluate_bad);
        mContentEt = (EditText) findViewById(R.id.evaluate_content);

        mGoodBtn.setOnCheckedChangeListener(this);
        mMiddleBtn.setOnCheckedChangeListener(this);
        mBadBtn.setOnCheckedChangeListener(this);
        findViewById(R.id.evaluate_cancel).setOnClickListener(this);
        findViewById(R.id.evaluate_submit).setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.evaluate_good:
                    mMiddleBtn.setChecked(false);
                    mBadBtn.setChecked(false);
                    break;
                case R.id.evaluate_middle:
                    mGoodBtn.setChecked(false);
                    mBadBtn.setChecked(false);
                    break;
                case R.id.evaluate_bad:
                    mGoodBtn.setChecked(false);
                    mMiddleBtn.setChecked(false);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.evaluate_cancel:
                dismiss();
                break;
            case R.id.evaluate_submit:
                submit();
                break;
        }
    }

    private void submit() {
        if (TextUtils.isEmpty(mContentEt.getText())) {
            ToastUtils.showToast(getContext(), R.string.evaluate_hint);
            return;
        }
        // TODO: 2017/9/26 weizhilei 请求提交接口
        dismiss();
    }

}
