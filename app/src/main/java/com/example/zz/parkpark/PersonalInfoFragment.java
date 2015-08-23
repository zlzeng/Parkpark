package com.example.zz.parkpark;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInfoFragment extends Fragment {

    public static TextView mBtnAddr;
    public static TextView mBtnPhone;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_personal_info, container, false);

        mBtnAddr = (TextView)v.findViewById(R.id.btn_address);
        mBtnPhone = (TextView)v.findViewById(R.id.btn_phone);

        return v;
    }


}
