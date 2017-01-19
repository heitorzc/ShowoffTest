package heitorzanetti.com.showofftest.login.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;

import heitorzanetti.com.showofftest.login.view.ILoginView;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class AuthWebViewClient extends WebViewClient {

    private ILoginView view;


    public AuthWebViewClient(ILoginView view) {
        this.view = view;
    }



    @Override
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);

        try {

            URL token_url = new URL(url);

            if (token_url.getHost().equals("localhost")){

                if (view != null) {
                    view.onAuthTokenReceived(url.substring(url.indexOf("=") + 1));
                    view = null;
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
