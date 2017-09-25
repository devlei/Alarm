package phonealarm.iss.com.alarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import phonealarm.iss.com.alarm.hall.WrapGridLayoutManager;
import phonealarm.iss.com.alarm.hall.adapter.BenefitAdapter;
import phonealarm.iss.com.alarm.hall.adapter.InfoAdapter;
import phonealarm.iss.com.alarm.utils.IntentUtils;

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
        WrapGridLayoutManager glm = new WrapGridLayoutManager(this, 6);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        mInfoRv.setLayoutManager(glm);
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
