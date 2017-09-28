package phonealarm.iss.com.alarm.personal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import phonealarm.iss.com.alarm.R;

/**
 * Created by weizhilei on 2017/9/26.
 */
public class HeaderDialog extends Dialog implements OnClickListener {

    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_ALBUM = 2;

    private OnHeaderDismissListener mListener;

    public static Dialog show(Context context, OnHeaderDismissListener listener) {
        HeaderDialog dialog = null;
        if (context != null) {
            dialog = new HeaderDialog(context, listener);
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dialog;
    }

    public HeaderDialog(@NonNull Context context, OnHeaderDismissListener listener) {
        super(context, R.style.ThemeTransparent);
        this.mListener = listener;
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

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_header);
        findViewById(R.id.camera).setOnClickListener(this);
        findViewById(R.id.album).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                if (mListener != null) mListener.onDismiss(TYPE_CAMERA);
                dismiss();
                break;
            case R.id.album:
                if (mListener != null) mListener.onDismiss(TYPE_ALBUM);
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }

    public interface OnHeaderDismissListener {
        void onDismiss(int type);
    }

}
