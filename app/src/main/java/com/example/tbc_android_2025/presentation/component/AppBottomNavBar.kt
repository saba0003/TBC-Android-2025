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
import com.example.tbc_android_2025.presentation.screen.NavBarIcons
import com.example.tbc_android_2025.presentation.ui.theme.DesignTokens

private const val SHOW_BACKGROUND = true

@Composable
fun AppBottomNavBar(
    selectedIcon: NavBarIcons = NavBarIcons.HOME, onIconSelected: (NavBarIcons) -> Unit
) = Surface(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(
        topStart = DesignTokens.Radii.NavBar.Corner, topEnd = DesignTokens.Radii.NavBar.Corner
    ),
    color = DesignTokens.Colors.NavBar.Background
) {
    Row(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .padding(
                horizontal = DesignTokens.Insets.NavBar.Horizontal,
                vertical = DesignTokens.Insets.NavBar.Vertical
            ),
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
        .size(
            size = if (isSelected)
                DesignTokens.Radii.NavBar.IconSelected
            else
                DesignTokens.Radii.NavBar.IconDefault
        )
        .clickable { onClick() },
    contentAlignment = Alignment.Center
) {
    if (isSelected)
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = CircleShape,
            color = DesignTokens.Colors.NavBar.IconSelectedSurface
        ) {}

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.size(size = DesignTokens.Icons.NavBar.Size),
        tint = if (isSelected)
            DesignTokens.Colors.NavBar.IconSelected
        else
            DesignTokens.Colors.NavBar.IconDefault
    )
}

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun AppBottomNavBarPreview() = AppBottomNavBar {}
