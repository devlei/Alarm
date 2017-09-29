package com.iss.phonealarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.carinfo.CarInfo;
import com.iss.phonealarm.utils.GlideUtils;
import com.iss.phonealarm.utils.IntentUtils;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class VehicleTrackActivity extends Activity implements OnClickListener {

    private static final String CAR_INFO = "car_info";

    private TextView mCarNumberTv;
    private TextView mCarTimeTv;
    private TextView mCarTypeTv;
    private TextView mCarBrandTv;
    private TextView mCarColorTv;
    private TextView mCarDescTv;
    private ImageView mCarIv;

    /**
     * open
     *
     * @param context
     * @param carInfo
     */
    public static void open(Context context, CarInfo carInfo) {
        if (context != null) {
            Intent intent = new Intent(context, VehicleTrackActivity.class);
            intent.putExtra(CAR_INFO, carInfo);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_track);
        init();
        setData();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.vehicle_track);
        TextView reportTv = (TextView) findViewById(R.id.title_other);
        reportTv.setText(R.string.report);
        mCarNumberTv = (TextView) findViewById(R.id.vt_car_number);
        mCarTimeTv = (TextView) findViewById(R.id.vt_cat_time);
        mCarTypeTv = (TextView) findViewById(R.id.vt_car_type);
        mCarBrandTv = (TextView) findViewById(R.id.vt_car_brand);
        mCarColorTv = (TextView) findViewById(R.id.vt_car_color);
        mCarDescTv = (TextView) findViewById(R.id.vt_car_desc);
        mCarIv = (ImageView) findViewById(R.id.vt_car_img);

        //set listener
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
    }

    private void setData() {
        CarInfo carInfo = (CarInfo) getIntent().getSerializableExtra(CAR_INFO);
        if (carInfo != null) {
            mCarNumberTv.setText(carInfo.getCar_num());
            mCarTimeTv.setText(carInfo.getPursuit_time());
            mCarTypeTv.setText(carInfo.getCar_type());
            mCarBrandTv.setText(carInfo.getCar_brand());
            mCarColorTv.setText(carInfo.getCar_color());
            mCarDescTv.setText(carInfo.getCar_info());
            GlideUtils.loadBackgroundImage(this, carInfo.getCar_purl(), R.drawable.icon_header_default, mCarIv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                IntentUtils.openFastAlarmActivity(this, R.integer.type_vehicle_track);
                break;
        }
    }
}
