package org.transistorsoft.cordova.plugin.background.fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

public class BackgroundAlarmReceiver extends BroadcastReceiver {
    private static CallbackContext callbackContext;

    public static void setCallbackContext(CallbackContext cbContext) {
        callbackContext = cbContext;
    }

    private void executeCallback() {
        Log.d("BackgroundAlarmReceiver", "Performing background work...");
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (callbackContext == null) return;

        executeCallback();
    }
}
