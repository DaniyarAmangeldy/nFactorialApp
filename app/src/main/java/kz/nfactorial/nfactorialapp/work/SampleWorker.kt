package kz.nfactorial.nfactorialapp.work

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kz.nfactorial.nfactorialapp.home.domain.repository.entity.HomeRepository
import org.koin.android.ext.android.inject

class SampleWorker(
    appContext: Context,
    workerParams: WorkerParameters
): CoroutineWorker(appContext, workerParams) {

    private val homeRepository: HomeRepository by (appContext as Application).inject()

    override suspend fun doWork(): Result {
        Log.d("SampleWorker", "Doing Some Operation!")
        return try {
            val components = homeRepository.getComponents().first()
            Log.d("SampleWorker", components.toString())
            Result.success()
        } catch (e: Throwable) {
            Result.failure()
        }
    }
}