package com.example.zz.parkpark;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordActivityFragment extends Fragment {

    public static NotificationCompat.Builder mBuilder;
    public static NotificationManager mManager;
    public static int notificationID;

    private PendingIntent mPendingIntent;



    public RecordActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        notificationID = 001;

        Intent intent = new Intent(MapsActivity.mContext, RecordDetailActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MapsActivity.mContext);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(RecordDetailActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);

        mPendingIntent = PendingIntent.getActivity (
                MapsActivity.mContext,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT );

        mBuilder = new NotificationCompat.Builder(MapsActivity.mContext)
                        .setSmallIcon(R.mipmap.small_logo)
                        .setColor(0xff70b8ef)
                        .setContentTitle("成功預定")
                        .setContentText("輕觸查看詳情。")
                        .setContentIntent(mPendingIntent);

        mManager = (NotificationManager)MapsActivity.mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record_activity, container, false);
    }

    /**
     * Record Fragment element_complete icon click event */
    public static void onNotificationClk() {
        mManager.notify(notificationID, mBuilder.build());
    }


}
