package heitorzanetti.com.showofftest.login.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heitorzanetti.com.showofftest.R;
import heitorzanetti.com.showofftest.Showoff;
import heitorzanetti.com.showofftest.home.view.HomeActivity;
import heitorzanetti.com.showofftest.login.model.IPrefsKeys;
import heitorzanetti.com.showofftest.login.presenter.LoginPresenter;
import heitorzanetti.com.showofftest.login.utils.AuthWebViewClient;


public class LoginActivity extends AppCompatActivity implements ILoginView, IPrefsKeys {
    @Bind(R.id.wvAuthenticate)    WebView wvAuthenticate;

    private LoginPresenter presenter;
    private Snackbar connMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);
        connMessage = Snackbar.make(getWindow().getDecorView(), R.string.connection_message_error, Snackbar.LENGTH_INDEFINITE);

        //Checks if user has logged in before. If so, get the saved auth_token and redirect the
        //user to the Home Activity.
        presenter.checkSavedLoginData(this);

        setupWebView();
    }


    /**
     * Setup the webView adding a custom WebViewClient into it and cleaning cookies.
     * Cleaning all cookies is important because if the user decides do log in using
     * a different account later on, they previous credentials won't be stored and
     * the user won't be redirected to the HomeActivity automatically.
     */
    private void setupWebView(){

        wvAuthenticate.setWebViewClient(new AuthWebViewClient(this));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().removeAllCookies(null);
        }
        else {
            CookieManager.getInstance().removeAllCookie();
        }

        loadAuthUrl();

    }


    private void loadAuthUrl(){
        String url = getString(R.string.instagram_auth_url);
        wvAuthenticate.loadUrl(url);
    }


    private void startHomeActivity(String token) {
        startActivity(new Intent(this, HomeActivity.class).putExtra(PREFS_AUTH_TOKEN_KEY, token));
        finish();
    }


    private void showConnMessage(){
        if (connMessage != null) connMessage.show();
    }


    private void dismissConnMessage(){
        if (connMessage != null && connMessage.isShown()){
            connMessage.dismiss();
        }
    }


    @OnClick(R.id.btLogin)
    public void onClickLoginButton(){

        if (Showoff.hasInternet) {

            wvAuthenticate.setVisibility(View.VISIBLE);

        }
        else {
            showConnMessage();
        }

    }


    @Override
    public void onStoredLoginDataReceived(String token) {
        startHomeActivity(token);
    }


    @Override
    public void onStoredLoginDataNotFound() {
        presenter.registerConnectivityStatusReceiver(this);
    }


    @Override
    public void onAuthTokenReceived(String token) {
        presenter.saveUserAuthToken(this, token);
        wvAuthenticate.setVisibility(View.GONE);

        startHomeActivity(token);

    }


    /**
     * Every time the user gets an error, we have to remove all cookies so if the login
     * fails, the user will see the Login page again when trying for a second time, instead of
     * being automatically redirected to an Instagram error page.
     */
    @Override
    public void onAuthTokenError() {
        wvAuthenticate.setVisibility(View.GONE);
        Toast.makeText(this, R.string.error_api_connection, Toast.LENGTH_SHORT).show();

        setupWebView();
    }


    @Override
    public void onInternetOnline() {
        dismissConnMessage();
    }


    @Override
    public void onInternetOffline() {
        showConnMessage();
    }



    @Override
    public void onBackPressed() {

        if (wvAuthenticate.getVisibility() == View.VISIBLE) {
            wvAuthenticate.setVisibility(View.INVISIBLE);
            return;
        }

        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        presenter.unregisterConnectivityStatusReceiver(this);
        super.onDestroy();
    }


}

