package com.revature.revaturetrainingroomplanner.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.login.LoginActivity;
import com.revature.revaturetrainingroomplanner.ui.login.SaveSharedPreference;

public class LaunchActivity extends AppCompatActivity {

    SaveSharedPreference saveSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(() -> {

            SharedPreferences sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
            Log.d("debug", "checking login info");

            if(sharedPreferences.getString("Email", "").isEmpty()){
                Log.d("debug", "no login info");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            else {
                Log.d("debug", "found login, to main page");

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
