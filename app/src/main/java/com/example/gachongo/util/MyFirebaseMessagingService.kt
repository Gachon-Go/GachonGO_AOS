package com.example.gachongo.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.gachongo.presentation.main.MainActivity
import com.example.gachongo_aos.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val TAG = "FCM"

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG, "FCM/onNewToken: $token")
    }

    /**
     * 디바이스가 FCM을 통해서 메시지를 받으면 수행된다.
     * @remoteMessage: FCM에서 보낸 데이터 정보들을 저장한다.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: " + remoteMessage.from)

        super.onMessageReceived(remoteMessage)

        // FCM을 통해서 전달 받은 정보에 Notification 정보가 있는 경우 알림을 생성한다.
        if (remoteMessage.notification != null){
            sendNotification(remoteMessage)
        }else{
            Log.d(TAG, "FCM Error : Notification이 비어있습니다.")
        }
    }

    /**
     * FCM에서 보낸 정보를 바탕으로 디바이스에 Notification을 생성한다.
     * @remoteMessage: FCM에서 보냄
     */
    private fun sendNotification(remoteMessage: RemoteMessage){
        // RequestCode, Id를 고유값으로 지정하여 알림이 개별 표시되도록 함
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()

        // 일회용 PendingIntent
        // PendingIntent : Intent 의 실행 권한을 외부의 어플리케이션에게 위임한다.
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack 을 경로만 남긴다. A-B-C-D-B => A-B
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // 알림 채널 이름
        val channelId = "Gachon Go"

        // 알림 소리
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // 알림에 대한 UI 정보와 작업을 지정한다.
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_logo) // 아이콘 설정
            .setContentTitle(remoteMessage.data["body"].toString()) // 제목
            .setContentText(remoteMessage.data["title"].toString()) // 메시지 내용
            .setAutoCancel(true)
            .setSound(soundUri) // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 이후에는 채널이 필요하다.
        val channel = NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        // 알림 생성
        notificationManager.notify(uniId, notificationBuilder.build())
    }

}