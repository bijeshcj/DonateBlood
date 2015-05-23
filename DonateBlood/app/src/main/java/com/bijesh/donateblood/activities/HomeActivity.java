package com.bijesh.donateblood.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;


import com.bijesh.donateblood.R;
import com.bijesh.donateblood.fragments.HomeFragment;
import com.bijesh.donateblood.fragments.NavigationDrawerFragment;
import com.parse.ParseObject;

/**
 * Created by bijesh on 5/13/2015.
 */
public class HomeActivity extends ActionBarActivity {


    private static final String TAG = HomeActivity.class.getCanonicalName();
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mMainFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);


//        set tool bar here
        mToolBar = (Toolbar)findViewById(R.id.app_toolbar);
        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        set navigation drawer here
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().
                findFragmentById(R.id.fragment_nav_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationDrawerFragment.setUp(mDrawerLayout, mToolBar);



        mMainFrameLayout = (FrameLayout) findViewById(R.id.home_container);
        init();

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "saibaba");
//        testObject.saveInBackground();





    }

    private void init(){
        HomeFragment fragment = new HomeFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.home_container,fragment);
        fragmentTransaction.commit();

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if(mDrawerLayout != null){
            mDrawerLayout.closeDrawers();
        }
    }
}
