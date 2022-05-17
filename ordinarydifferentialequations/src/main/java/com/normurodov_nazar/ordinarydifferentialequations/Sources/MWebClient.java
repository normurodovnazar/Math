package com.normurodov_nazar.ordinarydifferentialequations.Sources;

import static com.normurodov_nazar.ordinarydifferentialequations.Sources.Hey.print;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.normurodov_nazar.ordinarydifferentialequations.Listeners.PageListener;

public class MWebClient extends WebViewClient {
    final PageListener pageListener;

    public MWebClient(PageListener pageListener) {
        this.pageListener = pageListener;
    }

    final
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        pageListener.onStartedLoading();
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        pageListener.onPageFail(error.getDescription().toString());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        print("onPageFinished",view.getTitle());
        if (view.getTitle().contains("calculator")) pageListener.onPageSuccess(); else pageListener.onPageFail("0");
    }
}
