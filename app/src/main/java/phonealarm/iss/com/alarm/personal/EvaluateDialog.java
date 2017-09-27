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
import com.thoughtworks.xstream.XStream;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.BaseResponseBean;
import phonealarm.iss.com.alarm.bean.ResponseMessageBean;
import phonealarm.iss.com.alarm.bean.alarmjudge.AlarmJudgeBean;
import phonealarm.iss.com.alarm.bean.interactquery.InterQueryAttrConverter;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/26.
 */
public class EvaluateDialog extends Dialog implements OnCheckedChangeListener, OnClickListener {

    private RadioButton mGoodBtn;
    private RadioButton mMiddleBtn;
    private RadioButton mBadBtn;
    private EditText mContentEt;

    private String mAlarmId;
    private String mAlarmPhone;

    public static Dialog show(Context context, String alarmId, String alarmPhone) {
        EvaluateDialog dialog = null;
        if (context != null) {
            dialog = new EvaluateDialog(context, alarmId, alarmPhone);
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dialog;
    }

    public EvaluateDialog(@NonNull Context context, String alarmId, String alarmPhone) {
        super(context, R.style.ThemeTransparent);
        this.mAlarmId = alarmId;
        this.mAlarmPhone = alarmPhone;
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
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            int level = 0;
            if (mGoodBtn.isChecked()) level = 0;
            if (mMiddleBtn.isChecked()) level = 1;
            if (mBadBtn.isChecked()) level = 2;

            AlarmJudgeBean alarmJudgeBean = new AlarmJudgeBean();
            alarmJudgeBean.setAlarm_id(mAlarmId);
            alarmJudgeBean.setPhone(mAlarmPhone);
            alarmJudgeBean.setAssess_level(String.valueOf(level));
            alarmJudgeBean.setAssess_content(mContentEt.getText().toString());

            XStream xStream = new XStream();
            xStream.autodetectAnnotations(true);
            xStream.registerConverter(new InterQueryAttrConverter());
            String xmlString = xStream.toXML(alarmJudgeBean);
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_ALARM_EVALUATE)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
                    .addParam("value", xmlString)
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {

                        @Override
                        public void onStart() {}

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                dismiss();
                            } else {
                                ToastUtils.showToast(getContext(), postBean.getMessage());
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

}
