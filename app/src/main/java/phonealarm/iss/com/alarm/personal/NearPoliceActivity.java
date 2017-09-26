package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.personal.adapter.NearPoliceAdapter;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class NearPoliceActivity extends Activity implements OnClickListener {

    private RecyclerView mNearPoliceRv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, NearPoliceActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_police);
        init();
        getData();
        // TODO: 2017/9/23 weizhilei test data
        mNearPoliceRv.setAdapter(new NearPoliceAdapter());
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.near_police);
        mNearPoliceRv = (RecyclerView) findViewById(R.id.nearPolice);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setVisibility(View.GONE);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mNearPoliceRv.setLayoutManager(llm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

    private void getData() {
        // TODO: 2017/9/26 weizhilei 缺少附近派出所接口
    }

}
