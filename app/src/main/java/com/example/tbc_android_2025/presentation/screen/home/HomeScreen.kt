package com.example.tbc_android_2025.presentation.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.tbc_android_2025.presentation.common.Drawables
import com.example.tbc_android_2025.presentation.common.Images
import com.example.tbc_android_2025.presentation.common.Strings
import com.example.tbc_android_2025.presentation.screen.home.HomeContract.*
import com.example.tbc_android_2025.presentation.ui.theme.ComfortaaFamily
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeLarge
import com.example.tbc_android_2025.presentation.ui.theme.FontSizeMedium

private const val BORDER_RADIUS = 6
private const val LOGIN_BUTTON_BORDER_STROKE_WIDTH = 2

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (background, logo, backgroundLabel, profile, username, email, loginButton, registerButton) = createRefs()
        createHorizontalChain(loginButton, registerButton)
        createHorizontalChain(logo, backgroundLabel, chainStyle = ChainStyle.Spread)
//    createVerticalChain(background, loginButton, chainStyle = ChainStyle.Spread)

        LaunchedEffect(Unit) {
            viewModel.sideEffect.collect {
                when (it) {
                    is SideEffect.NavigateToLoginScreen -> onNavigateToLoginScreen()
                    is SideEffect.NavigateToRegisterScreen -> onNavigateToRegisterScreen()
                }
            }
        }

        Image(
            painter = painterResource(id = Images.entry_page_background_foreground),
            contentDescription = stringResource(id = Strings.content_description_home_screen_background_image),
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(background) {
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = loginButton.top)
                },
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = Drawables.ic_logo),
            contentDescription = stringResource(id = Strings.content_description_home_screen_app_logo),
            modifier = Modifier.constrainAs(ref = logo) {
                width = Dimension.wrapContent
                height = Dimension.wrapContent
                centerTo(other = background)
            }
        )

        Text(
            text = stringResource(id = Strings.photo),
            modifier = Modifier.constrainAs(backgroundLabel) {
                width = Dimension.wrapContent
                height = Dimension.wrapContent
                top.linkTo(logo.top)
                bottom.linkTo(logo.bottom)
            },
            fontFamily = ComfortaaFamily,
            fontSize = FontSizeLarge
        )

        Image(
            painter = painterResource(id = Images.ic_profile_photo_round),
            contentDescription = stringResource(id = Strings.content_description_home_screen_user_profile_image),
            modifier = Modifier.constrainAs(ref = profile) {
                width = Dimension.wrapContent
                height = Dimension.wrapContent
                start.linkTo(anchor = background.start)
                bottom.linkTo(anchor = background.bottom, margin = 32.dp)
            }
        )

        Text(
            text = stringResource(id = Strings.username),
            modifier = Modifier.constrainAs(ref = username) {
                width = Dimension.wrapContent
                height = Dimension.wrapContent
                start.linkTo(anchor = profile.end, margin = 10.dp)
                top.linkTo(anchor = profile.top)
                bottom.linkTo(anchor = email.top)
            },
            fontSize = FontSizeMedium
        )

        Text(
            text = stringResource(id = Strings.user_email),
            modifier = Modifier.constrainAs(ref = email) {
                width = Dimension.wrapContent
                height = Dimension.wrapContent
                start.linkTo(anchor = username.start)
                top.linkTo(anchor = username.bottom)
                bottom.linkTo(anchor = profile.bottom)
            },
            fontSize = FontSizeMedium
        )

        Button(
            onClick = { viewModel.onEvent(event = Event.OnLoginButtonClicked) },
            modifier = Modifier
                .constrainAs(ref = loginButton) {
                    width = Dimension.value(167.dp)
                    height = Dimension.value(48.dp)
                    top.linkTo(background.bottom, margin = 32.dp)
                    bottom.linkTo(parent.bottom, margin = 32.dp)
                },
            shape = RoundedCornerShape(BORDER_RADIUS.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(LOGIN_BUTTON_BORDER_STROKE_WIDTH.dp, Color.Black),
        ) {
            Text(
                text = stringResource(id = Strings.login),
                fontSize = FontSizeMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = { viewModel.onEvent(event = Event.OnRegisterButtonClicked) },
            modifier = Modifier
                .constrainAs(registerButton) {
                    top.linkTo(loginButton.top)
                    bottom.linkTo(loginButton.bottom)
                    width = Dimension.value(167.dp)
                    height = Dimension.value(48.dp)
                },
            shape = RoundedCornerShape(BORDER_RADIUS.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
        ) {
            Text(
                text = stringResource(id = Strings.register),
                fontSize = FontSizeMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() = HomeScreen(
    onNavigateToLoginScreen = {},
    onNavigateToRegisterScreen = {}
)
