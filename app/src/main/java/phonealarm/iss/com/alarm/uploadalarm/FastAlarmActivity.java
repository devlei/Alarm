package phonealarm.iss.com.alarm.uploadalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.utils.ToastUtils;

public class FastAlarmActivity extends Activity implements View.OnClickListener {

    /**
     * open
     *
     * @param context
     * @param typeResId
     */
    public static void open(Context context, int typeResId) {
        if (context != null) {
            Intent intent = new Intent(context, FastAlarmActivity.class);
            intent.putExtra(context.getString(R.string.key_type), typeResId);
            context.startActivity(intent);
        }
    }

    private int typeResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_alarm);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        if (null != intent) {
            typeResId = intent.getIntExtra(getString(R.string.key_type), 0);
        }

        TextView title = (TextView) findViewById(R.id.title_name);
        TextView titleOther= (TextView) findViewById(R.id.title_other);
        titleOther.setOnClickListener(this);
        findViewById(R.id.title_back).setOnClickListener(this);

        if (typeResId == R.integer.type_alarm) {
            title.setText(R.string.alarm);
            titleOther.setText("报警");
        } else if (typeResId == R.integer.type_eager_report) {
            title.setText(R.string.eager_report);
            titleOther.setText("举报");
        }

    }

    private void upLoad() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                upLoad();
                break;
        }
    }
}
