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
import phonealarm.iss.com.alarm.LoadingDialog;
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
    private static final int REQUEST_CODE = 1;

    private RecyclerView mEmergencyContactRv;

    private EmergencyContactAdapter mAdapter;

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
                IntentUtils.openEmergencyContactAdd(this, REQUEST_CODE);
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
                        public void onStart() {LoadingDialog.show(EmergencyContactActivity.this);}

                        @Override
                        public void onNext(GetContactListBean postBean) {
                            LoadingDialog.dismissSelf();
                            if (postBean != null) {
                                if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                    if (postBean.getContactslist() != null) {
                                        if (mAdapter == null) {
                                            mAdapter = new EmergencyContactAdapter();
                                            mAdapter.setContactList(postBean.getContactslist().getContacts());
                                            mEmergencyContactRv.setAdapter(mAdapter);
                                        } else {
                                            mAdapter.setContactList(postBean.getContactslist().getContacts());
                                            mAdapter.notifyDataSetChanged();
                                        }
                                    }
                                } else {
                                    ToastUtils.showToast(EmergencyContactActivity.this, postBean.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onComplete() {}

                        @Override
                        public void onError(Throwable e) {LoadingDialog.dismissSelf();}
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            loadData();
        }
    }
}
