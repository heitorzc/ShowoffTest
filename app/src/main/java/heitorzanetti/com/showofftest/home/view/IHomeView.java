package heitorzanetti.com.showofftest.home.view;

import heitorzanetti.com.showofftest.home.utils.ApiUserMediaResponse;
import heitorzanetti.com.showofftest.home.utils.ApiUserProfileResponse;

/**
 * Created by heitorzc on 18/01/2017.
 */

public interface IHomeView {
    void onUserProfileDataFetched(ApiUserProfileResponse userProfile);
    void onUserMediaDataFetched(ApiUserMediaResponse userMedia);
    void onUserRequestDataFailed(int code);
    void onLogoutUser();
    void onInternetOnline();
    void onInternetOffline();

}
