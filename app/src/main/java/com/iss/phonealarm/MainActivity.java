package com.iss.phonealarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.iss.phonealarm.hall.WrapGridLayoutManager;
import com.iss.phonealarm.hall.adapter.BenefitAdapter;
import com.iss.phonealarm.hall.adapter.InfoAdapter;
import com.iss.phonealarm.utils.IntentUtils;

/**
 * Created by weizhilei on 2017/9/22.
 */
public class MainActivity extends Activity implements OnClickListener {

    private RecyclerView mInfoRv;
    private RecyclerView mBenefitRv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        AlarmApplication.mainActivity = this;
    }

    /**
     * 初始化
     */
    private void init() {
        mInfoRv = (RecyclerView) findViewById(R.id.hall_info);
        mBenefitRv = (RecyclerView) findViewById(R.id.hall_benefit);

        findViewById(R.id.title_back).setVisibility(View.GONE);
        findViewById(R.id.title_other).setOnClickListener(this);

        //信息发布
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mInfoRv.setLayoutManager(llm);
        mInfoRv.setAdapter(new InfoAdapter());

        //我的惠民
        WrapGridLayoutManager glmBenefit = new WrapGridLayoutManager(this, 4);
        glmBenefit.setOrientation(GridLayoutManager.VERTICAL);
        mBenefitRv.setLayoutManager(glmBenefit);
        mBenefitRv.setAdapter(new BenefitAdapter());
    }

    @Override
    public void onClick(View view) {
        IntentUtils.openPersonal(this);
    }

}
