package kz.nfactorial.nfactorialapp.home.presentation.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.nfactorial.nfactorialapp.NFactorialAppApplication
import kz.nfactorial.nfactorialapp.home.domain.repository.entity.HomeRepository
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class HomeService: Service() {

    private val homeRepository: HomeRepository by inject()

    private val parentCoroutineScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        Log.d("HomeService", "Service Created")
    }

    override fun onDestroy() {
        super.onDestroy()
        parentCoroutineScope.cancel()
        // Cleanup Service
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("HomeService", "Service Start Command")
        parentCoroutineScope.launch {
            homeRepository.getComponents().collect { components ->
                Log.d("HomeService", components.toString())
            }
        }

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}