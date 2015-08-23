package com.example.zz.parkpark;

import android.content.Context;

/**
 * Created by zz on 15/5/31.
 */
public class DataSources {
    static String[] addressImageList = {"photo_spotview1",
            "photo_spotview2",
            "photo_spotview3",
            "photo_spotview4"};
    static String[] addressList = {"海濱南岸停車場", "黃埔新村路面停車場", "黃埔花園二期停車場", "翠華四期12號"};

    static int[] id;

    public static class ParkingLotIndex {
        public static final int ONE   = 0;
        public static final int TWO   = 1;
        public static final int THREE = 2;
        public static final int FOUR  = 3;
    }

    static void initImageId(Context mContext) {
        id = new int[4];
        for (int i=0; i<4; i++) {
            id[i] = mContext.getResources().getIdentifier(DataSources.addressImageList[i], "drawable", mContext.getPackageName() );
        }
    }
}
