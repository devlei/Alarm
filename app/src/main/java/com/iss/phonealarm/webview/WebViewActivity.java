package com.iss.phonealarm.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.iss.phonealarm.R;
import com.iss.phonealarm.bean.caseinfo.CaseFileInfo;
import com.iss.phonealarm.bean.caseinfo.CaseInfo;
import com.iss.phonealarm.utils.CollectionUtils;
import com.iss.phonealarm.utils.GlideUtils;

import java.util.List;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class WebViewActivity extends Activity implements View.OnClickListener {

    private static final String H5_URL = "h5_url";
    private static final String BEAN = "BEAN";

    private WebView mWebView;

    /**
     * open
     *
     * @param context
     * @param url
     */
    public static void open(Context context, String url) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(H5_URL, url);
            context.startActivity(intent);
        }
    }

    public static void open(Context context, CaseInfo mCasesInfo) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(BEAN, mCasesInfo);
            context.startActivity(intent);
        }
    }

    CaseInfo mCasesInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);

//        mWebView = (WebView) findViewById(R.id.webView);
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebViewClient(mWebViewClient);
//
//        String url = getIntent().getStringExtra(H5_URL);
//        mWebView.loadUrl(url);
        mCasesInfo = (CaseInfo) getIntent().getSerializableExtra(BEAN);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_other).setVisibility(View.GONE);
        TextView textView = (TextView) findViewById(R.id.title_name);
        textView.setText("要案");
        TextView case_title = (TextView) findViewById(R.id.case_title);
        TextView case_time = (TextView) findViewById(R.id.case_time);
        TextView case_from = (TextView) findViewById(R.id.case_from);
        TextView case_count = (TextView) findViewById(R.id.case_count);
        ImageView case_pic = (ImageView) findViewById(R.id.case_pic);
        ImageView case_pic2 = (ImageView) findViewById(R.id.case_pic2);


        if (null != mCasesInfo) {
            case_title.setText(mCasesInfo.getCases_theme());
            case_time.setText(mCasesInfo.getCases_sendtime());
            case_from.setText(mCasesInfo.getSendproxy());
            case_count.setText(mCasesInfo.getCases_content());
            List<CaseFileInfo> cases_files = mCasesInfo.getCases_files();
            if (!CollectionUtils.isEmpty(cases_files)) {
                if (cases_files.size() > 1) {
                    GlideUtils.loadImage(this, cases_files.get(0).getValue(), 0, case_pic);
                    GlideUtils.loadImage(this, cases_files.get(1).getValue(), 0, case_pic2);
                    case_pic.setVisibility(View.VISIBLE);
                    case_pic2.setVisibility(View.VISIBLE);
                } else {
                    GlideUtils.loadImage(this, cases_files.get(0).getValue(), 0, case_pic);
                    case_pic.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            //返回值true:WebView打开，false:系统浏览器或第三方浏览器
            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.loadUrl("about:blank");
            mWebView.freeMemory();
            mWebView.pauseTimers();
            ((ViewGroup) getWindow().getDecorView()).removeView(mWebView);
            mWebView = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.title_back) {
            finish();
        }
    }
}
