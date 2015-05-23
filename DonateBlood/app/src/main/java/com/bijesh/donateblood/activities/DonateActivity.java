package com.bijesh.donateblood.activities;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import com.bijesh.donateblood.R;
import com.bijesh.donateblood.models.ui.Donor;
import com.bijesh.donateblood.utils.ModelPopulateUtil;
import com.bijesh.donateblood.utils.ValidationUtils;
import com.bijesh.donateblood.utils.phone.PhoneUtils;
import com.parse.ParseObject;

/**
 * Created by bijesh on 5/21/2015.
 */
public class DonateActivity extends ActionBarActivity {

    private static final String TAG = DonateActivity.class.getCanonicalName();
    private Toolbar mToolBar;
    private EditText edtTxtMobile,edtTxtEmail,edtTxtName;
    private Spinner mSpinBGroup;
    private RadioGroup mRadioGroupGender;
    private RadioButton mRadioButton;
    private Button mBtnRegister;
    private String mBloodGroup;

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
        edtTxtEmail = (EditText)findViewById(R.id.edtTxtEmail);
        edtTxtMobile = (EditText)findViewById(R.id.edtTxtMobile);
        edtTxtName = (EditText)findViewById(R.id.edtTxtName);

        String number = PhoneUtils.getPrimaryMobileNumber(this);

        String email = PhoneUtils.getPrimaryEmailAddress(this);

        if(email != null)
            edtTxtEmail.setText(email);

        if(number != null)
            edtTxtMobile.setText(number);

        mRadioGroupGender = (RadioGroup) findViewById(R.id.genderRadioGroup);


        mSpinBGroup = (Spinner)findViewById(R.id.spinnerBloodGroup);
        mSpinBGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBloodGroup = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mBtnRegister = (Button) findViewById(R.id.btnRegister);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Donor donor = populateDonor();
                  if(ValidationUtils.validateDonateScreen(donor)){


                      ParseObject regObject = new ParseObject("Donor");
                      regObject.put("email",donor.getEmail());
                      regObject.put("name",donor.getName());
                      regObject.put("gender",donor.getGender());
                      regObject.put("above18","yes");
                      regObject.put("bloodGroup",donor.getBloodGroup());
                      regObject.saveInBackground();

                  }
            }
        });



    }

    private Donor populateDonor(){
        Donor donor = null;

        int radioId = mRadioGroupGender.getCheckedRadioButtonId();
        mRadioButton = (RadioButton) findViewById(radioId);

        String email = edtTxtEmail.getText().toString();
        String phone = edtTxtMobile.getText().toString();
        String name = edtTxtName.getText().toString();
        String gender = mRadioButton.getText().toString();

        Log.d(TAG,"Donor details "+email+" name "+name+" gender "+gender+" bldgrp "+mBloodGroup);

        donor = new Donor(email,name,gender,"yes",mBloodGroup);



        return donor;
    }



}
