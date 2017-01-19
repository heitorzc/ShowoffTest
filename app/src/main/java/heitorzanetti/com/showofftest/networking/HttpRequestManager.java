package heitorzanetti.com.showofftest.networking;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import heitorzanetti.com.showofftest.Showoff;
import okhttp3.OkHttpClient;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class HttpRequestManager implements JSONObjectRequestListener, IHttpRequestErrorCodes{

    private final String TAG = "HTTPREQUEST";

    private Object                 responseObject;
    private IHttpRequestManager    listener;



    public HttpRequestManager(Object responseObject, IHttpRequestManager listener) {
        this.responseObject   = responseObject;
        this.listener         = listener;
    }



    public void get(String url, String token) {

        if (Showoff.hasInternet) {

            Log.w(TAG, "internet: " + Showoff.hasInternet);

            AndroidNetworking.get(url)
                    .addQueryParameter("access_token", token)
                    .setPriority(Priority.HIGH)
                    .setOkHttpClient(newOkHttpClient())
                    .build()
                    .getAsJSONObject(this);

        }
        else {
            listener.onRequestFailed(ERROR_NO_INTERNET_CONNECTION);
        }

    }



    private OkHttpClient newOkHttpClient(){
        return new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .build();
    }



    @Override
    public void onResponse(JSONObject jsonResponse) {
        Log.i(TAG, "Success: " + jsonResponse.toString());

        Object response = new Gson().fromJson(jsonResponse.toString(), responseObject.getClass());

        if (listener != null) {
            listener.onRequestCompleted(response);
        }

    }


    @Override
    public void onError(ANError anError) {

        if (listener != null) {
            listener.onRequestFailed(ERROR_API_CONNECTION);
        }

        Log.w(TAG, "Error: " + anError.getMessage());
        Log.e(TAG, "Error: " + anError.getResponse().toString());

    }

}
