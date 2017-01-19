package heitorzanetti.com.showofftest.login.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by heitorzc on 18/01/2017.
 */

public class LoginInteractor implements IPrefsKeys {



    /**
     * Saves user's authentication token in SharedPreferences for further automatically login.
     */
    public void saveUserAuthToken(Context context, ILoginListener listener, String token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(PREFS_AUTH_TOKEN_KEY, token).apply();

        listener.onAuthTokenSaved();
    }



    /**
     * Checks if user already has an auth_token saved in the SharedPreferences.
     */
    public void checkSavedLoginData(Context context, ILoginListener listener) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String token = prefs.getString(PREFS_AUTH_TOKEN_KEY, "");

        if (!token.isEmpty()){
            listener.onStoredLoginDataReceived(token);
        }
        else {
            listener.onStoredLoginDataNotFound();
        }

    }


}
