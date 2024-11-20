package kz.nfactorial.nfactorialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kz.nfactorial.nfactorialapp.store.presentation.StoreScreen

class StoreDetailsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreScreen(
                store = intent.getParcelableExtra("extra_store")!!,
                onBackClick = { finish() }
            )
        }
    }
}