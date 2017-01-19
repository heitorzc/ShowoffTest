package heitorzanetti.com.showofftest.networking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import heitorzanetti.com.showofftest.Showoff;

/**
 * Created by heitorzc on 18/01/17.
 */
public class ConnectivityStatusReceiver extends BroadcastReceiver {

    IConnectivityStatusListener listener;
    Context mContext;


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        NetworkInfo info = extras.getParcelable("networkInfo");

        if (info != null) {

            NetworkInfo.State state = info.getState();

            if (state == NetworkInfo.State.CONNECTED && !Showoff.hasInternet) {
                Log.w("GCM_PUSH", "---------------------------------------------------");
                Log.i("GCM_PUSH", "---------------------------------------------------");
                Log.e("GCM_PUSH", "--------------- INTERNET AVAILABLE ----------------");
                Log.i("GCM_PUSH", "---------------------------------------------------");
                Log.w("GCM_PUSH", "---------------------------------------------------");

                Showoff.hasInternet = true;
                listener.onInternetOnline();
            } else if (state == NetworkInfo.State.DISCONNECTED && Showoff.hasInternet) {
                Log.w("GCM_PUSH", "---------------------------------------------------");
                Log.i("GCM_PUSH", "---------------------------------------------------");
                Log.e("GCM_PUSH", "----------------- DEVICE OFFLINE ------------------");
                Log.i("GCM_PUSH", "---------------------------------------------------");
                Log.w("GCM_PUSH", "---------------------------------------------------");

                Showoff.hasInternet = false;
                listener.onInternetOffline();
            }

        }

    }


    public void register(IConnectivityStatusListener listener, Context mContext){
        this.listener = listener;
        this.mContext = mContext;

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(this, intentFilter);

        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        Showoff.hasInternet = (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected());

    }


    public void unregister(Context mContext){
        this.mContext = mContext;
        mContext.unregisterReceiver(this);
    }

}

