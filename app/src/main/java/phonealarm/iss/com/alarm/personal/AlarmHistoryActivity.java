package phonealarm.iss.com.alarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import phonealarm.iss.com.alarm.R;
import phonealarm.iss.com.alarm.personal.adapter.AlarmHistoryAdapter;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class AlarmHistoryActivity extends Activity implements OnClickListener, TextWatcher {

    private EditText mSearchEt;
    private RecyclerView mAlarmHistoryRv;

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, AlarmHistoryActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_history);
        init();

        // TODO: 2017/9/23 weizhilei 测试数据
        mAlarmHistoryRv.setAdapter(new AlarmHistoryAdapter());
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(R.string.alarm_history);
        mSearchEt = (EditText) findViewById(R.id.ah_search);
        mAlarmHistoryRv = (RecyclerView) findViewById(R.id.alarmHistory);

        findViewById(R.id.title_back).setOnClickListener(this);
        mSearchEt.addTextChangedListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mAlarmHistoryRv.setLayoutManager(llm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO: 2017/9/23 weizhilei 搜索更新列表
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_ENTER:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(mSearchEt.getWindowToken(), 0);

                }
                return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
