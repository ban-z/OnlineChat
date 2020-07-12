package com.example.onlinechat.Notifaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.example.onlinechat.R;
import com.example.onlinechat.fragment.HomeFrag.ChatFragment;

public class NoteReceive {

    private Context context;
    private int Receiver;
    private String informaiton;

    private static String CHANNEL_ID = "receive_msg";
    private static String channel_name = "receiver";
    private static String channel_description = "client receive the message";

    /*
    * 利用NotificationCompat以及其子类，和NotificationManagerCompat
    * 就无需编写条件代码来检查API级别
    * */
    //NotificationCompat.Builder用来设置通知内容
    private static NotificationCompat.Builder builder = null;
    //创建一个渠道，在发送通知之前，将通知分组到一个channel
    private static NotificationChannel channel = null;
    private static NotificationManager notificationManager = null;

    private static int NotificationID = 1;

    public NoteReceive(Context context, int receiver, String informaiton) {
        this.context = context;
        Receiver = receiver;
        this.informaiton = informaiton;
    }

    /*
    * 在Android8.0及以上，必须在发送通知之前创建一个channel（渠道），才能正常显示通知
    * channel便于分组管理众多通知
    * 而且重复调用createNotificationChannel()是安全的，它不会重复创建实例
    * */
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_HIGH;

            channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            //实例化notificationManager
            notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void buildNotificationCompat() {
        Intent intent = new Intent(context, ChatFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        //在新版本的Notifaction中需要为Builder提供一个CHANNEL
        builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(String.valueOf(Receiver))
                .setContentText(informaiton)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    public void displayNotificaiton(){
        createNotificationChannel();
        buildNotificationCompat();
        notificationManager.notify(NotificationID, builder.build());
    }
}
