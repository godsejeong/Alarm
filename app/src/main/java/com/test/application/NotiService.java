package com.test.application;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class NotiService extends Service {
    Alarm aralm;
    NotificationManager NotiManager;
    final String TESTID = "TestId";


    PendingIntent Alarmintent;
    AlarmManager am;
    int huor = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Alarmintent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                new Intent(getApplicationContext(), AlarmReceiver.class), 0);

        am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("test", "serviceDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("test", "service");

        test();


//        NotiManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//            NotiChnnal();
//        notification(this);


        return super.onStartCommand(intent, flags, startId);
    }

    public void test() {
        //long delay = TimeUnit.HOURS.toMillis(2L);//2시
        long delay = TimeUnit.MINUTES.toMinutes(1L);
        Calendar calendar = Calendar.getInstance();
        Calendar currentCal = Calendar.getInstance();
        LocalTime present = LocalTime.now();

        huor = present.getMinute();

        Log.e("hour1", String.valueOf(huor));

//        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, huor);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long intendedTime = calendar.getTimeInMillis();

        am.setRepeating(AlarmManager.RTC, intendedTime, delay, Alarmintent);

    }
}