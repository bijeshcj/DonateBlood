package com.bijesh.donateblood.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.bijesh.donateblood.R;
import com.bijesh.donateblood.base.ui.BaseActivity;
import com.bijesh.donateblood.base.ui.BaseFragmentActivity;
import com.bijesh.donateblood.fragments.NavigationDrawerFragment;

/**
 * Created by bijesh on 5/13/2015.
 */
public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//        set tool bar here


//        set navigation drawer here
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().
                findFragmentById(R.id.fragment_nav_drawer);


    }
}
