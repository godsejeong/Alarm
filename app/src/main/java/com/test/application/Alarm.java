package com.test.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Alarm {
    PendingIntent Alarmintent;
    AlarmManager am;
    int huor = 0;
//    Boolean bl;

    public Alarm(Context context) {
//        this.bl = bl;
        Alarmintent = PendingIntent.getBroadcast(context, 0,
                new Intent(context, AlarmReceiver.class), 0);

        am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
    }

    public void clearAlarm(){
        am.cancel(Alarmintent);
    }

    public void alarm() {
            //long delay = TimeUnit.HOURS.toMillis(2L);//2시
            long delay = TimeUnit.MINUTES.toMillis(1L);
            Calendar calendar = Calendar.getInstance();
            Calendar currentCal = Calendar.getInstance();
            LocalTime present = LocalTime.now();

            huor = present.getMinute();

            Log.e("hour1", String.valueOf(huor));

            calendar.set(Calendar.HOUR_OF_DAY, 7);
            calendar.set(Calendar.MINUTE, huor);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            long intendedTime = calendar.getTimeInMillis();
            long currentTime = currentCal.getTimeInMillis();

            am.setRepeating(AlarmManager.RTC,intendedTime,System.currentTimeMillis() + delay, Alarmintent);
    }
}
