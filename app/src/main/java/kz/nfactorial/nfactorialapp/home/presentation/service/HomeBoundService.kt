package kz.nfactorial.nfactorialapp.home.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class HomeBoundService: Service() {

    val binder = HomeServiceBinder()

    override fun onCreate() {
        super.onCreate()
        // Initialize Service
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup Service
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }
}