package kz.nfactorial.nfactorialapp.home.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import kz.nfactorial.nfactorialapp.MainActivity
import kz.nfactorial.nfactorialapp.R

class HomeForegroundService: Service() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        Log.d("HomeForegroundService", "Service Created")
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup Service
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("HomeForegroundService", "Service Start Command")
        val notification = createNotification()
        ServiceCompat.startForeground(this, startId, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("Service is running...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }

    private companion object {

        const val CHANNEL_ID = "nFactorialAppChannel"
    }
}