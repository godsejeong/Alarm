package com.test.application;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Switch mainSwitch;
    Intent intent;
    Alarm aralm;

//    PendingIntent Alarmintent;
//    AlarmManager am;
//    int huor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this,NotiService.class);

//        Alarmintent = PendingIntent.getService(getApplicationContext(), 0,
//                new Intent(getApplicationContext(), NotiService.class), 0);

        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //노티피케이션 제거
        nm.cancel(7777);

//        am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
//        aralm = new Alarm(getApplicationContext());

        mainSwitch = findViewById(R.id.mainSwitch);

        mainSwitch.setChecked(getChecked());

        mainSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(intent);
                    savePreferences(true);
                } else {
                    stopService(intent);
                    savePreferences(false);
                }
            }
        });
    }

//    public void test(){
//        //long delay = TimeUnit.HOURS.toMillis(2L);//2시
//        Calendar calendar = Calendar.getInstance();
//        Calendar currentCal = Calendar.getInstance();
//        LocalTime present = LocalTime.now();
//
//        huor = present.getMinute();
//
//        Log.e("hour1", String.valueOf(huor));
//
////        calendar.set(Calendar.HOUR_OF_DAY, 9);
//        calendar.set(Calendar.MINUTE, huor);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        long intendedTime = calendar.getTimeInMillis();
//
//        am.setRepeating(AlarmManager.RTC,intendedTime,1000,Alarmintent);
//
//    }

    public void savePreferences(boolean is) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("ischeck",is);
        editor.commit();
    }

    public boolean getChecked() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        return pref.getBoolean("ischeck", false);
    }
}
