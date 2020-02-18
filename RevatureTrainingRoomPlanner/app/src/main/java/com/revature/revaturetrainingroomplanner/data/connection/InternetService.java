package com.revature.revaturetrainingroomplanner.data.connection;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class InternetService  extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public InternetService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        Bundle extras = intent.getExtras();
//        boolean isNetworkConnected = extras.getBoolean("isNetworkConnected");

        Toast.makeText(this, "CONNECTED TO INTERNET!!", Toast.LENGTH_LONG).show();

    }
}