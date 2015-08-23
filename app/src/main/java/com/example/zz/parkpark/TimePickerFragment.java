package com.example.zz.parkpark;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by zz on 15/6/8.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        String nTime;
        if ( minute<10 )
            nTime = hourOfDay+":0"+minute;
        else
            nTime = hourOfDay+":"+minute;

        if (DetailActivity.isStartTime) {
            DetailActivity.mStartTime.setText(nTime);
            DetailActivity.calculatePrices();
        }
        else {
            DetailActivity.mEndTime.setText(nTime);
            DetailActivity.calculatePrices();
        }
    }
}
