package com.example.tbc_android_2025.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tbc_android_2025.design.navbar.NavBarColors
import com.example.tbc_android_2025.design.navbar.NavBarInsets
import com.example.tbc_android_2025.design.navbar.NavBarRadii
import com.example.tbc_android_2025.navigation.NavBarIcons
import com.example.tbc_android_2025.presentation.ui.theme.TBCAndroid2025Theme

private const val SIZE = 28
private const val SHOW_BACKGROUND = true
private const val IS_IN_LIGHT_MODE = false
private const val IS_IN_DARK_MODE = true
private const val LIGHT_MODE = "Light Mode"
private const val DARK_MODE = "Dark Mode"

@Composable
fun AppBottomNavBar(
    selectedIcon: NavBarIcons = NavBarIcons.HOME, onIconSelected: (NavBarIcons) -> Unit
) = Surface(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(topStart = NavBarRadii.Corner, topEnd = NavBarRadii.Corner),
    color = NavBarColors.Background
) {
    Row(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .padding(horizontal = NavBarInsets.Horizontal, vertical = NavBarInsets.Vertical),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavBarIcon(
            icon = Icons.Rounded.FavoriteBorder, isSelected = selectedIcon == NavBarIcons.FAVORITES
        ) {
            onIconSelected(NavBarIcons.FAVORITES)
        }

        NavBarIcon(icon = Icons.Rounded.Home, isSelected = selectedIcon == NavBarIcons.HOME) {
            onIconSelected(NavBarIcons.HOME)
        }

        NavBarIcon(icon = Icons.Rounded.ChatBubble, isSelected = selectedIcon == NavBarIcons.CHAT) {
            onIconSelected(NavBarIcons.CHAT)
        }
    }
}

@Composable
private fun NavBarIcon(icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) = Box(
    modifier = Modifier
        .clip(shape = CircleShape)
        .size(size = if (isSelected) NavBarRadii.IconSelected else NavBarRadii.IconDefault)
        .clickable { onClick() },
    contentAlignment = Alignment.Center
) {
    if (isSelected)
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = CircleShape,
            color = NavBarColors.IconSelectedSurface
        ) {}

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.size(size = SIZE.dp),
        tint = if (isSelected) NavBarColors.IconSelected else NavBarColors.IconDefault
    )
}

@Composable
@Preview(name = LIGHT_MODE, showBackground = SHOW_BACKGROUND)
private fun AppBottomNavBarLightModePreview() =
    TBCAndroid2025Theme(darkTheme = IS_IN_LIGHT_MODE) { AppBottomNavBar {} }

@Composable
@Preview(name = DARK_MODE, showBackground = SHOW_BACKGROUND)
private fun AppBottomNavBarDarkModePreview() =
    TBCAndroid2025Theme(darkTheme = IS_IN_DARK_MODE) { AppBottomNavBar {} }
