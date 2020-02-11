package com.revature.revaturetrainingroomplanner.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.revature.revaturetrainingroomplanner.R;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.forgot_password);

        Button submit = findViewById(R.id.btn_forgot_password_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.et_forgot_password_input);
                // TODO: send reset password link to emial
                String emailMsg = "Email sent to " + email.getText().toString();
                Snackbar.make(v, emailMsg, Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
