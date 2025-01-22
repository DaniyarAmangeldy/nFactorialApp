package kz.nfactorial.nfactorialapp.home.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import java.time.Duration
import kz.nfactorial.nfactorialapp.home.presentation.service.HomeBoundService
import kz.nfactorial.nfactorialapp.home.presentation.service.HomeForegroundService
import kz.nfactorial.nfactorialapp.home.presentation.service.HomeService
import kz.nfactorial.nfactorialapp.home.presentation.service.HomeServiceBinder
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import kz.nfactorial.nfactorialapp.work.SampleWorker
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val sampleWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<SampleWorker>()
//            .build()
//
//        WorkManager
//            .getInstance(requireContext())
//            .enqueue(sampleWorkRequest)


//        val intent = Intent(requireContext(), HomeServiceService::class.java)
//
//        requireContext().startService(intent)

//        val connection = object : ServiceConnection {
//
//            override fun onServiceConnected(className: ComponentName, service: IBinder) {
//                val binder = service as HomeServiceBinder
//                Log.d("HomeFragment", "Bound to service")
//                binder.printHelloWorld()
//            }
//
//            override fun onServiceDisconnected(arg0: ComponentName) {
//            }
//        }

//        val intent = Intent(requireContext(), HomeBoundService::class.java)
//
//        requireContext().bindService(intent, connection, Context.BIND_AUTO_CREATE)


//        val intent = Intent(requireContext(), HomeForegroundService::class.java)
//
//        startForegroundService(requireContext(), intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val navController = findNavController()
        setContent {
            AppTheme {
                val state by homeViewModel.homeState.collectAsState()
                val effect by homeViewModel.effect.collectAsState(null)
                HomeScreen(
                    state = state,
                    onEvent = { event -> homeViewModel.dispatch(event, navController) },
                    effect = effect,
                )
            }
        }
    }
}