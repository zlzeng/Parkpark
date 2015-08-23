package com.example.zz.parkpark;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


/**
 * A simple {@link Fragment} subclass.
 */
public class WalletActivityFragment extends Fragment {


    public WalletActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet_activity, container, false);
    }

    public static void addCardBtnUp(View layout, View btn) {
        layout.setVisibility(View.INVISIBLE);
        btn.setTranslationY(5);
    }

    public static void addCardBtnDown(View layout, View btn) {
        btn.setTranslationY(500);
        layout.setVisibility(View.VISIBLE);
    }
}
