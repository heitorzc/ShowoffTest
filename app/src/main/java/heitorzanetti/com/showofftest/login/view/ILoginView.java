package heitorzanetti.com.showofftest.login.view;

/**
 * Created by heitorzc on 18/01/2017.
 */

public interface ILoginView {
    void onStoredLoginDataReceived(String token);
    void onStoredLoginDataNotFound();
    void onAuthTokenReceived(String token);
    void onInternetOnline();
    void onInternetOffline();
}
