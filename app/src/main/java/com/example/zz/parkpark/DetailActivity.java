package com.example.zz.parkpark;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends FragmentActivity {

    public static Context mContext;

    public static TextView mDate;
    public static TextView mDateD1;
    public static TextView mDateD2;
    public static TextView mDateM1;
    public static TextView mDateM2;

    public static TextView mStartTime;
    public static TextView mEndTime;

    public static boolean isStartTime;
    public static boolean isStartDate;

    public static TextView mPriceSum;
    public static TextView mPriceSumDay;
    public static TextView mPriceSumMon;
    public static TextView mPrice;

    public List<RelativeLayout> mCardLayout;

    public static int type;
    public int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mContext = getApplicationContext();
        index = this.getIntent().getExtras().getInt("POS");
        type = this.getIntent().getExtras().getInt("TYPE");

        mCardLayout = new ArrayList<>();
        mCardLayout.add((RelativeLayout)findViewById(R.id.type_hour_card));
        mCardLayout.add((RelativeLayout) findViewById(R.id.type_day_card));
        mCardLayout.add((RelativeLayout) findViewById(R.id.type_month_card));

        TextView textView = (TextView)TitleFragment.v.findViewById(R.id.textView11);
        textView.setText("車位詳情");

        mDate = (TextView)findViewById(R.id.date);
        mStartTime = (TextView)findViewById(R.id.start_time);
        mEndTime = (TextView)findViewById(R.id.end_time);

        mPrice = (TextView)findViewById(R.id.price);
        mPriceSum = (TextView)findViewById(R.id.price_sum);
        mPriceSumDay = (TextView)findViewById(R.id.price_sum_day);
        mPriceSumMon = (TextView)findViewById(R.id.price_sum_mon);

        mDateD1  = (TextView)findViewById(R.id.date_day1);
        mDateD2  = (TextView)findViewById(R.id.date_day2);
        mDateM1  = (TextView)findViewById(R.id.date_mon_s);
        mDateM2  = (TextView)findViewById(R.id.date_mon_e);

        initialCardInfo(index);

        initialCardViewByType(type);

        // set statue bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(0xff599ac0);
    }

    /**
     * Initial card info */
    public void initialCardInfo(int index) {
        ImageView photo = (ImageView)findViewById(R.id.detail_image);
        TextView  addr  = (TextView)findViewById(R.id.detail_address);

        switch (index) {
            case DataSources.ParkingLotIndex.ONE:
                photo.setImageResource(DataSources.id[index]);
                addr.setText(DataSources.addressList[0]);
                break;
            case DataSources.ParkingLotIndex.TWO:
                photo.setImageResource(DataSources.id[index]);
                addr.setText(DataSources.addressList[1]);
                break;
            case DataSources.ParkingLotIndex.THREE:
                photo.setImageResource(DataSources.id[index]);
                addr.setText(DataSources.addressList[2]);
                break;
            case DataSources.ParkingLotIndex.FOUR:
                photo.setImageResource(DataSources.id[index]);
                addr.setText(DataSources.addressList[3]);
                break;
        }
    }

    /**
     * Initial card by type */
    public void initialCardViewByType(int type) {

        for (int index=0; index<3; index++)
            mCardLayout.get(index).setVisibility(View.INVISIBLE);

        switch (type) {
            case ImageButtonFragment.Rentype.HOUR:
                mCardLayout.get(type).setVisibility(View.VISIBLE);
                break;

            case ImageButtonFragment.Rentype.DAY:
                mCardLayout.get(type).setVisibility(View.VISIBLE);
                break;

            case ImageButtonFragment.Rentype.MONTH:
                mCardLayout.get(type).setVisibility(View.VISIBLE);
                break;
        }

    }

    /**
     * Back button event, back to pre activity */
    public void onBackEvent(View v) {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    /**
     * Appear a new dialog for user to select */
    public void onPickUpDate(View v) {
        if ( (v.getId() == R.id.start_date_day) || (v.getId() == R.id.start_date_mon))
            isStartDate = true;
        else
            isStartDate = false;

        DialogFragment mDialogFragment = new DatePickerFragment();
        mDialogFragment.show(getFragmentManager(), "datePicker");
    }

    /**
     * Appear a new dialog for user to select */
    public void onPickUpTime(View v) {
        if ( v.getId() == R.id.startTime )
            isStartTime = true;
        else
            isStartTime = false;

        DialogFragment mDialogFragment = new TimePickerFragment();
        mDialogFragment.show(getFragmentManager(), "timePicker");
    }

    /**
     * Book button click event handle */
    public void onBookClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setMessage("请确定信息无误!").setTitle("通知");
        builder.setMessage("请确定信息无误!");
        builder.setPositiveButton("確定",null).setNegativeButton("取消",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Click to calculate the prices */
    public void onCalculateClick(View v) {

        switch (type) {
            case ImageButtonFragment.Rentype.HOUR:
                break;

            case ImageButtonFragment.Rentype.DAY:
                break;

            case ImageButtonFragment.Rentype.MONTH:
                break;
        }

    }

    /**
     * Calculate the prices */
    public static void calculatePrices() {

        switch (type) {

            case ImageButtonFragment.Rentype.HOUR:
                String[] tmp1 = mStartTime.getText().toString().split(":");
                String[] tmp2 = mEndTime.getText().toString().split(":");
                int param = Integer.parseInt(tmp2[0]) - Integer.parseInt(tmp1[0]);
                mPriceSum.setText(Math.abs(param * 20) + "HKD");

                break;

            case ImageButtonFragment.Rentype.DAY:
                String d_tmp1 = mDateD1.getText().toString();
                String d_tmp2 = mDateD2.getText().toString();
                int d_param = Integer.parseInt(d_tmp2.substring(2,3)) - Integer.parseInt(d_tmp1.substring(2, 3)) ;
                mPriceSumDay.setText(Math.abs(d_param * 100) + "HKD");

                break;

            case ImageButtonFragment.Rentype.MONTH:
                String m_tmp1 = mDateM1.getText().toString();
                String m_tmp2 = mDateM2.getText().toString();
                int m_param = Integer.parseInt(m_tmp2.substring(0,1)) - Integer.parseInt(m_tmp1.substring(0, 1)) ;
                mPriceSumMon.setText(Math.abs(m_param * 2500) + "HKD");

                break;
        }

    }


}
