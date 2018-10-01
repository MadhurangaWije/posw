package com.example.kanishka.myapplication.Recorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NativeCallAudioRecorder {
    private static final String LOG_TAG = "CALL_RECORDER";

    private final java.util.Random rand = new java.util.Random();
    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    private final Set<String> identifiers = new HashSet<>();

    private String AudioSavePathInDevice;
    private MediaRecorder mediaRecorder;

    public void init(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
    }

    public void start()
    {
        setOutputFileName();
        init();
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Log.d("@@@@@@","*****************************mediaRecorder started!********************************");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(LOG_TAG, "Recording Stared");
    }
    //pause() supports API level 24(Android 7.0) onwards
//    public void pause(){
//        mediaRecorder.pause();
//    }

    public void stop(){
        mediaRecorder.stop();
        mediaRecorder.release();
    }

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    void setOutputFileName(){
        createSavingDirectory();
        String fileName = "/Plantronic/Recording_"+randomIdentifier()+".3gp";
        AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath()+fileName;
    }

    void createSavingDirectory(){
        File base_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Plantronic");
        try{
            if(base_dir.mkdir()) {
                System.out.println("Directory created");
            } else {
                System.out.println("Directory is not created");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        File sub_dir_native_calls = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Plantronic/NativeCalls");
        try{
            if(sub_dir_native_calls.mkdir()) {
                System.out.println("Directory created");
            } else {
                System.out.println("Directory is not created");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}


//overlay widget
//https://www.journaldev.com/14673/android-floating-widget