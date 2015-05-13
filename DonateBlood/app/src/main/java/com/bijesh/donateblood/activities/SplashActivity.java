package com.bijesh.donateblood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bijesh.donateblood.R;
import com.bijesh.donateblood.base.ui.BaseActivity;

/**
 * Created by Bijesh on 12-05-2015.
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getCanonicalName();
    private Thread timer;
    private boolean isBackPressed = false;
    private static int SPLASH_TIME_OUT = 2000; // 2 Seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timer = new Thread() {
            public void run() {
                try {
                    // Display for 2 seconds
                    sleep(SPLASH_TIME_OUT);
                } catch (InterruptedException ex) {
                    Log.e(TAG, "Exception is " + Log.getStackTraceString(ex));

                } finally {
                    // Goes to Next Activity
                    if (!isBackPressed) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        finish();
                    }

                }
            }
        };
        timer.start();
    }

    @Override
    public void onBackPressed() {
        isBackPressed = true;
        timer.interrupt();

    }
}
