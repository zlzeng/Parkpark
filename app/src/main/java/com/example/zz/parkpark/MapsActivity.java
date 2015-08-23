package com.example.zz.parkpark;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    static Context mContext;
    private SearchFragment searchFragment;
    private Marker mMarker;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    static List<Fragment> fragments;
    static SupportMapFragment mSupportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        searchFragment = (SearchFragment)getFragmentManager().findFragmentById(R.id.searchFragment);

        mContext = getApplicationContext();
        setUpMapIfNeeded();
        setUpFragment();
        setUpLocationService();

        // set statue bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(0xff599ac0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mSupportMapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
            mMap = mSupportMapFragment.getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        DataSources.initImageId(mContext);
        mMap.setOnMarkerClickListener(this);
        //mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(22.303745, 114.186428)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
        mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(22.303745, 114.186428)));

        //setUpLocationService();
    }

    /**
     * set up the fragment */
    private void setUpFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragments = new ArrayList<>();

        fragments.add( fragmentManager.findFragmentById(R.id.searchFragment) ); // index 0
        //fragments.add( fragmentManager.findFragmentById(R.id.tab) ); // index
        fragments.add( new RecordActivityFragment() ); // index 1
        fragments.add( new WalletActivityFragment() ); // index 2
        fragments.add( new PersonalInfoFragment() ); // index 3

        for (int i=1; i<4; i++) {
            fragmentTransaction.add( R.id.tab, fragments.get(i) );
        }

        for (int i=1; i<4; i++) {
            fragmentTransaction.hide( fragments.get(i) );
        }

        fragmentTransaction.commit();
    }

    /**
     * set up the gps function of this app
     * add by ZZ in 06/11/2015 */

    private void setUpLocationService() {
        //mMap.setMyLocationEnabled(true);
        LocationManager mLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = mLocationManager.getBestProvider(criteria, true);
        Location location = mLocationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
        }
        mLocationManager.requestLocationUpdates(provider, 200000, 0, this);
    }

    /**
     *  Button click event still host in main acitivity
     *  Just call
     *  This function is use to handle the button click event on the search bar */
    public void onSearchBtnClick(View v) {
        searchFragment.onSearchBtnClick(v);
    }

    /**
     * This function handle the edit text event on serarch bar
     * add by ZZ in 06/11/2015 */
    public void onSearchTxtClick(View v) {
        searchFragment.onSearchTxtClk(v);
    }

    /**
     *  Bottom tab click event */
    public void onTabClick(View v) {
        ButtonPanelFragment.hideAllFragment(fragmentManager.beginTransaction());
        ButtonPanelFragment.setButtonViewUCLK();
        ButtonPanelFragment.tabClick(v, fragmentManager.beginTransaction());

        ButtonPanelFragment.changeButtonView(v);
    }

    /**
     *  Click the Marker open new activity*/
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if( marker.equals(mMarker) ) {
            Intent intent = new Intent(this, PayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("POS", 0);
            //Toast.makeText(mContext, "List pos 1", Toast.LENGTH_SHORT).show();
            intent.putExtras(bundle);
            this.startActivity(intent);
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            return true;
        }

        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        LatLng latLng = new LatLng(lat, lng);

        // move the camera view to current position
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // zoom in the Google map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * Add credit card event */
    public void onAddCreditCardClick(View v) {
        View framelayout = findViewById(R.id.widget_paypal);
        View btn = findViewById(R.id.btn_add_card);
        if (framelayout.getVisibility() == View.INVISIBLE) {
            WalletActivityFragment.addCardBtnDown(framelayout, btn);

        } else {
            WalletActivityFragment.addCardBtnUp(framelayout, btn);
        }
    }

    /**
     * Personal Info Fragment Panel Click event */
    public void onPanelClick(View v) {
        View btn1 = findViewById(R.id.panel_addr);
        View btn2 = findViewById(R.id.panel_phone);
        View btn3 = findViewById(R.id.panel_car);
        if ( v.equals(btn1) ) {
            Intent intent = new Intent(this, InputAddrActivity.class);
            startActivity(intent);
        } else if ( v.equals(btn2) ) {
            Intent intent = new Intent(this, InputPhoneActivity.class);
            startActivity(intent);
        } else if ( v.equals(btn3) ) {
            Intent intent = new Intent(this, VerifyCarActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Record Fragment tick image click event */
    public void onNotificationClick(View v) {
        RecordActivityFragment.onNotificationClk();
    }

    /**
     * Record Fragment info card click event */
    public void onRecordCardClick(View v) {
        Intent intent = new Intent(this, RecordDetailActivity.class);
        startActivity(intent);
    }
}
