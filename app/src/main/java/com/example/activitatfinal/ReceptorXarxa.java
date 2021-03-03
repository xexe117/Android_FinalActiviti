package com.example.activitatfinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceptorXarxa extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ActualitzaEstatXarxa(context);
    }
}
