package com.bijesh.donateblood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijesh.donateblood.R;
import com.bijesh.donateblood.activities.HomeActivity;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getCanonicalName();
    private TextView mTxtViewStatus;
    private TextView mTxtViewMessages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_home, container, false);
        init(retView);
        return retView;
    }

    private void init(View view){
        mTxtViewStatus = (TextView)view.findViewById(R.id.txtViewQuote);
        mTxtViewMessages = (TextView) view.findViewById(R.id.txtViewPushRequests);


        HomeActivity homeActivity = (HomeActivity)getActivity();
        mTxtViewMessages.setText(homeActivity.getPushMessage());
    }
}
