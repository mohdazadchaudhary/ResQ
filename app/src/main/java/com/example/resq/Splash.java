package com.example.resq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000; // 3 seconds delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 🔒 Bonus: Hide ActionBar just in case
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_splash);

        // Delay and then move to MainActivity
        new Handler().postDelayed(() -> {
            Intent iNext = new Intent(Splash.this, MainActivity.class);
            startActivity(iNext);
            finish(); // close splash so user can't come back with back button
        }, SPLASH_TIME);
    }
}
