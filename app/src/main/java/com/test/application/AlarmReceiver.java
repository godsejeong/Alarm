package com.test.application;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    NotificationManager NotiManager;
    final String TESTID = "TestId";
    Alarm alarm;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        alarm = new Alarm(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            NotiChnnal();
        notification(context);
    }

    public void notification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder Noti = new NotificationCompat.Builder(context, TESTID);

        Noti.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Title")
                .setContentText("content")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        NotiManager.notify(7777, Noti.build());



        Toast.makeText(context, "알람생성", Toast.LENGTH_LONG).show();
    }

    public void NotiChnnal() {
        NotificationChannel notificationChannel = new NotificationChannel(TESTID, "testName", NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setDescription("channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.GREEN);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotiManager.createNotificationChannel(notificationChannel);
    }
}

