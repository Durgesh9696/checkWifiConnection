package com.durgeshparekh.checkwificonnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

public class ConnectivityReceiver extends BroadcastReceiver {
    public static boolean isWifiOn;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e( "onReceive: ", "-----------------------------");
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN);
        switch (wifiStateExtra){
            case WifiManager.WIFI_STATE_ENABLED:
                wifiManager.setWifiEnabled(true);
                isWifiOn = true;
                break;

            case WifiManager.WIFI_STATE_DISABLED:
                wifiManager.setWifiEnabled(false);
                isWifiOn = false;
                break;

        }

        Intent wifiIntent = new Intent(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intent.putExtra("WifiConnection", isWifiOn);
        context.sendBroadcast(wifiIntent);

    }
}
