package com.bijesh.donateblood.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bijesh.donateblood.R;
import com.bijesh.donateblood.models.ui.NavigationDrawerOptions;


import java.util.Collections;
import java.util.List;

/**
 * Created by bijesh on 5/20/2015.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavigationDrawerViewHolder>{


    private LayoutInflater inflater;
    private List<NavigationDrawerOptions> mOptionsList = Collections.emptyList();
    private Context mContext;
    private NavigationDrawerClickListener clickListener;

    public NavigationDrawerAdapter(Context context,List<NavigationDrawerOptions> data){
        inflater = LayoutInflater.from(context);
        this.mOptionsList = data;
        this.mContext = context;
    }

    public void setClickListener(NavigationDrawerClickListener clickListener){
        this.clickListener = clickListener;
    }


    @Override
    public NavigationDrawerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.row_navigation_drawer,viewGroup,false);
        NavigationDrawerViewHolder viewHolder = new NavigationDrawerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationDrawerViewHolder navigationDrawerViewHolder, int i) {
        navigationDrawerViewHolder.txtOption.setText(mOptionsList.get(i).getOption());
    }


    @Override
    public int getItemCount() {
        return mOptionsList.size();
    }




    class NavigationDrawerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtOption;

        public NavigationDrawerViewHolder(View itemView) {
            super(itemView);
            txtOption = (TextView) itemView.findViewById(R.id.txtView_navigation_drawer);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(clickListener != null){
               clickListener.onNavigationItemClick(view,getAdapterPosition());
            }
        }
    }


    public interface NavigationDrawerClickListener{
        public void onNavigationItemClick(View view,int position);
    }


}
