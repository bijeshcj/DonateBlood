package com.bijesh.donateblood.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.bijesh.donateblood.R;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class NeedActivity extends ActionBarActivity {

    private static final String TAG = NeedActivity.class.getCanonicalName();
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need);

        init();

    }

    private void init(){
        initToolBar();
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

}
