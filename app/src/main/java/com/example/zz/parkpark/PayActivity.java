package com.example.zz.parkpark;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class PayActivity extends FragmentActivity {

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        index = this.getIntent().getExtras().getInt("POS");
        initialInfoCard(index);


        // set statue bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(0xff599ac0);

    }


    /**
     *  Button click event still host in root acitivity
     *  Just call */
    public void onBackEvent(View v) {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    public void onTextClick(View v) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();

        switch ( v.getId() ) {
            case R.id.type_hour:
                bundle.putInt("TYPE", 0);
                break;
            case R.id.type_day:
                bundle.putInt("TYPE",1);
                break;
            case R.id.type_month:
                bundle.putInt("TYPE",2);
                break;
        }
        bundle.putInt("POS",index);

        intent.putExtras(bundle);
        this.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        //Toast.makeText(this, "TextViewClicked", Toast.LENGTH_LONG).show();
    }

    /**
     * initial info card view */
    public void initialInfoCard(int index) {
        ImageView photo = (ImageView)findViewById(R.id.pay_photo);
        TextView  addr  = (TextView)findViewById(R.id.pay_addr);

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

}
