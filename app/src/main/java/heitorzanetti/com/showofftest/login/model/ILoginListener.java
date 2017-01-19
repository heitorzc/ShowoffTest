package heitorzanetti.com.showofftest.login.model;

/**
 * Created by heitorzc on 18/01/2017.
 */

public interface ILoginListener {
    void onAuthTokenSaved();
    void onStoredLoginDataReceived(String token);
    void onStoredLoginDataNotFound();
}
