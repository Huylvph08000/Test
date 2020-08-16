package com.example.a1608;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {
TextView username, email, joined;
UserResponse userResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        joined = findViewById(R.id.joined);
        Intent intent = getIntent();
        if (intent.getExtras() !=null){
            userResponse = (UserResponse) intent.getSerializableExtra("data");
            String usernamedata = userResponse.getUsername();
            String email1 = userResponse.getEmail();
            String joined1 = userResponse.getDate_joined();

            username.setText(usernamedata);
            email.setText(email1);
            joined.setText(joined1);
        }
    }
}