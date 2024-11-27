package kz.nfactorial.nfactorialapp.registration.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.ui.theme.LocalColors
import kz.nfactorial.nfactorialapp.ui.theme.LocalTypography

@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onEvent: (RegistrationEvent) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        Header { onEvent(RegistrationEvent.OnBackClick) }
        NameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            text = state.name,
            onEvent = onEvent
        )
        SizeTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = state.size,
            onEvent = onEvent,
        )
    }
}

@Composable
private fun Header(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = onBackClick,
                ),
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "back"
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.update_your_profile),
            style = LocalTypography.current.bold.bold700,
        )
    }
}

@Composable
private fun NameTextField(
    text: String,
    modifier: Modifier = Modifier,
    onEvent: (RegistrationEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .height(53.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(remember { RoundedCornerShape(8.dp) }),
            value = text,
            onValueChange = { onEvent(RegistrationEvent.OnNameChanged(it)) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LocalColors.current.back.base,
                focusedContainerColor = LocalColors.current.back.base,
                unfocusedTextColor = LocalColors.current.text.primary,
                focusedTextColor = LocalColors.current.text.primary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            textStyle = LocalTypography.current.medium.medium400,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_person_24),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.name_field_placeholder),
                    color = LocalColors.current.text.secondary,
                    style = LocalTypography.current.medium.medium300,
                )
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions { onEvent(RegistrationEvent.OnNameChangeSaveClicked) },
        )
    }
}

@Composable
private fun SizeTextField(
    text: String,
    modifier: Modifier = Modifier,
    onEvent: (RegistrationEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .height(53.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .clip(remember { RoundedCornerShape(8.dp) }),
            value = text,
            onValueChange = { onEvent(RegistrationEvent.OnSizeChanged(it)) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LocalColors.current.back.base,
                focusedContainerColor = LocalColors.current.back.base,
                unfocusedTextColor = LocalColors.current.text.primary,
                focusedTextColor = LocalColors.current.text.primary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            textStyle = LocalTypography.current.medium.medium400,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_numbers_24),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.size_field_placeholder),
                    color = LocalColors.current.text.secondary,
                    style = LocalTypography.current.medium.medium300,
                )
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Decimal,
            ),
            keyboardActions = KeyboardActions { onEvent(RegistrationEvent.OnSizeChangeSaveClicked) },
        )
    }
}