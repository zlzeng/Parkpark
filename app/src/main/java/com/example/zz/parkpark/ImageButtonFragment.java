package com.example.zz.parkpark;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageButtonFragment extends Fragment {

    public static class Rentype {
        public static final int HOUR   = 0;
        public static final int DAY    = 1;
        public static final int MONTH  = 2;

    }


    public ImageButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_button, container, false);
    }


}
