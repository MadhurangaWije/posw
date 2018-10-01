package com.example.kanishka.myapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.kanishka.myapplication.Recorder.NativeCallAudioRecorder;

public class RecorderService extends Service {

    NativeCallAudioRecorder nativeCallAudioRecorder = new NativeCallAudioRecorder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);


        nativeCallAudioRecorder.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nativeCallAudioRecorder.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
