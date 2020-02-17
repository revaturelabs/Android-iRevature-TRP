package com.revature.revaturetrainingroomplanner.ui.login;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        NavController navController = Navigation.findNavController(this, R.id.navhost_login_main);

    }
}
