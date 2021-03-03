package com.example.activitatfinal;

import android.content.BroadcastReceiver;

public class ReceptorXarxa extends BroadcastReceiver {
    @Override
    public void onRecive(Context context, Intent intent){
        ActualitzaEstatXarxa(context);
    }
}
