package com.example.tbc_android_2025.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tbc_android_2025.presentation.common.Drawables
import com.example.tbc_android_2025.presentation.ui.theme.Navbar

private const val HEIGHT = 96
private const val BORDER_RADIUS = 25
private const val DISABLED = false
private const val SHOW_BACKGROUND = true

@Composable
fun AppBottomNavbar(modifier: Modifier = Modifier, backgroundColor: Color = Navbar) = Row(
    modifier = modifier
        .fillMaxWidth()
        .height(height = HEIGHT.dp)
        .background(
            color = backgroundColor,
            shape = RoundedCornerShape(topStart = BORDER_RADIUS.dp, topEnd = BORDER_RADIUS.dp)
        ),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceEvenly
) {
    AppBottomNavbarLikesButton(onClick =)
    AppBottomNavbarHomeButton(onClick =)
    AppBottomNavbarChatButton(onClick =)
    AppBottomNavbarNotificationsButton(onClick =)
    AppBottomNavbarFavoritesButton(onClick =)
}

@Composable
private fun AppBottomNavbarLikesButton(onClick: () -> Unit) =
    IconButton(onClick = onClick, enabled = DISABLED) {
        Icon(
            painter = painterResource(id = Drawables.ic_navbar_heart),
            contentDescription = null,
            tint = Color.Black
        )
    }

@Composable
private fun AppBottomNavbarHomeButton(onClick: () -> Unit) =
    IconButton(onClick = onClick, enabled = DISABLED) {
        Icon(
            painter = painterResource(id = Drawables.ic_home),
            contentDescription = null,
            tint = Color.Black
        )
    }

@Composable
private fun AppBottomNavbarChatButton(onClick: () -> Unit) =
    IconButton(onClick = onClick, enabled = DISABLED) {
        Icon(
            painter = painterResource(id = Drawables.ic_chat),
            contentDescription = null,
            tint = Color.Black
        )
    }

@Composable
private fun AppBottomNavbarNotificationsButton(onClick: () -> Unit) =
    IconButton(onClick = onClick, enabled = DISABLED) {
        Icon(
            painter = painterResource(id = Drawables.ic_bell),
            contentDescription = null,
            tint = Color.Black
        )
    }

@Composable
private fun AppBottomNavbarFavoritesButton(onClick: () -> Unit) =
    IconButton(onClick = onClick, enabled = DISABLED) {
        Icon(
            painter = painterResource(id = Drawables.ic_star),
            contentDescription = null,
            tint = Color.Black
        )
    }

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun AppBottomNavbarPreview() = AppBottomNavbar()
