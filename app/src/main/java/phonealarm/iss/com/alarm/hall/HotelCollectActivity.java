package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thoughtworks.xstream.XStream;
import phonealarm.iss.com.alarm.AlarmApplication;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.bean.BaseResponseBean;
import phonealarm.iss.com.alarm.bean.ResponseMessageBean;
import phonealarm.iss.com.alarm.bean.infocollection.InfoCollectBean;
import phonealarm.iss.com.alarm.bean.infocollection.LiablePerson;
import phonealarm.iss.com.alarm.bean.infocollection.LiablePersonList;
import phonealarm.iss.com.alarm.bean.uploadalarm.UpLoadAttrConverter;
import phonealarm.iss.com.alarm.network.UrlSet;
import phonealarm.iss.com.alarm.network.callback.CallBack;
import phonealarm.iss.com.alarm.network.http.util.OkHttpUtils;
import phonealarm.iss.com.alarm.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class HotelCollectActivity extends Activity implements OnClickListener {

    private EditText mHotelAddressEt;
    private EditText mHotelNameEt;
    private EditText mRoomNameEt;
    private EditText mHotelPhoneEt;
    private LinearLayout mPassengerContainerTv;

    private List<View> mPassengerViewList = new ArrayList<View>();

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, HotelCollectActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_collect);
        init();
        addPassenger();
    }

    private void init() {
        mHotelAddressEt = (EditText) findViewById(R.id.hc_hotel_address);
        mHotelNameEt = (EditText) findViewById(R.id.hc_hotel_name);
        mRoomNameEt = (EditText) findViewById(R.id.hc_room_name);
        mHotelPhoneEt = (EditText) findViewById(R.id.hc_hotel_phone);
        mPassengerContainerTv = (LinearLayout) findViewById(R.id.hc_passenger_container);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.hotel_collect);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.complete);
        findViewById(R.id.hc_add).setOnClickListener(this);
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
            case R.id.hc_add:
                addPassenger();
                break;
        }
    }

    /**
     * 添加旅客
     */
    private void addPassenger() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_passenger, mPassengerContainerTv, false);
        mPassengerContainerTv.addView(view);
        mPassengerViewList.add(view);
    }

    private void collect() {
        if (AlarmApplication.mAlarmApplication.isLogin()) {
            if (TextUtils.isEmpty(mHotelAddressEt.getText())) {
                ToastUtils.showToast(this, R.string.address_hint);
                return;
            }
            if (TextUtils.isEmpty(mHotelNameEt.getText())) {
                ToastUtils.showToast(this, R.string.hotel_name_hint);
                return;
            }
            if (TextUtils.isEmpty(mRoomNameEt.getText())) {
                ToastUtils.showToast(this, R.string.room_name_hint);
                return;
            }
            //循环检查必要信息输入是否为空
            for (View view : mPassengerViewList) {
                EditText passengerNameEt = (EditText) view.findViewById(R.id.hc_passenger_name);
                EditText passengerIdNumberEt = (EditText) view.findViewById(R.id.hc_passenger_id_number);
                if (TextUtils.isEmpty(passengerNameEt.getText())) {
                    ToastUtils.showToast(this, R.string.name_hint);
                    return;
                }
                if (TextUtils.isEmpty(passengerIdNumberEt.getText())) {
                    ToastUtils.showToast(this, R.string.id_number_hint);
                    return;
                }
            }
            //旅馆信息
            InfoCollectBean infoCollectBean = new InfoCollectBean();
            infoCollectBean.setSHANGBAOID(UUID.randomUUID().toString());
            infoCollectBean.setHOTEL_ADRESS(mHotelAddressEt.getText().toString());
            infoCollectBean.setHOTEL_NAME(mHotelNameEt.getText().toString());
            infoCollectBean.setHOME_NAME(mRoomNameEt.getText().toString());
            if (!TextUtils.isEmpty(mHotelPhoneEt.getText()))
                infoCollectBean.setHOTEL_TELE(mHotelPhoneEt.getText().toString());
            //旅客信息
            List<LiablePerson> liablePersonList = new ArrayList<>();
            for (View view : mPassengerViewList) {
                EditText passengerNameEt = (EditText) view.findViewById(R.id.hc_passenger_name);
                EditText passengerIdNumberEt = (EditText) view.findViewById(R.id.hc_passenger_id_number);
                EditText passengerPhoneEt = (EditText) view.findViewById(R.id.hc_passenger_phone);

                LiablePerson liablePerson = new LiablePerson();
                liablePerson.setPNAME(passengerNameEt.getText().toString());
                liablePerson.setPIDCARD(passengerIdNumberEt.getText().toString());
                if (!TextUtils.isEmpty(passengerPhoneEt.getText()))
                    liablePerson.setPTELE(passengerPhoneEt.getText().toString());
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
                    .url(UrlSet.URL_HOTEL_COLLECT)
                    .addParam("userid", AlarmApplication.mAlarmApplication.getUserId())
                    .addParam("value", xmlString)
                    .build()
                    .buildRequestCall()
                    .execute(new CallBack<ResponseMessageBean>() {

                        @Override
                        public void onStart() {}

                        @Override
                        public void onNext(ResponseMessageBean postBean) {
                            if (postBean != null) {
                                if (postBean.getResult() == BaseResponseBean.RESULT_SUCCESS) {
                                    ToastUtils.showToast(HotelCollectActivity.this, "采集成功");
                                    finish();
                                } else {
                                    ToastUtils.showToast(HotelCollectActivity.this, postBean.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onComplete() {}
                    });
        }
    }

}
