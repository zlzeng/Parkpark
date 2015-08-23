package com.example.zz.parkpark;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public static ImageView topBarBtn;
    public static EditText mEditText;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        topBarBtn = (ImageView)v.findViewById(R.id.topBarButton);
        mEditText = (EditText)v.findViewById(R.id.editText);

        /*mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mEditText.setCursorVisible(true);
                    mEditText.setText("");
                    mEditText.setTextColor(0xff111111);
                    //mEditText.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);


                } else {
                    mEditText.setCursorVisible(false);
                    mEditText.setText("Search");
                    mEditText.setTextColor(0xffc9c9c9);
                }

            }
        });*/

        return v;
    }

    public void onSearchBtnClick(View v) {
        try {
            if (MapsActivity.mContext == getActivity().getApplicationContext()) {
                Intent intent = new Intent(MapsActivity.mContext, ListActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(MapsActivity.mContext, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onSearchTxtClk(View v) {

    }
}
