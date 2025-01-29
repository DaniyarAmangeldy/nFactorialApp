package kz.nfactorial.nfactorialapp.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme

class MapsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                val kazakhstanLatLng = remember { LatLng(48.1448, 67.1429) }
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(kazakhstanLatLng, 8f)
                }
                val almatyLatLng = remember { LatLng(43.2220, 76.8512) }
                val astanaLatLng = remember { LatLng(51.1694, 71.4491) }
                val almatyMarkerState = rememberMarkerState(position = almatyLatLng)
                val astanaMarkerState = rememberMarkerState(position = astanaLatLng)
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                ) {
                    Marker(
                        state = almatyMarkerState,
                        title = "Almaty",
                        snippet = "This is Almaty!"
                    )
                    Marker(
                        state = astanaMarkerState,
                        title = "Astana",
                        snippet = "This is Astana!"
                    )
                }
            }
        }
    }

}