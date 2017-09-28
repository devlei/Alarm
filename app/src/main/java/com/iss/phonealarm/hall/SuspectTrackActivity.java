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
import com.iss.phonealarm.bean.suspect.SuspectInfo;
import com.iss.phonealarm.utils.GlideUtils;
import com.iss.phonealarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class SuspectTrackActivity extends Activity implements OnClickListener {

    private static final String SUSPECT_INFO = "suspect_info";

    private TextView mNameTv;
    private TextView mTimeTv;
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
     * @param suspectInfo
     */
    public static void open(Context context, SuspectInfo suspectInfo) {
        if (context != null) {
            Intent intent = new Intent(context, SuspectTrackActivity.class);
            intent.putExtra(SUSPECT_INFO, suspectInfo);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspect_track);
        init();
        setData();
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.suspect_track);
        TextView reportTv = (TextView) findViewById(R.id.title_other);
        reportTv.setText(R.string.report);
        mNameTv = (TextView) findViewById(R.id.st_name);
        mTimeTv = (TextView) findViewById(R.id.st_time);
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

    private void setData() {
        SuspectInfo suspectInfo = (SuspectInfo) getIntent().getSerializableExtra(SUSPECT_INFO);
        if (suspectInfo != null) {
            mNameTv.setText(suspectInfo.getSuspect_name());
            mTimeTv.setText(suspectInfo.getPursuit_time());
            mGenderTv.setText(suspectInfo.getSuspect_sex());
            mNationTv.setText("汉");
            mBirthDateTv.setText(suspectInfo.getSuspect_birth());
            mIdNumberTv.setText(suspectInfo.getSuspect_card());
            mWantedLevelTv.setText(suspectInfo.getSuspect_rank());
            mCaseCategoryTv.setText(suspectInfo.getSuspect_fanzui());
            mSignalmentTv.setText(suspectInfo.getSuspect_feature());
            mCaseDescTv.setText(suspectInfo.getSuspect_case());
            GlideUtils.loadBackgroundImage(this, suspectInfo.getSuspect_purl(), R.drawable.icon_header_default,
                    mHeaderIv);
            mRewardTv.setText(suspectInfo.getSuspect_award());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                // TODO: 2017/9/26 weizhilei 无举报接口
                ToastUtils.showToast(this, R.string.report);
                break;
        }
    }
}
