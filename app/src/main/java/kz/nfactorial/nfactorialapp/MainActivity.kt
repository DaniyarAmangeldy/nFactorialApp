package kz.nfactorial.nfactorialapp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kz.nfactorial.nfactorialapp.room.DatabaseHolder
import kz.nfactorial.nfactorialapp.ui.theme.LocalColors

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Column(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize()
            ) {
                var navController: NavController? = remember { null }
                AndroidView(
                    modifier = Modifier.weight(1f),
                    factory = { context ->
                        FragmentContainerView(context).apply {
                            id = View.generateViewId()
                            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
                            val navHostFragment = NavHostFragment()
                            supportFragmentManager
                                .beginTransaction()
                                .add(this, navHostFragment, null)
                                .commitNow()
                            navController = navHostFragment.navController
                            navController?.setGraph(R.navigation.app_graph)
                        }
                    }
                )
                var selected by remember { mutableIntStateOf(0) }
                NavigationBar(
                    modifier = Modifier.height(48.dp),
                    containerColor = LocalColors.current.back.primary,
                ) {
                    NavigationBarItem(
                        selected = selected == 0,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                        ),
                        onClick = {
                            selected = 0
                            navController?.navigate(R.id.homeFragment)
                        },
                        icon = {
                            if (selected == 0) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_home_24),
                                    contentDescription = "home",
                                    tint = LocalColors.current.brand.primary,
                                )
                            } else {
                                Icon(
                                    painter = painterResource(R.drawable.outline_home_24),
                                    contentDescription = "home"
                                )
                            }
                        },
                    )
                    NavigationBarItem(
                        selected = selected == 1,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                        ),
                        onClick = {
                            selected = 1
                            navController?.navigate(R.id.searchFragment)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_search_24),
                                contentDescription = "home",
                                tint = if (selected == 1) {
                                    LocalColors.current.brand.primary
                                } else {
                                    LocalColors.current.icon.primary
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}