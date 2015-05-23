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
import com.bijesh.donateblood.activities.DonateActivity;
import com.bijesh.donateblood.adapters.NavigationDrawerAdapter;
import com.bijesh.donateblood.models.ui.NavigationDrawerOptions;
import com.bijesh.donateblood.utils.BaseUtils;

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


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawer_recycle_view);
        mNavigationDrawerAdapter = new NavigationDrawerAdapter(getActivity(), Arrays.asList(
                new NavigationDrawerOptions("I donate"), new NavigationDrawerOptions("I need"),
                new NavigationDrawerOptions("Share this app")));
        mRecyclerView.setAdapter(mNavigationDrawerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNavigationDrawerAdapter.setClickListener(this);

        return layout;
    }


    public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar) {
            this.mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
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
                startActivity(new Intent(getActivity(), DonateActivity.class));
                break;
            case 1:
                break;
            case 2:
                BaseUtils.shareApplication(getActivity());
                break;
        }
    }
}
