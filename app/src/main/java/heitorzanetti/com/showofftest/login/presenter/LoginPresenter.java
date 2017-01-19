package heitorzanetti.com.showofftest.login.presenter;

import android.content.Context;
import android.util.Log;

import heitorzanetti.com.showofftest.login.model.ILoginListener;
import heitorzanetti.com.showofftest.login.model.LoginInteractor;
import heitorzanetti.com.showofftest.login.view.ILoginView;
import heitorzanetti.com.showofftest.networking.ConnectivityStatusReceiver;
import heitorzanetti.com.showofftest.networking.IConnectivityStatusListener;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class LoginPresenter implements ILoginPresenter, ILoginListener, IConnectivityStatusListener {

    private final String TAG = "LoginPresenter";

    private ILoginView view;
    private LoginInteractor interactor;
    private ConnectivityStatusReceiver connReceiver;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.interactor = new LoginInteractor();
    }



    @Override
    public void saveUserAuthToken(Context context, String token) {
        interactor.saveUserAuthToken(context, this, token);
    }


    @Override
    public void checkSavedLoginData(Context context) {
        interactor.checkSavedLoginData(context, this);
    }


    @Override
    public void registerConnectivityStatusReceiver(Context context) {
        connReceiver = new ConnectivityStatusReceiver();
        connReceiver.register(this, context);
    }

    @Override
    public void unregisterConnectivityStatusReceiver(Context context) {
        if (connReceiver != null) connReceiver.unregister(context);
    }


    @Override
    public void onAuthTokenSaved() {
        Log.i(TAG, "User login data saved for using on the next section.");
    }


    @Override
    public void onStoredLoginDataReceived(String token) {
        view.onStoredLoginDataReceived(token);
    }


    @Override
    public void onStoredLoginDataNotFound() {
        view.onStoredLoginDataNotFound();
    }


    @Override
    public void onInternetOnline() {
        view.onInternetOnline();
    }

    @Override
    public void onInternetOffline() {
        view.onInternetOffline();
    }


}
