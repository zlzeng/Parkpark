package com.example.zz.parkpark;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonPanelFragment extends Fragment {

    static View mRootView;


    public ButtonPanelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_buttonpanel, container, false);
        return mRootView;
    }


    public static void hideAllFragment(FragmentTransaction ft) {
        int length = MapsActivity.fragments.size();
        for (int i=0; i < length; i++) {
            ft.hide(MapsActivity.fragments.get(i));
        }
        ft.commit();
        MapsActivity.mSupportMapFragment.getView().setVisibility(View.INVISIBLE);
    }

    public static void tabClick(View v, FragmentTransaction ft) {
        Button mBtn = (Button)mRootView.findViewById(v.getId());
        switch (v.getId()) {
            case R.id.tab_map:
                ft.show(MapsActivity.fragments.get(0));
                MapsActivity.mSupportMapFragment.getView().setVisibility(View.VISIBLE);
                //mBtn.setBackground(MapsActivity.mContext.getDrawable(R.drawable.nav_map_clk));
                //Toast.makeText(MapsActivity.mContext, "this is tab map", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_record:
                ft.show(MapsActivity.fragments.get(1));
                //mBtn.setBackground(MapsActivity.mContext.getDrawable(R.drawable.nav_record_clk));
                //Toast.makeText(MapsActivity.mContext, "this is tab record", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_wallet:
                ft.show(MapsActivity.fragments.get(2));
                //mBtn.setBackground(MapsActivity.mContext.getDrawable(R.drawable.nav_wallet_clk));
                break;
            case R.id.tab_profile:
                ft.show(MapsActivity.fragments.get(3));
                //mBtn.setBackground(MapsActivity.mContext.getDrawable(R.drawable.nav_profile_clk));
                break;
        }
        ft.commit();
    }

    public static void setButtonViewUCLK() {
        Button mBtn = (Button)mRootView.findViewById(R.id.tab_map);
        mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.map));

        mBtn = (Button)mRootView.findViewById(R.id.tab_record);
        mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.message));

        mBtn = (Button)mRootView.findViewById(R.id.tab_wallet);
        mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.wallet));

        mBtn = (Button)mRootView.findViewById(R.id.tab_profile);
        mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.profile));
    }

    /**
     * only one button in panel show be select
     * this method would be invoke in ListActivity class
     * this method is used to avoid some background image setting in the transition
     * between MapsA <--> ListA */
    public static void changeButtonView(View v) {
        Button mBtn = (Button)mRootView.findViewById(v.getId());
        switch (v.getId()) {
            case R.id.tab_map:
                mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.map2));
                break;
            case R.id.tab_record:
                mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.message2));
                //Toast.makeText(MapsActivity.mContext, "record clk!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_wallet:
                mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.wallet2));
                break;
            case R.id.tab_profile:
                mBtn.setBackground(MapsActivity.mContext.getDrawable(R.mipmap.profile2));
                break;
        }
    }


}
