package com.bijesh.donateblood.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bijesh.donateblood.R;
import com.bijesh.donateblood.activities.ContactActivity;
import com.bijesh.donateblood.activities.RegisterActivity;
import com.bijesh.donateblood.activities.NeedActivity;
import com.bijesh.donateblood.activities.SponserActivity;
import com.bijesh.donateblood.adapters.NavigationDrawerAdapter;
import com.bijesh.donateblood.models.ui.NavigationDrawerOptions;
import com.bijesh.donateblood.storage.DonateSharedPrefs;
import com.bijesh.donateblood.utils.BaseUtils;
import com.bijesh.donateblood.utils.ValidationUtils;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements NavigationDrawerAdapter.NavigationDrawerClickListener{


    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private NavigationDrawerAdapter mNavigationDrawerAdapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View mNavigationContainerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(DonateSharedPrefs.getInstance(getActivity()).getStringData(DonateSharedPrefs.IS_NAVIGATION_DRAWER_LEARNED,"false"));
        if(savedInstanceState != null){
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawer_recycle_view);
        mNavigationDrawerAdapter = new NavigationDrawerAdapter(getActivity(), Arrays.asList(
                new NavigationDrawerOptions("Register"), new NavigationDrawerOptions("Request to all donors"),
                new NavigationDrawerOptions("Our Sponsors"),
                new NavigationDrawerOptions("Share this app"),
                new NavigationDrawerOptions("Contact us")));
        mRecyclerView.setAdapter(mNavigationDrawerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNavigationDrawerAdapter.setClickListener(this);

        return layout;
    }


    public void setUp(int fragmentId,DrawerLayout drawerLayout, final Toolbar toolbar) {
        this.mNavigationContainerView = getActivity().findViewById(fragmentId);
        this.mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    DonateSharedPrefs.getInstance(getActivity()).setStringData(DonateSharedPrefs.IS_NAVIGATION_DRAWER_LEARNED,"true");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                if(slideOffset < 0.6)
//                   toolbar.setAlpha(1-slideOffset);
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(mNavigationContainerView);
        }

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });

    }

    @Override
    public void onNavigationItemClick(View view, int position) {
//        Toast.makeText(getActivity(),"Clicked on position "+position,Toast.LENGTH_LONG).show();
        switch (position){
            case 0:
                if(ValidationUtils.isUserAlreadyRegistered(getActivity())) {
                    Toast.makeText(getActivity(),"Already Registered",Toast.LENGTH_LONG).show();
                }else{
                    startActivity(new Intent(getActivity(), RegisterActivity.class));
                }
                break;
            case 1:
                if(ValidationUtils.isUserAlreadyRegistered(getActivity())) {
                    long currentTime = System.currentTimeMillis();
                    long previousRequestTime = DonateSharedPrefs.getInstance(getActivity()).getLongData(DonateSharedPrefs.HAS_ALREADY_NOTIFIED_DONORS_RECENTLY, 0);
                    if(ValidationUtils.hasAlreadySentRequest(currentTime,previousRequestTime)) {
                        startActivity(new Intent(getActivity(), NeedActivity.class));
                    }else{
                        Toast.makeText(getActivity(),"You have already sent request",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Please Register",Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                startActivity(new Intent(getActivity(), SponserActivity.class));
                break;
            case 3:
                BaseUtils.shareApplication(getActivity());
                break;
            case 4:
                startActivity(new Intent(getActivity(), ContactActivity.class));
                break;
        }
    }



}
