package com.iss.phonealarm.personal;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.iss.phonealarm.R;
import com.kymjs.rxvolley.net.GcAppDownloadCallback;
import com.kymjs.rxvolley.net.dowload.DownLoadManager;

/**
 * Created by weizhilei on 2017/10/16.
 */
public class UpgradeDialog extends Dialog implements OnClickListener {

    private String mDownloadUrl;

    public static Dialog show(Context context, String url) {
        UpgradeDialog dialog = null;
        if (context != null) {
            dialog = new UpgradeDialog(context, url);
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dialog;
    }

    public UpgradeDialog(@NonNull Context context, String url) {
        super(context, R.style.ThemeFullscreen);
        this.mDownloadUrl = url;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_upgrade);
        findViewById(R.id.upgrade).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upgrade:
                upgrade();
                dismiss();
                break;
        }
    }

    /**
     * 升级
     */
    private void upgrade() {
        if (!TextUtils.isEmpty(mDownloadUrl)) {
            String name = getContext().getString(R.string.app_name);
            String packageName = getContext().getPackageName();
            GcAppDownloadCallback callback = new GcAppDownloadCallback(mDownloadUrl, name, packageName, true,
                    getContext().getApplicationContext());
            DownLoadManager.getInstance().startDownloadApk(callback);
        }
    }

}
