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
public class PeopleLostActivity extends Activity implements OnClickListener {

    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mGenderTv;
    private TextView mAgeTv;
    private TextView mMissLocationTv;
    private TextView mMissTimeTv;
    private TextView mBirthDateTv;
    private TextView mHeightWeightTv;
    private TextView mHairColorTv;
    private TextView mDetailTv;
    private ImageView mHeaderIv;
    private TextView mFamilyNameTv;
    private TextView mFamilyPhoneTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, PeopleLostActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_lost);
        init();

        mDetailTv.setText(
                "2017年10月20号18时，发现姓名A从位于北京市朝阳区和平街15区附近走失，姓名A，陕西省安康市人，走失时身高100cm" +
                        "左右，体态教授，短发，上身蓝色半袖，下渗川河色短裤，娇喘白色运动鞋，未携带其他物品。");
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.people_lost);
        TextView reportTv = (TextView) findViewById(R.id.title_other);
        reportTv.setText(R.string.report);
        mNameTv = (TextView) findViewById(R.id.pl_name);
        mTimeTv = (TextView) findViewById(R.id.pl_time);
        mGenderTv = (TextView) findViewById(R.id.pl_gender);
        mAgeTv = (TextView) findViewById(R.id.pl_age);
        mMissLocationTv = (TextView) findViewById(R.id.pl_miss_location);
        mMissTimeTv = (TextView) findViewById(R.id.pl_miss_time);
        mBirthDateTv = (TextView) findViewById(R.id.pl_birth_date);
        mHeightWeightTv = (TextView) findViewById(R.id.pl_height_weight);
        mHairColorTv = (TextView) findViewById(R.id.pl_hair);
        mDetailTv = (TextView) findViewById(R.id.pl_detail);
        mHeaderIv = (ImageView) findViewById(R.id.pl_header);
        mFamilyNameTv = (TextView) findViewById(R.id.pl_family_name);
        mFamilyPhoneTv = (TextView) findViewById(R.id.pl_family_phone);

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
