package org.transistorsoft.cordova.plugin.background.fetch;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

public class BackgroundFetch extends CordovaPlugin {
    public static final int PERIOD = 1800000; // Every 30 minutes
    public static final String ACTION_RUN_BACKGROUND_FETCH = "ED_RUN_BACKGROUND_FETCH";

    @Override
    public boolean execute(String action, final JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d("BackgroundFetch", "Running backgroundFetch");
        if (action.equals("configure")) {
            Context appContext = cordova.getActivity().getApplicationContext();
            AlarmManager alarmMgr = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);

            BackgroundAlarmReceiver.setCallbackContext(callbackContext);
            Intent i = new Intent(ACTION_RUN_BACKGROUND_FETCH);

            PendingIntent pi = PendingIntent.getBroadcast(appContext, 0, i ,0);

            alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 60000,
                    PERIOD,
                    pi);

            return true;
        }

        return false;
    };
}
