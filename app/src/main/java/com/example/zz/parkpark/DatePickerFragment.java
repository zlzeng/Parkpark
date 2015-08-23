package com.example.zz.parkpark;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by zz on 15/6/7.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        String nDate = (month+1)+"月"+day+"日";

        switch (DetailActivity.type) {
            case ImageButtonFragment.Rentype.HOUR:
                DetailActivity.mDate.setText(nDate);
                break;

            case ImageButtonFragment.Rentype.DAY:
                if (DetailActivity.isStartDate) {
                    DetailActivity.mDateD1.setText(nDate);
                    DetailActivity.calculatePrices();
                }
                else {
                    DetailActivity.mDateD2.setText(nDate);
                    DetailActivity.calculatePrices();
                }
                break;

            case ImageButtonFragment.Rentype.MONTH:
                if (DetailActivity.isStartDate) {
                    DetailActivity.mDateM1.setText(nDate);
                    DetailActivity.calculatePrices();
                }
                else {
                    DetailActivity.mDateM2.setText(nDate);
                    DetailActivity.calculatePrices();
                }
                break;
        }

    }

}
