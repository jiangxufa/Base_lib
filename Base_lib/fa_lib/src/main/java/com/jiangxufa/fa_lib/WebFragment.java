package com.jiangxufa.fa_lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jiangxufa.fa_lib.base.BaseFragment;
import com.jiangxufa.fa_lib.base.BasePresenter;
import com.jiangxufa.fa_lib.listener.NoDoubleClickListener;
import com.jiangxufa.fa_lib.utils.SelectorFactory;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * 创建时间：2018/6/27
 * 编写人：lenovo
 * 功能描述：
 */

public class WebFragment extends BaseFragment {

    private WebView mWebView;
    private String mUrl;
    private TextView tvjump;

    class WebClient extends WebChromeClient {
        public WebClient() {
            super();
        }

        @Override
        public void onProgressChanged(WebView webView, int i) {
//            if (i >= 40) {
//                mPwLoading.setVisibility(View.GONE);
//            } else {
//                mPwLoading.setVisibility(View.VISIBLE);
//            }
            mWebView.getSettings().setBlockNetworkImage(false);
            super.onProgressChanged(webView, i);
        }

        @Override
        public void onReachedMaxAppCacheSize(long spaceNeeded, long quota, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(spaceNeeded * 2);
        }
    }

    class WebClientBase extends WebViewClient {
        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
//            mPwLoading.setVisibility(View.GONE);
            mWebView.getSettings().setBlockNetworkImage(false);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            mWebView.measure(w, h);

            tvjump.setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
//            mPwLoading.setVisibility(View.GONE);
            String errorHtml = "<html><body><h2>找不到网页</h2></body></html>";
            mWebView.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
        }

    }

    public static WebFragment newInstance() {
        WebFragment webFragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("URL", "https://www.baidu.com/");
        webFragment.setArguments(bundle);
        return webFragment;
    }

    @Override
    protected int getViewResourse() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initView(View view) {
        mWebView = view.findViewById(R.id.webView);
        tvjump = view.findViewById(R.id.tv_jump);
        tvjump.setBackgroundDrawable(SelectorFactory.newShapeSelector()
                .setShape(RECTANGLE)
                .setCornerRadius((int) dp2px(24))
                .setDefaultBgColor(getResources().getColor(R.color.gray_three))
                .create());
        tvjump.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (isAdded()) {
                    listener.onClick(v);
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mUrl = (String) bundle.get("URL");
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initWebView() {
        WebClient webChromeClient = new WebClient();
        WebClientBase webViewClient = new WebClientBase();
        WebSettings webSettings = mWebView.getSettings();
        //设置js支持
        webSettings.setJavaScriptEnabled(true);
        // 设置支持javascript脚本
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        //设置缓存
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小
        String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCacheDir);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webSettings.setGeolocationEnabled(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLoadWithOverviewMode(true);//全屏
        webSettings.setBuiltInZoomControls(true);// 设置显示缩放按钮
        webSettings.setSupportZoom(true);//支持缩放
        webSettings.setDisplayZoomControls(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setDrawingCacheEnabled(true);
        mWebView.getSettings().setBlockNetworkImage(true);
        mWebView.setWebViewClient(webViewClient);
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.setWebChromeClient(webChromeClient);
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected BasePresenter initPresenter(BaseFragment baseFragment) {
        return null;
    }

    @Override
    public void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }

    private View.OnClickListener listener;

    public void setOnJumpClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
