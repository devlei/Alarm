package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.beLost.BeLostBean;
import phonealarm.iss.com.alarm.bean.carinfo.InformationBean;
import phonealarm.iss.com.alarm.bean.caseinfo.CasesInfoListBean;
import phonealarm.iss.com.alarm.bean.lost.LostBean;
import phonealarm.iss.com.alarm.bean.searchalarm.AlarmInfoBeanList;
import phonealarm.iss.com.alarm.bean.suspect.SuspectBean;
import phonealarm.iss.com.alarm.hall.adapter.CommonSearchAdapter;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.utils.IntentUtils;
import phonealarm.iss.com.alarm.utils.LogUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class CommonSearchActivity extends Activity implements OnClickListener, TextWatcher {

    private EditText mSearchEt;
    private RecyclerView mRv;

    /**
     * open
     *
     * @param context
     * @param typeResId
     */
    public static void open(Context context, int typeResId) {
        if (context != null) {
            Intent intent = new Intent(context, CommonSearchActivity.class);
            intent.putExtra(context.getString(R.string.key_type), typeResId);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_search);
        init();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        TextView otherTv = (TextView) findViewById(R.id.title_other);
        mSearchEt = (EditText) findViewById(R.id.common_search);
        mRv = (RecyclerView) findViewById(R.id.commonSearch);

        //set listener
        findViewById(R.id.title_back).setOnClickListener(this);
        otherTv.setVisibility(View.GONE);
        mSearchEt.addTextChangedListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRv.setLayoutManager(llm);

        int typeResId = getIntent().getIntExtra(getString(R.string.key_type), R.integer.type_cases);
        switch (typeResId) {
            case R.integer.type_cases:
                titleTv.setText(R.string.cases);
                getCasesData();
                break;
            case R.integer.type_vehicle_track:
                titleTv.setText(R.string.vehicle_track);
                getVehicleTrackData();
                break;
            case R.integer.type_suspect_track:
                titleTv.setText(R.string.suspect_track);
                getSuspectTrackData();
                break;
            case R.integer.type_people_lost:
                titleTv.setText(R.string.people_lost);
                getPeopleLostData();
                break;
            case R.integer.type_lost_found:
                titleTv.setText(R.string.lost_found);
                getLostFoundData();
                break;
            case R.integer.type_police_interact:
                titleTv.setText(R.string.police_interact);
                otherTv.setVisibility(View.VISIBLE);
                otherTv.setText(R.string.add);
                otherTv.setOnClickListener(this);
                // TODO: 2017/9/25 weizhilei 无列表接口
                break;
            case R.integer.type_alarm_history:
                titleTv.setText(R.string.alarm_history);
                getAlarmHistoryData();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                IntentUtils.openPoliceInteractAdd(this);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO: 2017/9/23 weizhilei 搜索更新列表
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_ENTER:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(mSearchEt.getWindowToken(), 0);

                }
                return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 获取要案数据
     */
    private void getCasesData() {
        OkHttpUtils.getBuilder()
                .url(UrlSet.URL_CASES)
                .build()
                .buildRequestCall()
                .execute(new CallBack<CasesInfoListBean>() {

                    @Override
                    public void onStart() {}

                    @Override
                    public void onNext(CasesInfoListBean getBean) {
                        if (getBean != null) {
                            CommonSearchAdapter adapter = new CommonSearchAdapter(R.integer.type_cases);
                            if (getBean.getCasesInfoList() != null) {
                                adapter.setCasesInfoList(getBean.getCasesInfoList().getCasesInfoList());
                                mRv.setAdapter(adapter);
                                LogUtils.d(getBean.getCasesInfoList().toString());
                            }
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * 获取车辆追踪数据
     */
    private void getVehicleTrackData() {
        OkHttpUtils.getBuilder()
                .url(UrlSet.URL_VEHICLE_TRACK)
                .build()
                .buildRequestCall()
                .execute(new CallBack<InformationBean>() {

                    @Override
                    public void onStart() { }

                    @Override
                    public void onNext(InformationBean getBean) {
                        if (getBean != null && getBean.getCarInfoList() != null) {
                            CommonSearchAdapter adapter = new CommonSearchAdapter(R.integer.type_vehicle_track);
                            adapter.setCarInfoList(getBean.getCarInfoList().getCarInfoList());
                            mRv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * 获取疑犯追踪数据
     */
    private void getSuspectTrackData() {
        OkHttpUtils.getBuilder()
                .url(UrlSet.URL_SUSPECT_TRACK)
                .build()
                .buildRequestCall()
                .execute(new CallBack<SuspectBean>() {

                    @Override
                    public void onStart() {}

                    @Override
                    public void onNext(SuspectBean getBean) {
                        if (getBean != null && getBean.getSuspectInfoList() != null) {
                            CommonSearchAdapter adapter = new CommonSearchAdapter(R.integer.type_suspect_track);
                            adapter.setSuspectInfoList(getBean.getSuspectInfoList().getSuspectInfoList());
                            mRv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * 获取人员走失数据
     */
    private void getPeopleLostData() {
        OkHttpUtils.getBuilder()
                .url(UrlSet.URL_PEOPLE_LOST)
                .build()
                .buildRequestCall()
                .execute(new CallBack<BeLostBean>() {

                    @Override
                    public void onStart() {}

                    @Override
                    public void onNext(BeLostBean getBean) {
                        if (getBean != null && getBean.getAllBelost() != null) {
                            CommonSearchAdapter adapter = new CommonSearchAdapter(R.integer.type_people_lost);
                            adapter.setBelostInfoList(getBean.getAllBelost().getBeLostInfoList());
                            mRv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * 获取遗失招领数据
     */
    private void getLostFoundData() {
        OkHttpUtils.getBuilder()
                .url(UrlSet.URL_LOST_FOUND)
                .build()
                .buildRequestCall()
                .execute(new CallBack<LostBean>() {

                    @Override
                    public void onStart() {}

                    @Override
                    public void onNext(LostBean getBean) {
                        if (getBean != null && getBean.getAllLost() != null) {
                            CommonSearchAdapter adapter = new CommonSearchAdapter(R.integer.type_lost_found);
                            adapter.setLostInfoList(getBean.getAllLost().getLostInfoList());
                            mRv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * 获取报警历史数据
     */
    private void getAlarmHistoryData() {
        if (!AlarmApplication.mAlarmApplication.isLogin() || AlarmApplication.mUserInfo == null) {
            return;
        }
        OkHttpUtils.getBuilder()
                .url(UrlSet.getAlarmHistoryUrl(AlarmApplication.mUserInfo.getUserid()))
                .build()
                .buildRequestCall()
                .execute(new CallBack<AlarmInfoBeanList>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onNext(AlarmInfoBeanList getBean) {
                        if (getBean != null) {
                            CommonSearchAdapter adapter = new CommonSearchAdapter(R.integer.type_alarm_history);
                            adapter.setAlarmInfoList(getBean.getAlarminfo());
                            mRv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

}
