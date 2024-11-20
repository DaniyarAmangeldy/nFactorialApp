package kz.nfactorial.nfactorialapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kz.nfactorial.nfactorialapp.home.presentation.HomeScreen
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                HomeScreen(
                    onStoreClick = { store ->
                        startActivity(
                            Intent(this, StoreDetailsActivity::class.java).apply {
                                putExtra("extra_store", store)
                            }
                        )
                    }
                )
            }
        }
    }
}