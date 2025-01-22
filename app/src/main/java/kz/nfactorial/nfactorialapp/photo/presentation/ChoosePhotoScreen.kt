package kz.nfactorial.nfactorialapp.photo.presentation

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.extensions.checkPermission
import kz.nfactorial.nfactorialapp.extensions.createImageFile
import kz.nfactorial.nfactorialapp.ui.theme.LocalColors
import kz.nfactorial.nfactorialapp.ui.theme.LocalTypography

@Composable
fun ChoosePhotoScreen(
    state: ChoosePhotoState,
    onEvent: (ChoosePhotoEvent) -> Unit,
    effect: ChoosePhotoEffect?,
) {
    val activity = LocalContext.current as Activity
    var imageFileUri = remember<Uri?> { null }
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSaved ->
        if (isSaved) {
            onEvent(ChoosePhotoEvent.OnPhotoSelected(imageFileUri))
        }
    }
    val requestCameraPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGrant ->
        if (isGrant) {
            imageFileUri = createImageFile(activity)
            imageFileUri?.let(cameraLauncher::launch)
        }
    }
    val selectFromGalleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        onEvent(ChoosePhotoEvent.OnPhotoSelected(uri))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("ChoosePhotoColumn")
    ) {
        AsyncImage(
            modifier = Modifier
                .testTag("ChoosePhotoImage")
                .padding(top = 64.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
            model = state.selectedPhoto ?: state.currentPhoto ?: R.drawable.baseline_person_24,
            contentDescription = "image",
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { onEvent(ChoosePhotoEvent.OnCameraClick) },
                colors = ButtonDefaults.buttonColors(containerColor = LocalColors.current.brand.primary)
            ) {
                Text(
                    text = stringResource(R.string.choose_from_camera),
                    color = LocalColors.current.text.primaryInverse,
                    style = LocalTypography.current.medium.medium300,
                )
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { onEvent(ChoosePhotoEvent.OnGalleryClick) },
                colors = ButtonDefaults.buttonColors(containerColor = LocalColors.current.brand.primary)
            ) {
                Text(
                    text = stringResource(R.string.choose_from_gallery),
                    color = LocalColors.current.text.primaryInverse,
                    style = LocalTypography.current.medium.medium300,
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = { onEvent(ChoosePhotoEvent.OnSubmitClick) },
            colors = ButtonDefaults.buttonColors(containerColor = LocalColors.current.brand.primary)
        ) {
            Text(
                text = stringResource(R.string.submit),
                color = LocalColors.current.text.primaryInverse,
                style = LocalTypography.current.medium.medium300,
            )
        }
    }

    LaunchedEffect(effect) {
        when (effect) {
            is ChoosePhotoEffect.Close -> {
                activity.setResult(RESULT_OK, Intent().putExtra(EXTRA_URL, effect.result))
                activity.finish()
            }
            is ChoosePhotoEffect.OpenCamera -> {
                val hasPermission = activity.checkPermission(Manifest.permission.CAMERA)
                if (hasPermission) {
                    val uri = createImageFile(activity).also { imageFileUri = it }
                    cameraLauncher.launch(uri)
                } else {
                    requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
            is ChoosePhotoEffect.OpenGallery -> {
                selectFromGalleryLauncher.launch(CONTENT_TYPE_IMAGE)
            }
            null -> Unit
        }
    }
}

const val EXTRA_URL = "extra_url"
private const val CONTENT_TYPE_IMAGE = "image/*"