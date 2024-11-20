package kz.nfactorial.nfactorialapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme

class HomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val navController = findNavController()
        return ComposeView(requireContext()).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setContent {
                AppTheme {
                    HomeScreen(
                        onStoreClick = { store ->
                            navController.navigate(HomeFragmentDirections.actionHomeToStore(store))
                        }
                    )
                }
            }
        }
    }
}