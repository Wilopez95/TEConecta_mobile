package com.example.teconecta;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlertReciver extends BroadcastReceiver {
    NotificationCompat.Builder mBuilder;
    int mNotificationId = 1;
    String channelId = "my_Channel_01";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mBuilder = new NotificationCompat.Builder(context,null);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            CharSequence name = "Oferta";

            String description = "Comunicacion de ogertas a usaurio";
            int importance =  NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(channelId,name , importance);

            mChannel.setDescription(description);
            mChannel.enableLights(true);

            mChannel.setLightColor(Color.BLUE);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
            mNotificationManager.createNotificationChannel(mChannel);

            mBuilder =  new NotificationCompat.Builder(context,channelId);
        }

        mBuilder.setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("TEConecta")
                .setContentText("Un evento que te interesa esta pronto a iniciar");


        mNotificationManager.notify(mNotificationId,mBuilder.build());
    }
}
