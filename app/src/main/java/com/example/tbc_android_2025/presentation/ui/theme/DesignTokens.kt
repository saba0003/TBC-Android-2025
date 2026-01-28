package com.example.tbc_android_2025.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object DesignTokens {
    object Dimensions {
        object AppRatingBar {
            private const val SIZE = 16

            val Size = SIZE.dp
        }
    }

    object Insets {

        object NavBar {
            private const val HORIZONTAL = 45
            private const val VERTICAL = 20

            val Horizontal = HORIZONTAL.dp
            val Vertical = VERTICAL.dp
        }
    }

    object Radii {
        object NavBar {
            private const val CORNER = 28
            private const val ICON_DEFAULT = 48
            private const val ICON_SELECTED = 64

            val Corner = CORNER.dp
            val IconDefault = ICON_DEFAULT.dp
            val IconSelected = ICON_SELECTED.dp
        }
    }

    object Icons {
        object NavBar {
            private const val SIZE = 28

            val Size = SIZE.dp
        }
    }

    object Colors {
        private const val PURPLE_80 = 0xFFD0BCFF
        private const val PURPLE_GREY_80 = 0xFFCCC2DC
        private const val PINK_80 = 0xFFEFB8C8

        private const val PURPLE_40 = 0xFF6650A4
        private const val PURPLE_GREY_40 = 0xFF625B71
        private const val PINK_40 = 0xFF7D5260

        private const val TEAL_200 = 0xFF03DAC5
        private const val TEAL_700 = 0xFF018786

        private const val LIGHT_GREEN = 0xFF70ED63
        private const val AMARANTH = 0xFFE52B50
        private const val VIRIDIAN = 0xFF40826D
        private const val HOLO_BLUE_BRIGHT = 0xFF00DDFF
        private const val BACKGROUND_DARK = 0xFF1F2C34
        private const val SURFACE_DARK = 0xFF2A3942
        private const val TEXT_GREY = 0xFF8696A0


        val Purple80 = Color(color = PURPLE_80)
        val PurpleGrey80 = Color(color = PURPLE_GREY_80)
        val Pink80 = Color(color = PINK_80)

        val Purple40 = Color(color = PURPLE_40)
        val PurpleGrey40 = Color(color = PURPLE_GREY_40)
        val Pink40 = Color(color = PINK_40)

        val Teal200 = Color(color = TEAL_200)
        val Teal700 = Color(color = TEAL_700)

        val LightGreen = Color(color = LIGHT_GREEN)
        val Amaranth = Color(color = AMARANTH)
        val Viridian = Color(color = VIRIDIAN)
        val HoloBlueBright = Color(color = HOLO_BLUE_BRIGHT)
        val BackgroundDark = Color(color = BACKGROUND_DARK)
        val SurfaceDark = Color(color = SURFACE_DARK)
        val TextGrey = Color(color = TEXT_GREY)

        object RatingBar {
            private const val FILLED = 0xFFFFFFFF
            private const val UNFILLED = 0x80FFFFFF

            val Filled = Color(color = FILLED)
            val Unfilled = Color(color = UNFILLED)
        }

        object NavBar {
            private const val BACKGROUND = 0xFF2A3942
            private const val ICON_DEFAULT = 0xFF8696A0
            private const val ICON_SELECTED = 0xFFFFFFFF
            private const val ICON_SELECTED_SURFACE = 0xFF4ADE80

            val Background = Color(color = BACKGROUND)
            val IconDefault = Color(color = ICON_DEFAULT)
            val IconSelected = Color(color = ICON_SELECTED)
            val IconSelectedSurface = Color(color = ICON_SELECTED_SURFACE)
        }
    }
}
