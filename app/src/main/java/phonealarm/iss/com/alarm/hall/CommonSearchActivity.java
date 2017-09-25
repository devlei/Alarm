package phonealarm.iss.com.alarm.hall;

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
import phonealarm.iss.com.alarm.hall.adapter.CommonSearchAdapter;
import phonealarm.iss.com.alarm.utils.IntentUtils;

/**
 * Created by weizhilei on 2017/9/23.
 */
public class CommonSearchActivity extends Activity implements OnClickListener, TextWatcher {

    private EditText mSearchEt;
    private RecyclerView mRv;

    /**
     * open
     *
     * @param context
     * @param typeResId
     */
    public static void open(Context context, int typeResId) {
        if (context != null) {
            Intent intent = new Intent(context, CommonSearchActivity.class);
            intent.putExtra(context.getString(R.string.key_type), typeResId);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_search);
        init();

        // TODO: 2017/9/23 weizhilei 测试数据
        mRv.setAdapter(
                new CommonSearchAdapter(getIntent().getIntExtra(getString(R.string.key_type), R.integer.type_cases)));
    }

    private void init() {
        TextView titleTv = (TextView) findViewById(R.id.title_name);
        TextView otherTv = (TextView) findViewById(R.id.title_other);
        mSearchEt = (EditText) findViewById(R.id.common_search);
        mRv = (RecyclerView) findViewById(R.id.commonSearch);

        //set listener
        findViewById(R.id.title_back).setOnClickListener(this);
        otherTv.setVisibility(View.GONE);
        mSearchEt.addTextChangedListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRv.setLayoutManager(llm);

        int typeResId = getIntent().getIntExtra(getString(R.string.key_type), R.integer.type_cases);
        switch (typeResId) {
            case R.integer.type_cases:
                titleTv.setText(R.string.cases);
                break;
            case R.integer.type_vehicle_track:
                titleTv.setText(R.string.vehicle_track);
                break;
            case R.integer.type_suspect_track:
                titleTv.setText(R.string.suspect_track);
                break;
            case R.integer.type_people_lost:
                titleTv.setText(R.string.people_lost);
                break;
            case R.integer.type_lost_found:
                titleTv.setText(R.string.lost_found);
                break;
            case R.integer.type_police_interact:
                titleTv.setText(R.string.police_interact);
                otherTv.setVisibility(View.VISIBLE);
                otherTv.setText(R.string.add);
                otherTv.setOnClickListener(this);
                break;
            case R.integer.type_alarm_history:
                titleTv.setText(R.string.alarm_history);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_other:
                IntentUtils.openPoliceInteractAdd(this);
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
