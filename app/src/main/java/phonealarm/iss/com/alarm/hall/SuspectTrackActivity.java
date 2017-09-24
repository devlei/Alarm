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
public class SuspectTrackActivity extends Activity implements OnClickListener {

    private TextView mNameTv;
    private TextView mGenderTv;
    private TextView mNationTv;
    private TextView mBirthDateTv;
    private TextView mIdNumberTv;
    private TextView mWantedLevelTv;
    private TextView mCaseCategoryTv;
    private TextView mSignalmentTv;
    private TextView mCaseDescTv;
    private ImageView mHeaderIv;
    private TextView mRewardTv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, SuspectTrackActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspect_track);
        init();

        mCaseDescTv.setText(
                "2017年10月20号18时，发现姓名A从位于北京市朝阳区和平街15区附近走失，姓名A，陕西省安康市人，走失时身高100cm" +
                        "左右，体态教授，短发，上身蓝色半袖，下渗川河色短裤，娇喘白色运动鞋，未携带其他物品。");
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.suspect_track);
        TextView reportTv = (TextView) findViewById(R.id.title_other);
        reportTv.setText(R.string.report);
        mNameTv = (TextView) findViewById(R.id.st_name);
        mGenderTv = (TextView) findViewById(R.id.st_gender);
        mNationTv = (TextView) findViewById(R.id.st_nation);
        mBirthDateTv = (TextView) findViewById(R.id.st_birth_date);
        mIdNumberTv = (TextView) findViewById(R.id.st_id_number);
        mWantedLevelTv = (TextView) findViewById(R.id.st_wanted_level);
        mCaseCategoryTv = (TextView) findViewById(R.id.st_case_category);
        mSignalmentTv = (TextView) findViewById(R.id.st_signalment);
        mCaseDescTv = (TextView) findViewById(R.id.st_case_desc);
        mHeaderIv = (ImageView) findViewById(R.id.st_header);
        mRewardTv = (TextView) findViewById(R.id.st_reward);

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
