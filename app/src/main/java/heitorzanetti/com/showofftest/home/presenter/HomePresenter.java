package heitorzanetti.com.showofftest.home.presenter;

import android.content.Context;

import heitorzanetti.com.showofftest.home.model.HomeInteractor;
import heitorzanetti.com.showofftest.home.model.ILogoutListener;
import heitorzanetti.com.showofftest.home.utils.ApiUserMediaResponse;
import heitorzanetti.com.showofftest.home.utils.ApiUserProfileResponse;
import heitorzanetti.com.showofftest.home.view.IHomeView;
import heitorzanetti.com.showofftest.networking.ConnectivityStatusReceiver;
import heitorzanetti.com.showofftest.networking.IConnectivityStatusListener;
import heitorzanetti.com.showofftest.networking.IHttpRequestManager;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class HomePresenter implements IHomePresenter, IHttpRequestManager, ILogoutListener, IConnectivityStatusListener{

    private ConnectivityStatusReceiver connReceiver;
    private IHomeView view;
    private HomeInteractor interactor;


    public HomePresenter(IHomeView view) {
        this.view = view;
        this.interactor = new HomeInteractor();
    }



    @Override
    public void requestUserProfileData(String token) {
        interactor.getUserProfileData(this, token);
    }


    @Override
    public void requestUserMediaData(String token) {
        interactor.getUserMediaData(this, token);
    }


    @Override
    public void logoutUser(Context context) {
        interactor.logoutUser(this, context);
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
    public void onRequestCompleted(Object responseObject) {

        if (responseObject instanceof ApiUserProfileResponse){
            ApiUserProfileResponse userProfile = (ApiUserProfileResponse) responseObject;
            view.onUserProfileDataFetched(userProfile);
        }
        else if (responseObject instanceof ApiUserMediaResponse){
            ApiUserMediaResponse userMedia = (ApiUserMediaResponse) responseObject;
            view.onUserMediaDataFetched(userMedia);
        }

    }


    @Override
    public void onRequestFailed(int code) {
        view.onUserRequestDataFailed(code);
    }


    @Override
    public void onLogoutCompleted() {
        view.onLogoutUser();
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
