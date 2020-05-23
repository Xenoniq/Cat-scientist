package com.example.viktorina;

import android.content.Context;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Chronometer;

import java.util.Date;

public class Stopwatch {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running = false;

    public Stopwatch(Context context) {
        this.chronometer = new Chronometer(context);
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    public void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    public String getStringTime(){
        return DateFormat.format("HH:mm:ss", new Date(pauseOffset)).toString();
    }
}
