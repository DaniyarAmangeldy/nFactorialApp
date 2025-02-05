package kz.nfactorial.nfactorialapp.push

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*
import kz.nfactorial.nfactorialapp.MainActivity
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.extensions.checkPermission

class MessagingService : FirebaseMessagingService() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("MessagingService", "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val remoteNotification = message.notification ?: return
        Log.d("MessagingService", "onMessageReceived: ${remoteNotification.title} | ${remoteNotification.body}")
        if (checkPermission(Manifest.permission.POST_NOTIFICATIONS)) {
            showNotification(createNotification(remoteNotification))
        }
    }

    private fun createNotificationChannel(): NotificationChannel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "nFactorialApp Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
            channel
        } else {
            null
        }
    }

    private fun createNotification(notification: RemoteMessage.Notification): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(notification.title)
            .setContentText(notification.body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showNotification(notification: Notification) {
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(UUID.randomUUID().hashCode(), notification)
    }

    private companion object {
        const val CHANNEL_ID = "nFactorialAppChannel"
    }
}