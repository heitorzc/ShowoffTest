package heitorzanetti.com.showofftest.login.presenter;

import android.content.Context;

/**
 * Created by heitorzc on 18/01/2017.
 */

public interface ILoginPresenter {
    void saveUserAuthToken(Context context, String token);
    void checkSavedLoginData(Context context);
    void registerConnectivityStatusReceiver(Context context);
    void unregisterConnectivityStatusReceiver(Context context);
}
