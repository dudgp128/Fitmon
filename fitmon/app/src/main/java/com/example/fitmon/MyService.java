package com.example.fitmon;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    NotificationManager notificationManager;
    ServiceThread thread;
    Notification Notifi ;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업

    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class myServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            //Notification 객체를 생성해주는 건축가객체 생성(AlertDialog 와 비슷)
            NotificationCompat.Builder builder= null;

            //Oreo 버전(API26 버전)이상에서는 알림시에 NotificationChannel 이라는 개념이 필수 구성요소가 됨.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                String channelID="channel_01"; //알림채널 식별자
                String channelName="MyChannel01"; //알림채널의 이름(별명)

                //알림채널 객체 만들기
                NotificationChannel channel= new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT);

                //알림매니저에게 채널 객체의 생성을 요청
                notificationManager.createNotificationChannel(channel);

                //알림건축가 객체 생성
                builder=new NotificationCompat.Builder(MyService.this, channelID);


            }else{
                //알림 건축가 객체 생성
                builder= new NotificationCompat.Builder(MyService.this, null);
            }

            //건축가에게 원하는 알림의 설정작업
            builder.setSmallIcon(android.R.drawable.ic_menu_view);

            //상태바를 드래그하여 아래로 내리면 보이는
            //알림창(확장 상태바)의 설정
            builder.setContentTitle("운동하실 시간이예요!");//알림창 제목
            builder.setContentText("피트몬과 함께 몬스터를 물리치며 운동하러 가요~");//알림창 내용
            //알림창의 큰 이미지
            Bitmap bm= BitmapFactory.decodeResource(getResources(),R.drawable.group);
            builder.setLargeIcon(bm);//매개변수가 Bitmap을 줘야한다.

            //알림창 클릭시 실행할 작엄
            Intent intent2 = new Intent(MyService.this, MainActivity.class);
            //지금 실행하는 것이 아니라 잠시 보류시키는 Intent 객체 필요
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            //알림창 클릭시 자동으로 알림 제거
            builder.setAutoCancel(true);

            //글씨 색 설정
            builder.setColor(Color.rgb(160,220,208));

            //건축가에게 알림 객체 생성하도록
            Notification notification=builder.build();

            //알림매니저에게 알림(Notify) 요청
            notificationManager.notify(1, notification);
        }
    };
}
