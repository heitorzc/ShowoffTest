package heitorzanetti.com.showofftest.home.presenter;

import android.content.Context;

/**
 * Created by heitorzc on 18/01/2017.
 */

public interface IHomePresenter {
    void requestUserProfileData(String token);
    void requestUserMediaData(String token);
    void logoutUser(Context context);
    void registerConnectivityStatusReceiver(Context context);
    void unregisterConnectivityStatusReceiver(Context context);
}
