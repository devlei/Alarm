package phonealarm.iss.com.alarm.hall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.utils.ToastUtils;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class PoliceInteractAddActivity extends Activity implements OnClickListener {

    private EditText mDescTv;


    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, PoliceInteractAddActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_interact_add);

        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.police_interact);
        ((TextView) findViewById(R.id.title_other)).setText(R.string.send);
        findViewById(R.id.pia_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                finish();
                ToastUtils.showToast(this, R.string.send);
                break;
            case R.id.pia_add:
                ToastUtils.showToast(this, "添加图片");
                break;
        }
    }
}
