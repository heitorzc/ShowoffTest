package heitorzanetti.com.showofftest.home.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import heitorzanetti.com.showofftest.home.utils.ApiUserMediaResponse;
import heitorzanetti.com.showofftest.home.utils.ApiUserProfileResponse;
import heitorzanetti.com.showofftest.login.model.IPrefsKeys;
import heitorzanetti.com.showofftest.networking.HttpRequestManager;
import heitorzanetti.com.showofftest.networking.IHttpEndPoints;
import heitorzanetti.com.showofftest.networking.IHttpRequestManager;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class HomeInteractor implements IHttpEndPoints, IPrefsKeys{


    public void getUserProfileData(IHttpRequestManager listener, String token) {
        new HttpRequestManager(new ApiUserProfileResponse(), listener).get(BASE_URL + GET_PROFILE_URL, token);
    }


    public void getUserMediaData(IHttpRequestManager  listener, String token) {
        new HttpRequestManager(new ApiUserMediaResponse(), listener).get(BASE_URL + GET_MEDIA_URL, token);
    }

    public void logoutUser(ILogoutListener listener, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().clear().apply();

        listener.onLogoutCompleted();
    }

}
