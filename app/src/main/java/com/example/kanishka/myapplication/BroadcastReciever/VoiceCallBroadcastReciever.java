package com.example.kanishka.myapplication.BroadcastReciever;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.kanishka.myapplication.Service.RecorderService;

import java.util.Date;

public class VoiceCallBroadcastReciever extends PhoneCallBroadcastReciever {
    private static final String TAG ="CALL_RECORDER";

    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx,"State : onIncomingCallReceived, incoming_number : " + number,Toast.LENGTH_SHORT).show();
        displayFloatingWidget(ctx);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx,"State : onOutgoingCallStarted, outgoing_number : " + number,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ctx,RecorderService.class);
        ctx.startService(intent);
        displayFloatingWidget(ctx);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Intent intent = new Intent(ctx,RecorderService.class);
        ctx.stopService(intent);
        closeFloatingWidget(ctx);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        Intent intent = new Intent(ctx,RecorderService.class);
        ctx.stopService(intent);
        closeFloatingWidget(ctx);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Intent intent = new Intent(ctx,RecorderService.class);
        ctx.stopService(intent);
        closeFloatingWidget(ctx);
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start) {
        super.onIncomingCallAnswered(ctx, number, start);
        Toast.makeText(ctx,"State : onIncomingCallAnswered ",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ctx,RecorderService.class);
        ctx.startService(intent);
        displayFloatingWidget(ctx);
    }
}
