package phonealarm.iss.com.alarm.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import phonealarm.iss.com.alarm.R;

/**
 * Created by weizhilei on 2017/9/24.
 */
public class WebViewActivity extends Activity {

    private static final String H5_URL = "h5_url";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(mWebViewClient);

        String url = getIntent().getStringExtra(H5_URL);
        mWebView.loadUrl(url);
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

}
