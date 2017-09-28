package phonealarm.iss.com.alarm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by weizhilei on 2017/2/22.
 */
public class LoadingDialog extends Dialog implements OnDismissListener, OnCancelListener {

    private static volatile Dialog mDialog = null;

    private String mText;

    /**
     * @param context
     * @return
     */
    public static Dialog show(Context context) {
        return show(context, null);
    }

    /**
     * @param context
     * @param resId
     * @return
     */
    public static Dialog show(Context context, int resId) {
        if (context != null) {
            return show(context, context.getString(resId));
        }
        return null;
    }


    /**
     * @param context
     * @param s
     * @return
     */
    public static Dialog show(Context context, String s) {
        dismissSelf();
        if (mDialog == null) {
            synchronized (LoadingDialog.class) {
                if (mDialog == null) {
                    mDialog = new LoadingDialog(context, s);
                }
            }
        }
        try {
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDialog;
    }

    public static void dismissSelf() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    private LoadingDialog(Context context, String s) {
        super(context, R.style.ThemeFullscreen_Transparent);
        mText = s;
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
        setContentView(R.layout.dialog_loading);
        if (!TextUtils.isEmpty(mText)) {
            ((TextView) findViewById(R.id.loading_text)).setText(mText);
        }
        setOnDismissListener(this);
        setOnCancelListener(this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mDialog = null;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mDialog = null;
    }

}
