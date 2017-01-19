package heitorzanetti.com.showofftest.login.utils;

import android.util.Log;
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

                    if (url.contains("access_token=")) {
                        view.onAuthTokenReceived(url.substring(url.indexOf("=") + 1));
                    }
                    else {
                        view.onAuthTokenError();
                    }

                    view = null;
                }

            }
            else if (!webView.getTitle().contains("Authorization Request") && !webView.getTitle().contains("Log in")){
                Log.w("HOME", webView.getTitle());
                view.onAuthTokenError();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
