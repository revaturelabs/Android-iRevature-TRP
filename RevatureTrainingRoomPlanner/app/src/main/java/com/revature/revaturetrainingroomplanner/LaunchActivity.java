package com.revature.revaturetrainingroomplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.LinearLayout;

import com.revature.revaturetrainingroomplanner.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;


public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_page);

        new Handler().postDelayed(() -> {
            /* Create an Intent that will start the Menu-Activity. */
            Intent mainIntent = new Intent(LaunchActivity.this, LoginActivity.class);
            startActivity(mainIntent);
            finish();
        }, 1500);

    }

}
