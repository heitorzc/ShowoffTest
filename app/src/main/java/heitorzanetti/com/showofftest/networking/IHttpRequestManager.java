package heitorzanetti.com.showofftest.networking;

/**
 * Created by heitorzc on 18/01/2017.
 */

public interface IHttpRequestManager {
    void onRequestCompleted(Object responseObject);
    void onRequestFailed(int code);
}
