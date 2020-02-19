package com.revature.revaturetrainingroomplanner.util.connection;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;

public class ConnectionService  extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Bundle extras = intent.getExtras();
        boolean isNetworkConnected = extras.getBoolean("isNetworkConnected");

        if(isNetworkConnected){
            Log.d("debug","CONNECTED to internet");
        }
        else {
            Log.d("debug","DISCONNECTED to internet");
        }

        return null;
    }
}