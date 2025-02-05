package kz.nfactorial.nfactorialapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val text: TextColors,
    val brand: BrandColors,
    val element: ElementColors,
    val back: BackColors,
    val icon: IconColors
)

data class TextColors(
    val primary: Color,
    val secondary: Color,
    val primaryInverse: Color,
    val brand: Color,
    val action: Color
)

data class BrandColors(
    val primary: Color,
    val secondary: Color
)

data class ElementColors(
    val light: Color,
    val lightAlt: Color,
    val star: Color,
    val back: Color,
    val like: Color,
    val inverse: Color,
    val destructive: Color,
    val destructiveBack: Color
)

data class BackColors(
    val primary: Color,
    val base: Color
)

data class IconColors(
    val secondary: Color,
    val primary: Color,
    val additional: Color,
    val backPrimary: Color,
    val inverse: Color
)

val appColors = Colors(
    text = TextColors(
        primary = Color(0xff1f1f1f),
        secondary = Color(0xffa5a5a8),
        primaryInverse = Color(0xffffffff),
        brand = Color(0xff36ac72),
        action = Color(0xff3082e2)
    ),
    brand = BrandColors(
        primary = Color(0xff36ac72),
        secondary = Color(0x36ac721a)
    ),
    element = ElementColors(
        light = Color(0xffd9d9d9),
        lightAlt = Color(0xfff8f8f8),
        star = Color(0xffffc962),
        back = Color(0x1f1f1f0d),
        like = Color(0xffdb414a),
        inverse = Color(0xffffffff),
        destructive = Color(0xffdb414a),
        destructiveBack = Color(0xdb414a),
    ),
    back = BackColors(
        primary = Color(0xffffffff),
        base = Color(0xfff2f1f6)
    ),
    icon = IconColors(
        secondary = Color(0xffa5a5a8),
        primary = Color(0xff1f1f1f),
        additional = Color(0xffdedede),
        backPrimary = Color(0x0d36ac72),
        inverse = Color(0xffffffff)
    )
)

internal val LocalColors = staticCompositionLocalOf { appColors }
