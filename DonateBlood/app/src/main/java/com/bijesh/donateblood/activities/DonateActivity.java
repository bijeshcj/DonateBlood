package com.bijesh.donateblood.activities;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.bijesh.donateblood.R;
import com.bijesh.donateblood.utils.phone.PhoneUtils;

/**
 * Created by bijesh on 5/21/2015.
 */
public class DonateActivity extends ActionBarActivity {

    private static final String TAG = DonateActivity.class.getCanonicalName();
    private Toolbar mToolBar;
    private EditText extTxtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);


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

        initComponents();

    }


    private void initComponents(){
        extTxtMobile = (EditText)findViewById(R.id.edtTxtMobile);
        String number = PhoneUtils.getPrimaryMobileNumber(this);

        if(number != null)
            extTxtMobile.setText(number);



    }



}
