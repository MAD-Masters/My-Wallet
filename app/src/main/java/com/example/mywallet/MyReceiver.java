package com.example.mywallet;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;

public class MyReceiver extends BroadcastReceiver {

    private static final String NOTIFICATION_CHANNEL_ID = "Channel01";
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context =context;
        /*Log.d("notification", "My Receiver Executing " );
        intent = new Intent(context, MyService.class);
        context.startService(intent);*/

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name =  preferences.getString("name", "User");
        name = name.split(" ")[0];

        Intent notifyIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, notifyIntent, 0);


        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyUpdate")
                .setContentTitle("Remainder")
                .setContentText("Hey " + name + ", Let's update your wallet")
                .setSmallIcon(R.drawable.wallet)
                .setPriority(Notification.PRIORITY_MAX)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.wallet_new))
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "myNotificationChannel";
            String description = "Channel for wallet update remainder";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel("notifyUpdate", channelName, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}
