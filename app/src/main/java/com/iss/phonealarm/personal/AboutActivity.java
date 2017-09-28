package com.iss.phonealarm.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.iss.phonealarm.R;
import com.iss.phonealarm.utils.AppUtils;

/**
 * Created by weizhilei on 2017/9/27.
 */
public class AboutActivity extends Activity implements OnClickListener {

    /**
     * open
     *
     * @param context
     */
    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, AboutActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView version = (TextView) findViewById(R.id.about_version);
        version.setText(AppUtils.getVersionName(this));
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.title_name)).setText(R.string.about);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
