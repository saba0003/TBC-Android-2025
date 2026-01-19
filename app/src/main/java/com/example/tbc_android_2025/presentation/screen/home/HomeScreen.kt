package com.example.tbc_android_2025.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tbc_android_2025.presentation.common.Drawables
import com.example.tbc_android_2025.presentation.common.Images
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.screen.component.AppButton
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.Event
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.SideEffect
import com.example.tbc_android_2025.presentation.ui.theme.ComfortaaFamily
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeLarge
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeMedium

private const val SCREEN_HORIZONTAL_PADDING = 24
private const val SCREEN_VERTICAL_SPACE_BETWEEN_CONTENTS = 32
private const val TAKE_SPACE_EVENLY = 1F
private const val PROFILE_PHOTO_BOTTOM_PADDING = 10
private const val SPACE_BETWEEN_PAGE_LOGO_AND_TEXT = 32
private const val SPACE_BETWEEN_USER_PROFILE_PHOTO_AND_USER_DETAILS = 5
private const val SPACE_BETWEEN_BUTTONS = 24
private const val SECONDARY_BUTTON = false
private const val SHOW_BACKGROUND = true

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit
) = with(receiver = viewModel) {

    LaunchedEffect(key1 = Unit) {
        sideEffect.collect {
            when (it) {
                is SideEffect.NavigateToLoginScreen -> onNavigateToLoginScreen()
                is SideEffect.NavigateToRegisterScreen -> onNavigateToRegisterScreen()
            }
        }
    }

    HomeScreenContent(
        onLoginClick = { onEvent(event = Event.OnLoginButtonClicked) },
        onRegisterClick = { onEvent(event = Event.OnRegisterButtonClicked) }
    )
}

@Composable
private fun HomeScreenContent(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(bottom = SCREEN_VERTICAL_SPACE_BETWEEN_CONTENTS.dp),
    verticalArrangement = Arrangement.spacedBy(space = SCREEN_VERTICAL_SPACE_BETWEEN_CONTENTS.dp)
) {
    HomeScreenHeaderSection()
    HomeScreenActionButtons(onLoginClick = onLoginClick, onRegisterClick = onRegisterClick)
}

@Composable
private fun ColumnScope.HomeScreenHeaderSection() = Box(
    modifier = Modifier
        .fillMaxWidth()
        .weight(weight = TAKE_SPACE_EVENLY)
) {
    HomeScreenBackgroundImage()
    HomeScreenTitle()
    HomeScreenProfileInfo()
}

@Composable
private fun BoxScope.HomeScreenBackgroundImage() = Image(
    painter = painterResource(id = Drawables.entry_page_background),
    contentDescription = stringResource(id = Strings.content_description_home_screen_background_image),
    modifier = Modifier.matchParentSize(),
    contentScale = ContentScale.Crop
)

@Composable
private fun BoxScope.HomeScreenTitle() = Row(
    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .align(alignment = Alignment.Center),
    horizontalArrangement = Arrangement.spacedBy(
        space = SPACE_BETWEEN_PAGE_LOGO_AND_TEXT.dp, alignment = Alignment.CenterHorizontally
    ),
    verticalAlignment = Alignment.CenterVertically
) {
    Image(
        painter = painterResource(id = Drawables.ic_logo),
        contentDescription = stringResource(id = Strings.content_description_home_screen_app_logo),
        modifier = Modifier.wrapContentSize()
    )

    Text(
        text = stringResource(id = Strings.photo),
        modifier = Modifier.wrapContentSize(),
        fontFamily = ComfortaaFamily,
        fontSize = FontSizeLarge
    )
}

@Composable
private fun BoxScope.HomeScreenProfileInfo() = Row(
    modifier = Modifier
        .wrapContentSize()
        .padding(start = SCREEN_HORIZONTAL_PADDING.dp, bottom = PROFILE_PHOTO_BOTTOM_PADDING.dp)
        .align(alignment = Alignment.BottomStart),
    horizontalArrangement = Arrangement.spacedBy(space = SPACE_BETWEEN_USER_PROFILE_PHOTO_AND_USER_DETAILS.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Image(
        painter = painterResource(id = Images.profile_photo_round),
        contentDescription = stringResource(id = Strings.content_description_home_screen_user_profile_image),
        modifier = Modifier.wrapContentSize()
    )

    Column(modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.SpaceEvenly) {
        Text(
            text = stringResource(id = Strings.username),
            modifier = Modifier.wrapContentSize(),
            fontSize = FontSizeMedium
        )

        Text(
            text = stringResource(id = Strings.user_email),
            modifier = Modifier.wrapContentSize(),
            fontSize = FontSizeMedium
        )
    }
}

@Composable
private fun HomeScreenActionButtons(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = SCREEN_HORIZONTAL_PADDING.dp),
    horizontalArrangement = Arrangement.spacedBy(space = SPACE_BETWEEN_BUTTONS.dp)
) {
    HomeScreenLoginButton(onClick = onLoginClick)
    HomeScreenRegisterButton(onClick = onRegisterClick)
}

@Composable
private fun RowScope.HomeScreenLoginButton(onClick: () -> Unit) = AppButton(
    text = stringResource(id = Strings.login),
    onClick = onClick,
    modifier = Modifier.weight(weight = TAKE_SPACE_EVENLY),
    isPrimary = SECONDARY_BUTTON
)

@Composable
private fun RowScope.HomeScreenRegisterButton(onClick: () -> Unit) = AppButton(
    text = stringResource(id = Strings.register),
    onClick = onClick,
    modifier = Modifier.weight(weight = TAKE_SPACE_EVENLY)
)

@Composable
@Preview(showBackground = SHOW_BACKGROUND)
private fun HomeScreenPreview() = HomeScreenContent(onLoginClick = {}, onRegisterClick = {})
