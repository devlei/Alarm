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
import phonealarm.iss.com.alarm.personal.adapter.EmergencyContactAdapter;
import phonealarm.iss.com.alarm.utils.IntentUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class EmergencyContactActivity extends Activity implements OnClickListener {

    private RecyclerView mEmergencyContactRv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, EmergencyContactActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        init();

        // TODO: 2017/9/23 weizhilei test data
        mEmergencyContactRv.setAdapter(new EmergencyContactAdapter());
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.emergency_contact);
        TextView addTv = (TextView) findViewById(R.id.title_other);
        addTv.setText(R.string.add);
        addTv.setOnClickListener(this);
        mEmergencyContactRv = (RecyclerView) findViewById(R.id.emergencyContact);

        findViewById(R.id.title_back).setOnClickListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mEmergencyContactRv.setLayoutManager(llm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                IntentUtils.openEmergencyContactAdd(this);
                break;
        }
    }
}
