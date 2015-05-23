package com.bijesh.donateblood.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.bijesh.donateblood.R;
import com.bijesh.donateblood.fragments.HomeFragment;
import com.bijesh.donateblood.fragments.NavigationDrawerFragment;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import org.json.JSONObject;

import java.util.List;

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

//        showPushMessage();


        ParsePush.subscribeInBackground("Donate");
        List<String> allChannels = ParseInstallation.getCurrentInstallation().getList("channels");
        Log.d(TAG,"all channels "+allChannels);
        sendPush();



    }

    private void sendPush(){
        ParsePush push = new ParsePush();
        push.setChannel("Donate");
        push.setMessage("The Giants just scored! It's now 2-2 against the Mets.");
        push.sendInBackground();

    }

    private void sendPush1(){
        // Create our Installation query
        ParseQuery pushQuery = ParseInstallation.getQuery();
        pushQuery.whereEqualTo("injuryReports", true);

// Send push notification to query
        ParsePush push = new ParsePush();
        push.setQuery(pushQuery); // Set our Installation query
        push.setMessage("Willie Hayes injured by own pop fly.");
        push.sendInBackground();
    }

    private void showPushMessage(){
        try{
            Bundle b = getIntent().getExtras();
            JSONObject jsonObject = new JSONObject(b.getString("com.parse.Data"));
            Toast.makeText(getApplicationContext(), "" + jsonObject.getString("alert"), Toast.LENGTH_LONG).show();
            String data=jsonObject.getString("alert");
            Log.d(TAG,"data "+data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void init(){
        HomeFragment fragment = new HomeFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.home_container,fragment);
        fragmentTransaction.commit();

    }

    private void initGCM(){
//        ParseInstallation.getCurrentInstallation().put("")
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
