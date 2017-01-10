package com.bma.codingchallange.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bma.codingchallange.R;

/**
 * Created by balanescumadalin on 10/01/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private final static int DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        goToMain();

    }

    private void goToMain() {
        new Handler().postDelayed(() -> {

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();

        }, DURATION);
    }

}
