package kz.nfactorial.nfactorialapp.store.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme

class StoreFragment: Fragment() {

    val args: StoreFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val navController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    StoreScreen(
                        storeUI = args.store,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}