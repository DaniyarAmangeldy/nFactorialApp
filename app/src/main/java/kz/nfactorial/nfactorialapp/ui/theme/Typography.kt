package kz.nfactorial.nfactorialapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kz.nfactorial.nfactorialapp.R

data class Typography(
    val semibold: SemiboldTypography,
    val bold: BoldTypography,
    val medium: MediumTypography,
)

data class SemiboldTypography(
    val semibold600: TextStyle,
    val semibold500: TextStyle,
    val semibold400: TextStyle,
    val semibold300: TextStyle,
)

data class BoldTypography(
    val bold900: TextStyle,
    val bold800: TextStyle,
    val bold700: TextStyle,
    val bold600: TextStyle,
    val bold500: TextStyle,
    val bold400: TextStyle,
)

data class MediumTypography(
    val medium500: TextStyle,
    val medium400: TextStyle,
    val medium300: TextStyle,
)

data class Title1Typography(
    val boldMultiline: TextStyle,
    val semibold: TextStyle,
)

data class Title2Typography(
    val semibold: TextStyle,
)

data class Title3Typography(
    val semibold: TextStyle,
)

data class SubtitleTypography(
    val semibold: TextStyle,
    val semiboldMultiline: TextStyle,
    val regular: TextStyle,
)

data class BodyTypography(
    val semibold: TextStyle,
    val multilineSemibold: TextStyle,
    val regular: TextStyle,
    val regularMultiline: TextStyle,
)

data class SubbodyTypography(
    val semibold: TextStyle,
    val regular: TextStyle,
    val regularMultiline: TextStyle,
)

data class CaptionTypography(
    val semibold: TextStyle,
    val medium: TextStyle,
    val regular: TextStyle,
    val regularMultiline: TextStyle,
)

data class BottomNavBarTypography(
    val active: TextStyle,
    val default: TextStyle,
)

val fontFamily = FontFamily(
    Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold),
    Font(R.font.plus_jakarta_sans_semibold, FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans_medium, FontWeight.Medium),
)

val appTypography = Typography(
    semibold = SemiboldTypography(
        semibold600 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        semibold500 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        semibold400 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        semibold300 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
    ),
    bold = BoldTypography(
        bold900 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold800 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold700 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold500 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold600 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold400 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
        ),
    ),
    medium = MediumTypography(
        medium500 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        ),
        medium400 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
        ),
        medium300 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    )
)

val appTypographySmall = Typography(
    semibold = SemiboldTypography(
        semibold600 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        semibold500 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        semibold400 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        semibold300 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.SemiBold,
        ),
    ),
    bold = BoldTypography(
        bold900 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 30.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold800 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold700 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold500 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold600 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
        ),
        bold400 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Bold,
        ),
    ),
    medium = MediumTypography(
        medium500 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Medium,
        ),
        medium400 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Medium,
        ),
        medium300 = TextStyle(
            fontFamily = fontFamily,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    )
)

val LocalTypography = staticCompositionLocalOf { appTypography }
