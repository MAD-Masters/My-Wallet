package com.example.mywallet;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class UserSettings extends AppCompatActivity {
    private static View timePickLayoutN;
    private NotificationManagerCompat notificationManagerCompat;
    private LinearLayout timePickLayout;
    private TextView timeRemind;
    private int mHour, mMinute;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        timeRemind = findViewById(R.id.timeTextView);
        timePickLayout = findViewById(R.id.timeRemainder);
        timePickLayoutN = findViewById(R.id.timeRemainder);

        setRemainTimeLayout(getApplicationContext());

        timePickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                final TimePickerDialog timePickerDialog = new TimePickerDialog(UserSettings.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = view.getHour();
                        mMinute = view.getMinute();

                        int minutes = mMinute + (mHour * 60);
                        System.out.println("time " + mHour + " " + mMinute);

                        DateFormat df = new SimpleDateFormat("hh:mm a");
                        Date date = new Date(0, 0, 0, mHour, mMinute);
                        System.out.println(date.toString());
                        timeRemind.setText(df.format(date));

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, mHour);
                        calendar.set(Calendar.MINUTE, mMinute);

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putLong("time", calendar.getTimeInMillis());
                        editor.apply();

                        Intent notifyIntent = new Intent(getApplicationContext(), MyReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        Log.d("notification", "Get Notification set at milliseconds " + String.valueOf(calendar.getTimeInMillis()));
                        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
                        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    }
                }, mHour, mMinute, false);


                timePickerDialog.show();
            }
        });
    }

    public void setRemainTimeLayout(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isNotify =  preferences.getBoolean("isNotify", false);
        long time = preferences.getLong("time", 0);
        System.out.println("IS NOTIFY " + isNotify);
        System.out.println("Time from SHARED PREF " + String.valueOf(time));


        if (isNotify) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            System.out.println("Time from shared pref " + calendar.getTime().toString());
            int hour =  calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            timePickLayout.setVisibility(View.VISIBLE);
            DateFormat df = new SimpleDateFormat("h:mm a");
            Date date = new Date(0, 0, 0, hour, minute);
            System.out.println("date enterd " + hour + " " + minute);
            timeRemind.setText(df.format(date));
            System.out.println("set timed " + df.format(date));
        } else {
            timePickLayout.setVisibility(View.INVISIBLE);
        }
    }

    public static void setRemainTimeLayoutN(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isNotify =  preferences.getBoolean("isNotify", false);

        if (isNotify) {
            timePickLayoutN.setVisibility(View.VISIBLE);
            long time = preferences.getLong("time", 0);
            Log.d("notification", "Get time from SharedPref " + String.valueOf(time));

            Intent notifyIntent = new Intent(context, MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,
                    AlarmManager.INTERVAL_DAY, pendingIntent);

        } else {
            timePickLayoutN.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(context, MyReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(sender);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    if (key.equals("isNotify")) {
                        setRemainTimeLayoutN(getContext());
                    }
                }
            };
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        }
    }


}