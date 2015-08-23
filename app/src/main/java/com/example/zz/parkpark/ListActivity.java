package com.example.zz.parkpark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class ListActivity extends Activity {

    static Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int[] id;
    /*private String[] addressImageList = {"photo_spotview1",
            "photo_spotview2",
            "photo_spotview3",
            "photo_spotview4"};*/
    //private String[] addressList = {"Parking Lot1", "Parking Lot2", "Parking Lot3", "Parking Lot4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclView);
        mRecyclerView.setHasFixedSize(true);

        //using layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //using adapter
        //id = new int[4];
        /*for (int i=0; i<4; i++) {
            id[i] = getResources().getIdentifier(DataSources.addressImageList[i], "drawable", getPackageName() );
        }*/
        id = DataSources.id;
        mAdapter = new RecycleAdapter(DataSources.addressList, id);
        mRecyclerView.setAdapter(mAdapter);

        // change the top bar button image
        SearchFragment.topBarBtn.setImageResource(R.mipmap.shape2);

        // set statue bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(0xff599ac0);
    }

    /**
     *  Button click event still host in main acitivity
     *  Just call */
    public void onSearchBtnClick(View v) {
        super.onBackPressed();
    }

    /**
     * Some as the one in MapActivity */
    public void onSearchTxtClick(View v) {

    }

    /**
     * Bottom panel click event */
    public void onTabClick(View v) {
        ButtonPanelFragment.setButtonViewUCLK();
        ButtonPanelFragment.changeButtonView(v);
    }

    /**
     * More option button click event */
    public void onBtnMoreOptionClick(View v) {
        Intent intent = new Intent(mContext, MoreOptionActivity.class);
        this.startActivity(intent);
    }


}
