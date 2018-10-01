package com.example.kanishka.myapplication.Recorder;

public interface AudioRecorder {

    //initialize the Android MediaRecorder, configuring AudioSource,OutputFormat,OutputFile path,AudioEncoder.
    public void init();
    //start MediaRecorder
    public void start();
    //stop MeidaRecorder, and release resources
    public void stop();

}
