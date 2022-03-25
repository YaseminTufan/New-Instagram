package com.yasemintufan.instagramfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        username = findViewById(R.id.user_name);

        String s = getIntent().getStringExtra("username");
        String usercomment = getIntent().getStringExtra("usercomment");
        username.setText(s + usercomment);
    }
}