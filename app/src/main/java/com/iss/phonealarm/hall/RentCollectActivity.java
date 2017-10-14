package com.iss.phonealarm.hall;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iss.phonealarm.AlarmApplication;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.BaseResponseBean;
import com.iss.phonealarm.bean.ResponseMessageBean;
import com.iss.phonealarm.bean.infocollection.InfoCollectBean;
import com.iss.phonealarm.bean.infocollection.LiablePerson;
import com.iss.phonealarm.bean.infocollection.LiablePersonList;
import com.iss.phonealarm.bean.uploadalarm.UpLoadAttrConverter;
import com.iss.phonealarm.network.UrlSet;
import com.iss.phonealarm.network.callback.CallBack;
import com.iss.phonealarm.network.http.util.OkHttpUtils;
import com.iss.phonealarm.utils.ToastUtils;
import com.iss.phonealarm.utils.Utils;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class RentCollectActivity extends Activity implements OnClickListener {

    private static final int TYPE_START_DATE = 0;
    private static final int TYPE_END_DATE = 1;

    private EditText mHouseAddressEt;
    private EditText mHouseholderNameEt;
    private EditText mHouseholderIdNumberEt;
    private EditText mHouseholderPhoneEt;
    private EditText mHouseNumberEt;
    private TextView mRentStartTimeTv;
    private TextView mRentEndTimeTv;
    private LinearLayout mTenantContainerTv;

    private List<View> mTenantViewList = new ArrayList<View>();

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, RentCollectActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_collect);
        init();
        addTenant();
    }

    private void init() {
        mHouseAddressEt = (EditText) findViewById(R.id.rc_house_address);
        mHouseholderNameEt = (EditText) findViewById(R.id.rc_householder_name);
        mHouseholderIdNumberEt = (EditText) findViewById(R.id.rc_householder_id_number);
        mHouseholderPhoneEt = (EditText) findViewById(R.id.rc_householder_phone);
        mHouseNumberEt = (EditText) findViewById(R.id.rc_house_number);
        mRentStartTimeTv = (TextView) findViewById(R.id.rc_start_time);
        mRentEndTimeTv = (TextView) findViewById(R.id.rc_end_time);
        mTenantContainerTv = (LinearLayout) findViewById(R.id.rc_tenant_container);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.rent_collect);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.complete);
        findViewById(R.id.rc_rent_start_time).setOnClickListener(this);
        findViewById(R.id.rc_rent_end_time).setOnClickListener(this);
        findViewById(R.id.rc_add).setOnClickListener(this);
    }

    /**
     * 添加租户
     */
    private void addTenant() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tenant, mTenantContainerTv, false);
        mTenantContainerTv.addView(view);
        view.findViewById(R.id.delete).setOnClickListener(this);
        mTenantViewList.add(view);
    }

    private void delete(View v) {
        if (null != v) {
            ViewGroup parent = (ViewGroup) v.getParent().getParent().getParent();
            if (null != parent) {
                if (null != parent.getTag() && parent.getTag().equals("itemRoot")) {
                    mTenantViewList.remove(parent);
                    mTenantContainerTv.removeView(parent);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                collect();
                break;
            case R.id.rc_rent_start_time:
                showDatePickerDialog(TYPE_START_DATE);
                break;
            case R.id.rc_rent_end_time:
                showDatePickerDialog(TYPE_END_DATE);
                break;
            case R.id.rc_add:
                addTenant();
                break;
            case R.id.delete:
                delete(v);
                break;
        }
    }

    /**
     * 日期选择
     *
     * @param type
     */
    private void showDatePickerDialog(final int type) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (type == TYPE_START_DATE) {
                    mRentStartTimeTv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                } else {
                    mRentEndTimeTv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        try {
            datePickerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void collect() {
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            if (TextUtils.isEmpty(mHouseAddressEt.getText())) {
                ToastUtils.showToast(this, R.string.address_hint);
                return;
            }
            if (TextUtils.isEmpty(mHouseholderNameEt.getText())) {
                ToastUtils.showToast(this, R.string.householder_name_hint);
                return;
            }
            if (TextUtils.isEmpty(mHouseholderIdNumberEt.getText())) {
                ToastUtils.showToast(this, R.string.id_number_hint);
                return;
            }
            for (View view : mTenantViewList) {
                EditText tenantNameEt = (EditText) view.findViewById(R.id.rc_tenant_name);
                EditText tenantIdNumberEt = (EditText) view.findViewById(R.id.rc_tenant_id_number);
                //EditText tenantPhoneEt = (EditText) view.findViewById(R.id.rc_tenant_phone);
                if (TextUtils.isEmpty(tenantNameEt.getText())) {
                    ToastUtils.showToast(this, R.string.name_hint);
                    return;
                }
                if (TextUtils.isEmpty(tenantIdNumberEt.getText())) {
                    ToastUtils.showToast(this, R.string.id_number_hint);
                    return;
                }
                if (!Utils.isMobile(tenantIdNumberEt.getText().toString())) {
                    ToastUtils.showToast(this, "请检查身份证号" + tenantIdNumberEt.getText() + "是否正确");
                    return;
                }
            }
            //租房房屋信息
            InfoCollectBean infoCollectBean = new InfoCollectBean();
            infoCollectBean.setSHANGBAOID(UUID.randomUUID().toString());
            infoCollectBean.setHOUSEADRESS(mHouseAddressEt.getText().toString());
            infoCollectBean.setFUZENAME(mHouseholderNameEt.getText().toString());
            infoCollectBean.setFUZERENCARD(mHouseholderIdNumberEt.getText().toString());
            if (!TextUtils.isEmpty(mHouseNumberEt.getText()))
                infoCollectBean.setFUZERENHOUSE(mHouseNumberEt.getText().toString());
            infoCollectBean.setFUZETELE(mHouseholderPhoneEt.getText().toString());
            infoCollectBean.setSTARTTIME(mRentStartTimeTv.getText().toString());
            infoCollectBean.setENDTIME(mRentEndTimeTv.getText().toString());
            //租户信息
            List<LiablePerson> liablePersonList = new ArrayList<>();
            for (View view : mTenantViewList) {
                EditText tenantNameEt = (EditText) view.findViewById(R.id.rc_tenant_name);
                EditText tenantIdNumberEt = (EditText) view.findViewById(R.id.rc_tenant_id_number);
                EditText tenantPhoneEt = (EditText) view.findViewById(R.id.rc_tenant_phone);

                LiablePerson liablePerson = new LiablePerson();
                liablePerson.setPNAME(tenantNameEt.getText().toString());
                liablePerson.setPIDCARD(tenantIdNumberEt.getText().toString());
                if (!TextUtils.isEmpty(tenantPhoneEt.getText()))
                    liablePerson.setPTELE(tenantPhoneEt.getText().toString());
                liablePersonList.add(liablePerson);
            }
            LiablePersonList libs = new LiablePersonList();
            libs.setRzeList(liablePersonList);
            infoCollectBean.setLiablePersonList(libs);

            //转换为xml
            XStream xStream = new XStream();
            xStream.autodetectAnnotations(true);
            xStream.registerConverter(new UpLoadAttrConverter());
            String xmlString = xStream.toXML(infoCollectBean).replace("__", "_");

            //请求采集接口
            OkHttpUtils.postBuilder()
                    .url(UrlSet.URL_RENT_COLLECT)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
                    .addParam("value", xmlString)
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {

                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean != null) {
                                if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                    ToastUtils.showToast(RentCollectActivity.this, "采集成功");
                                    finish();
                                } else {
                                    ToastUtils.showToast(RentCollectActivity.this, postBean.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

}
