package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.BaseResponseBean;
import phonealarm.iss.com.alarm.bean.contact.GetContactListBean;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.personal.adapter.EmergencyContactAdapter;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class EmergencyContactActivity extends Activity implements OnClickListener {

    private RecyclerView mEmergencyContactRv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, EmergencyContactActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        init();
        loadData();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.emergency_contact);
        TextView addTv = (TextView) findViewById(R.id.title_other);
        addTv.setText(R.string.add);
        addTv.setOnClickListener(this);
        mEmergencyContactRv = (RecyclerView) findViewById(R.id.emergencyContact);

        findViewById(R.id.title_back).setOnClickListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mEmergencyContactRv.setLayoutManager(llm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                IntentUtils.openEmergencyContactAdd(this);
                break;
        }
    }

    private void loadData() {
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_GET_CONTACTS)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<GetContactListBean>() {

                        @Override
                        public void onStart() {}

                        @Override
                        public void onNext(GetContactListBean postBean) {
                            if (postBean != null) {
                                if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                    if (postBean.getContactslist() != null) {
                                        EmergencyContactAdapter adapter = new EmergencyContactAdapter(
                                                postBean.getContactslist().getContacts());
                                        mEmergencyContactRv.setAdapter(adapter);
                                    }
                                } else {
                                    ToastUtils.showToast(EmergencyContactActivity.this, postBean.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
