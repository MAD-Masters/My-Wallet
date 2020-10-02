package com.example.mywallet;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;


public class MyService extends IntentService {
    public MyService() {
        super("Notification Create");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        createNotification();
    }

    private static final String NOTIFICATION_CHANNEL_ID = "Channel01";
    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String name =  preferences.getString("name", "User");
            name = name.split(" ")[0];
            String namee = "Remainder";
            String description = "Remainder to update Wallet";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, namee, importance);
            notificationChannel.setDescription(description);

            Intent notifyIntent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, notifyIntent, 0);

            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Remainder")
                    .setContentText("Hey " + name + ", Let's update your wallet")
                    .setSmallIcon(R.drawable.wallet)
                    .setChannelId(NOTIFICATION_CHANNEL_ID)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.wallet_new))
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            // Issue the notification.
            notificationManager.notify(1 , notification);
        }
    }
}
