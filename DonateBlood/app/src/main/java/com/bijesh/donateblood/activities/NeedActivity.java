package com.bijesh.donateblood.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bijesh.donateblood.R;
import com.bijesh.donateblood.utils.cloud.PushServiceUtils;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class NeedActivity extends ActionBarActivity {

    private static final String TAG = NeedActivity.class.getCanonicalName();

    private Toolbar mToolBar;
    private Button mBtnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need);

        init();

    }

    private void init(){
        initToolBar();
        initComponents();
    }

    private void initToolBar(){
        mToolBar = (Toolbar)findViewById(R.id.app_toolbar);
        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "toolbar clicked ");
                finish();
            }
        });
    }

    private void initComponents(){
        mBtnRequest = (Button) findViewById(R.id.btnRegister);

        mBtnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PushServiceUtils.sendPush();
                Toast.makeText(NeedActivity.this,"Request is sent",Toast.LENGTH_LONG).show();
            }
        });

    }

}
