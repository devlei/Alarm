package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class VehicleTrackActivity extends Activity implements OnClickListener {

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
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, VehicleTrackActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_track);
        init();

        mCarDescTv.setText(
                "2017年10月20号18时，发现姓名A从位于北京市朝阳区和平街15区附近走失，姓名A，陕西省安康市人，走失时身高100cm" +
                        "左右，体态教授，短发，上身蓝色半袖，下渗川河色短裤，娇喘白色运动鞋，未携带其他物品。");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                ToastUtils.showToast(this, R.string.report);
                break;
        }
    }
}
